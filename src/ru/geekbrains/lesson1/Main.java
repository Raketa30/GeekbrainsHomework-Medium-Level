package ru.geekbrains.lesson1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Team team = new Team(4);
        Course course = new Course(10);
        System.out.println(course);
        course.dolt(team);



        System.out.println(team);
    }
}
