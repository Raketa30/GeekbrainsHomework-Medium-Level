package ru.geekbrains.lesson1;

public class HumanJump implements JumpBehavior {
    @Override
    public int jump(double height) {
        return (int) (height * 10);
    }
}
