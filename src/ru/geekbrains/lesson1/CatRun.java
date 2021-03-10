package ru.geekbrains.lesson1;

public class CatRun implements RunBehavior {
    @Override
    public int run(double length) {
        return (int)length / 5;
    }
}
