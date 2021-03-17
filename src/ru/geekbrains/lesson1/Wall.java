package ru.geekbrains.lesson1;

import java.util.Random;

public class Wall implements Overcome {

    private final double height;

    public Wall() {
        Random random = new Random();
        int r = random.nextInt(2);

        if (r == 0) {
            height = 0.5;
        } else {
            height = 1.0;
        }
    }

    @Override
    public String toString() {
        return "Wall{" +
                "height=" + height +
                '}';
    }

    @Override
    public void doAction(ActionUnit unit) {
        unit.performJump(height);
    }

    @Override
    public void getInfo() {
        System.out.println("Wall " + height + " m");
    }
}
