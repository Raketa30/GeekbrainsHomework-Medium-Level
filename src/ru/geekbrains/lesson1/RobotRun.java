package ru.geekbrains.lesson1;

public class RobotRun implements RunBehavior {
    @Override
    public int run(double length) {
        return (int)length * 2;
    }
}
