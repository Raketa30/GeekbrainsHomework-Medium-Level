package ru.geekbrains.lesson1;

public class Cat extends ActionUnit {
    private final String name;

    public Cat(String name) {
        this.name = name;
        setRunBehavior(new CatRun());
        setJumpBehavior(new CatJump());
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                " power=" + getPower() +
                '}';
    }
}
