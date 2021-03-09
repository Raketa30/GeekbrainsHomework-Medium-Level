package ru.geekbrains.lesson1.entity;

import ru.geekbrains.lesson1.behavior.RobotJump;
import ru.geekbrains.lesson1.behavior.RobotRun;

public class Robot extends RunJumpEntity {
    private final int id;

    public Robot(int id) {
        this.id = id;
        setRunBehavior(new RobotRun());
        setJumpBehavior(new RobotJump());
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                " power=" + getPower() +
                '}';
    }
}
