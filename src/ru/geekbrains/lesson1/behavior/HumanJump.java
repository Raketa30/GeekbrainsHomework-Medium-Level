package ru.geekbrains.lesson1.behavior;

public class HumanJump implements JumpBehavior {
    @Override
    public int jump(double height) {
        return (int) (height * 10);
    }
}
