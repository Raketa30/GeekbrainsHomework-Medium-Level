package ru.geekbrains.lesson1;

public class Track implements Overcome {
    private final double length;

    public Track() {
        length = (int) (Math.random() * 10 + 5);
    }

    @Override
    public String toString() {
        return "Track{" +
                "length=" + length +
                '}';
    }

    @Override
    public void doAction(ActionUnit unit) {
        unit.performRun(length);
    }

    @Override
    public void getInfo() {
        System.out.println("Next track " + length + " m");
    }
}
