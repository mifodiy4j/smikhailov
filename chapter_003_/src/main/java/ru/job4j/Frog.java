package ru.job4j;

import java.util.Scanner;

/**
 * Класс проверяет возможность на кругу, разбитого на 16 секторов и
 * 10 колец, добраться из начального сегмента в конечный, имея возможность менять
 * положение заданными способами <code>jumps</code>.
 * Если движение возможно, то выводит минимальное количество шагов и набор
 * шагов необходимый для достижения конечного сегмента.
 * В условии задаются сегменты не доступные для нахождения на них.
 * @author  Sergey Mikhailov
 * @since   1.0
 */
public class Frog {

    protected Jump[] jumps = {
            new Jump(0, 3),
            new Jump(-1, 2),
            new Jump(1, 2),
            new Jump(-2, 1),
            new Jump(2, 1)
    };

    protected Dot[] tree;

    protected final int n = jumps.length;
    protected Dot start;
    protected Dot finish;
    protected int[] maxIt = new int[n]; //количество возможных ходов для каждого типа для достижения конечного сегмента
    protected int[] iIt = new int[n];
    protected int[] bestAm = new int[n];
    protected int bestNumberOfSteps = 0;

    /**
     * Конструктор
     */
    public Frog() {

        enterData();

        int deltaCircle = finish.getCircle() - start.getCircle();
        int deltaSector = finish.getSector() - start.getSector();

        deltaSector = (deltaSector > 0) ? deltaSector : (16 + deltaSector);

        for (int i = 0; i < n; i++) {

            if (jumps[i].getCircle() == 0 || (int) (deltaCircle / jumps[i].getCircle()) < 0) {
                maxIt[i] = (int) (deltaSector / jumps[i].getSector());
            } else {
                maxIt[i] = Math.min(
                        (int) (deltaCircle / jumps[i].getCircle()),
                        (int) (deltaSector / jumps[i].getSector())
                );
            }
        }

        calcWithRecursion(0);

        if (bestNumberOfSteps != 0) {
            System.out.printf("Minimum number of steps = %d %n", bestNumberOfSteps);

            System.out.printf("Need to be steps = %n");
            for (int i = 0; i < n; i++) {
                System.out.printf("%25d  -  %s   %n", bestAm[i], jumps[i].toString());
            }
        } else {
            System.out.printf("Нет возможности добраться из исходного положения в конечное %n");
        }
    }

    /**
     * Метод читает с консоли введенные данные
     * начальный и конечный сегменты,
     * количество деревьев,
     * коордмнаты сегментов занятых деревьями
     */
    private void enterData() {

        System.out.printf("Начальный сегмент номер кольца = ");
        Scanner in = new Scanner(System.in);
        int startCircle = in.nextInt();

        System.out.printf("Начальный сегмент номер сектора = ");
        int startSector = in.nextInt();

        start = new Dot(startCircle, startSector);

        System.out.printf("Конечный сегмент номер кольца = ");
        int finishCircle = in.nextInt();

        System.out.printf("Конечный сегмент номер сектора = ");
        int finishSector = in.nextInt();

        finish = new Dot(finishCircle, finishSector);

        System.out.printf("Количество деревьев = ");
        int quantityTree = in.nextInt();

        tree = new Dot[quantityTree];

        for (int i = 0; i < quantityTree; i++) {
            System.out.printf("Номер кольца %d-ого дерева = ", (i + 1));
            int x = in.nextInt();

            System.out.printf("Номер сектора %d-ого дерева = ", (i + 1));
            int y = in.nextInt();
            tree[i] = new Dot(x, y);
        }
    }

    /**
     * Метод возвращает следующую точку <code>Dot</code>
     * если из текущей точки <code>current</code>
     * осуществить движение <code>jump</code>
     * <code>number</code> количество раз.
     * Возвращает текущую точку <code>current</code>
     * если движение невозможно
     * @param current текущая точка <code>Dot</code>
     * @param jump тип изменения положения <code>Jump</code>
     * @param number количество перемещений
     * @return новая точка <code>Dot</code> после перемещения или
     * текущая точка <code>current</code> если движение невозможно
     */
    public Dot nextMove(Dot current, Jump jump, int number) {
        int x = current.getCircle() + number * jump.getCircle();
        int y = current.getSector() + number * jump.getSector();
        if (y > 16) {
            y = y - 16;
        }
        if ((x < 0) && (x > 10)) {
            return current;
        } else if (new Dot(x, y).equals(tree)) {
            return current;
        }
        return new Dot(x, y);
    }

    /**
     * Метод проходит по всем возможным комбинациям <code>iIt[]</code>
     * доступных ходов <code>jumps</code>. Если комбинация передвижений
     * достигает конечного сектора, то проверяется чтобы количество шагов
     * было минимально.
     * @param item индекс проверяемого варианта шага <code>jumps</code>
     */
    public void calcWithRecursion(int item) {
        for (int i = 0; i <= maxIt[item]; i++) {
            iIt[item] = i;
            if (item < n - 1) {
                calcWithRecursion(item + 1);
            } else {
                Dot curr = start;
                for (int j = 0; j < n; j++) {
                    curr = nextMove(curr, jumps[j], iIt[j]);
                }

                if (curr.equals(finish)) {
                    int sumMove = 0;
                    for (int j = 0; j < n; j++) {
                        sumMove += iIt[j];
                    }

                    if (bestNumberOfSteps == 0) {
                        bestNumberOfSteps = sumMove;
                        for (int j = 0; j < n; j++) {
                            bestAm[j] = iIt[j];
                        }
                    } else {
                        bestNumberOfSteps = sumMove;
                        for (int j = 0; j < n; j++) {
                            bestAm[j] = iIt[j];
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Frog();
    }

}

/**
 * Класс представляет сегмент на круге.
 * Круг разбит на 160 сегментов, 16 секторов и
 * 10 колец. В конструкторе первый параметр -
 * номер окружности,
 * второй параметр - номер сектора.
 *
 * @author  Sergey Mikhailov
 * @since   1.0
 */
class Dot {
    private int circle;
    private int sector;

    public Dot(int circle, int sector) {
        this.circle = circle;
        this.sector = sector;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dot dot = (Dot) o;

        if (circle != dot.circle) return false;
        return sector == dot.sector;

    }

    @Override
    public int hashCode() {
        int result = circle;
        result = 31 * result + sector;
        return result;
    }
}

/**
 * Класс представляет вариант изменения
 * сегмента на круге. Значение изменения окружности
 * может быть отрицательным, что означает о уменьшение
 * номера окружности на заданное число (движение к центру)
 *
 * @author  Sergey Mikhailov
 * @since   1.0
 */
class Jump {
    private int circle;
    private int sector;

    public Jump(int circle, int sector) {
        this.circle = circle;
        this.sector = sector;
    }

    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        String str = "Прыжок на ";
        if (circle == 0) {
            str += sector + " сектора вперед";
        } else if (circle < 0) {
            str += Math.abs(circle) + " кольца ближе к центру и на " + sector + " сектора вперед";
        } else if (circle > 0) {
            str += circle + " кольца дальше от центра и на " + sector + " сектора вперед";
        }
        return str;
    }
}
