package ru.geekbrains.lesson1;

public class CatJump implements JumpBehavior {
    @Override
    public int jump(double height) {
        return (int) (height * 5);
    }
}
