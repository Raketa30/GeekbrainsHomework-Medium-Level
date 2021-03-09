package ru.geekbrains.lesson1.entity;

import ru.geekbrains.lesson1.behavior.HumanJump;
import ru.geekbrains.lesson1.behavior.HumanRun;

public class Human extends Entity {
    private final String name;

    public Human(String name) {
        this.name = name;
        setRunBehavior(new HumanRun());
        setJumpBehavior(new HumanJump());
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                " power=" + getPower() +
                '}';
    }
}

