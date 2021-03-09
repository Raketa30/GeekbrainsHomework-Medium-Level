package ru.geekbrains.lesson1.behavior;

public class CatRun implements RunBehavior {
    @Override
    public int run(int length) {
        return length / 5;
    }
}
