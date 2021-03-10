package ru.geekbrains.lesson1;

public class HumanRun implements RunBehavior {
    @Override
    public int run(double length) {
        return (int)length;
    }
}
