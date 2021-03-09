package ru.geekbrains.lesson1;

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
