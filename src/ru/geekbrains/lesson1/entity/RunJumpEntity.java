package ru.geekbrains.lesson1.entity;

import ru.geekbrains.lesson1.behavior.JumpBehavior;
import ru.geekbrains.lesson1.behavior.RunBehavior;

public abstract class RunJumpEntity {
    private RunBehavior runBehavior;
    private JumpBehavior jumpBehavior;
    private boolean isTired;
    private int power;

    public RunJumpEntity() {
        this.power = 100;
    }

    public void setRunBehavior(RunBehavior runBehavior) {
        this.runBehavior = runBehavior;
    }

    public void setJumpBehavior(JumpBehavior jumpBehavior) {
        this.jumpBehavior = jumpBehavior;
    }

    public boolean performRun(int length) {
        int action = runBehavior.run(length);
        if (power >= action) {
            System.out.println("run " + length + " meters!!");
            power -= action;
            return true;

        } else {
            isTired = true;
            System.out.println("Tired, cannot run!");
            return false;
        }
    }

    public boolean performJump(double height) {
        int action = jumpBehavior.jump(height);
        if (power >= action) {
            System.out.println("What a jump! Jumped " + height + " meters wall");
            power -= action;
            return true;

        } else {
            System.out.println("Tired, cannot jump!");
            isTired = true;
            return false;
        }
    }

    public abstract void display();

    public int getPower() {
        return power;
    }

    public boolean isTired() {
        return isTired;
    }
}
