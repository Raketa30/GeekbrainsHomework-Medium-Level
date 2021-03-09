package ru.geekbrains.lesson1;

public class RobotRun implements RunBehavior {
    @Override
    public int run(int length) {
        return length * 2;
    }
}
