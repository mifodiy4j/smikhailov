package ru.job4j;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Elevator {

    private final int countFloors;
    private final double heightFloor;
    private final double speed;
    private final int timeBeforeOpeningAndClosing;

    private int currentFloor = 1;

    Queue<Floor> floorPQ;

    public Elevator(int countFloors, float heightFloor, float speed, int timeBeforeOpeningAndClosing) {
        this.countFloors = countFloors;
        this.heightFloor = heightFloor;
        this.speed = speed;
        this.timeBeforeOpeningAndClosing = timeBeforeOpeningAndClosing;

        floorPQ = new PriorityBlockingQueue<>(countFloors, new FloorComparator());

        System.out.printf("кол-во этажей в подъезде : %d %n", this.countFloors);
        System.out.printf("высота одного этажа : %2.2f %n", this.heightFloor);
        System.out.printf("скорость лифта при движении в метрах в секунду : %2.2f %n", this.speed);
        System.out.printf("время между открытием и закрытием дверей : %d %n", this.timeBeforeOpeningAndClosing);
    }

    public void readButtonPress() {
        System.out.printf("Please, enter the floor%n");

        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.hasNext()) {
                String str = sc.next();
                checkPress(str);
            }
        }
    }

    public void checkPress(String str) {
        int n = Integer.parseInt(str);
        if (n <= countFloors) {
            int pr = 1;
            //move down
            if (currentFloor > n) {
                for (int i = currentFloor - 1; i >= n; i--) {
                    addInQueue(i, pr++);
                }
            } else { //move up
                for (int i = currentFloor + 1; i <= n; i++) {
                    addInQueue(i, pr++);
                }
            }
        } else {
            System.out.printf("Please, enter again %n");
        }
    }

    public void addInQueue(int n, int pr) {

        if (!containsInQueue(n)) {
            floorPQ.add(new Floor(n, pr));
        }

    }

    public boolean containsInQueue(int n) {
        boolean status = false;
        for (Floor f : floorPQ) {
            if (f.getNumber() == n) {
                status = true;
            }
        }
        return status;
    }

    public void moveElevator() {

        if (!floorPQ.isEmpty()) {

            System.out.printf("Current floor = %d %n", currentFloor);
            try {
                TimeUnit.SECONDS.sleep((long) (heightFloor / speed));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Floor nextFloor = floorPQ.remove();
            currentFloor = nextFloor.getNumber();

            if (floorPQ.isEmpty()) {
                closeOpenDoor();
            }
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
                Float.parseFloat(args[1]),
                Float.parseFloat(args[2]),
                Integer.parseInt(args[3])
        );
        //Elevator elevator = new Elevator(10, 3, 1, 5);

        Thread elevatorThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    elevator.moveElevator();
                }
            }
        };

        Thread userThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    elevator.readButtonPress();
                }
            }
        };

        elevatorThread.start();
        userThread.start();
    }
}
