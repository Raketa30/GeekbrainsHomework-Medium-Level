package ru.geekbrains.lesson1;

public abstract class ActionUnit {
    private RunBehavior runBehavior;
    private JumpBehavior jumpBehavior;
    private boolean isTired = false;
    private int power;

    public ActionUnit() {
        this.power = 100;
    }

    public void setRunBehavior(RunBehavior runBehavior) {
        this.runBehavior = runBehavior;
    }

    public void setJumpBehavior(JumpBehavior jumpBehavior) {
        this.jumpBehavior = jumpBehavior;
    }

    public void performRun(double length) {
        int action = runBehavior.run(length);
        if (power >= action) {
            System.out.println("run " + length + " meters!!");
            power -= action;

        } else {
            isTired = true;
            System.out.println("Tired, cannot run!");
        }
    }

    public void performJump(double height) {
        int action = jumpBehavior.jump(height);
        if (power >= action) {
            System.out.println("What a jump! Jumped " + height + " meters wall");
            power -= action;

        } else {
            System.out.println("Tired, cannot jump!");
            isTired = true;
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
