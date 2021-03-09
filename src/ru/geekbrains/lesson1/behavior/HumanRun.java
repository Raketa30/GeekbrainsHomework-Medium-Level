package ru.geekbrains.lesson1.behavior;

public class HumanRun implements RunBehavior {
    @Override
    public int run(int length) {
        return length;
    }
}
