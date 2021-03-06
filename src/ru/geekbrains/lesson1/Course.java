package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final List<Overcome> barriers;

    public Course(int size) {
        barriers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                barriers.add(new Track());
            } else {
                barriers.add(new Wall());
            }
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "lets=" + barriers +
                '}';
    }

    public void dolt(Team team) throws InterruptedException {
        for (ActionUnit actionUnit : team.getTeam()) {
            actionUnit.display();

            for (Overcome barrier : barriers) {
                if (!actionUnit.isTired()) {
                    System.out.print("In forward ");
                    barrier.getInfo();
                    barrier.doAction(actionUnit);
                }

                Thread.sleep(100);
            }

            actionUnit.display();
            if (!actionUnit.isTired()) {

                team.getWinners().add(actionUnit);
                System.out.println("FINISH!!");
            }
            System.out.println();
        }
    }
}
