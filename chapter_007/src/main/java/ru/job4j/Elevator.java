package ru.job4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Elevator {

    private final int countFloors;
    private final double heightFloor;
    private final double speed;
    private final int timeBeforeOpeningAndClosing;

    private int currentFloor = 1;

    private Queue<Integer> queue = new LinkedList<>();

    public Elevator(int countFloors, double heightFloor, double speed, int timeBeforeOpeningAndClosing) {
        this.countFloors = countFloors;
        this.heightFloor = heightFloor;
        this.speed = speed;
        this.timeBeforeOpeningAndClosing = timeBeforeOpeningAndClosing;
        System.out.printf("кол-во этажей в подъезде : %d %n", this.countFloors);
        System.out.printf("высота одного этажа : %2.2f %n", this.heightFloor);
        System.out.printf("скорость лифта при движении в метрах в секунду : %2.2f %n", this.speed);
        System.out.printf("время между открытием и закрытием дверей : %d %n", this.timeBeforeOpeningAndClosing);
    }

    public void statusElevator() throws InterruptedException {

        if (!queue.isEmpty()) {
            int nextFloor = queue.remove();
            if (nextFloor > currentFloor) {
                nextFloorUp(nextFloor);
            } else if (nextFloor < currentFloor) {
                nextFloorDown(nextFloor);
            }

        }
    }

    public void nextFloorUp(int nFloor) {
        for (int i = currentFloor; i < nFloor; i++) {
            System.out.printf("Current floor = %d %n", currentFloor);
            try {
                TimeUnit.SECONDS.sleep((long) (heightFloor / speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentFloor++;
        }

        if (currentFloor == nFloor) {
            closeOpenDoor();
        }
    }

    public void nextFloorDown(int nFloor) {
        for (int i = currentFloor; i > nFloor; i--) {
            System.out.printf("Current floor = %d %n", currentFloor);
            try {
                TimeUnit.SECONDS.sleep((long) (heightFloor / speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentFloor--;
        }

        if (currentFloor == nFloor) {
            closeOpenDoor();
        }
    }

    public void closeOpenDoor() {
        System.out.printf("Current floor = %d %n", currentFloor);
        System.out.printf("Open door on %d floor%n", currentFloor);
        try {
            TimeUnit.SECONDS.sleep(timeBeforeOpeningAndClosing);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Close door on %d floor%n", currentFloor);
    }

    public static void main(String[] args) throws InterruptedException {

        Elevator elevator = new Elevator(
                Integer.parseInt(args[0]),
                Double.parseDouble(args[1]),
                Double.parseDouble(args[2]),
                Integer.parseInt(args[3])
        );
        //Elevator elevator = new Elevator(20, 3, 1, 5);

        System.out.printf("Please, enter the floor%n" +
                "\"<\" - enter in the cabin elevator%n" +
                "\">\" - enter in the floor%n");

        Scanner sc = new Scanner(System.in);

        Thread elevatorThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        elevator.statusElevator();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread userThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (sc.hasNext()) {
                        String str = sc.next();
                        if (str.startsWith("<")) {
                            addInQueue(str);
                        } else if (str.startsWith(">")) {
                            addInQueue(str);
                        } else {
                            System.out.printf("Please, enter again");
                        }
                    }
                }
            }

            public void addInQueue(String str) {
                int n = Integer.parseInt(str.substring(1));
                elevator.queue.add(n);
            }
        };

        elevatorThread.start();
        userThread.start();
    }
}
