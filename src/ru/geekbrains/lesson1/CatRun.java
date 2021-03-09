package ru.geekbrains.lesson1;

public class CatRun implements RunBehavior {
    @Override
    public int run(int length) {
        return length / 5;
    }
}
