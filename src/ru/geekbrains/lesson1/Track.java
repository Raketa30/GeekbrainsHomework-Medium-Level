package ru.geekbrains.lesson1;

public class Track implements Overcomable{
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
    public double doAction() {
        return length;
    }

    @Override
    public void getInfo() {
        System.out.println("Next track " + length + " m");
    }
}
