package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.entity.Cat;
import ru.geekbrains.lesson1.entity.Entity;
import ru.geekbrains.lesson1.entity.Human;
import ru.geekbrains.lesson1.entity.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    private final List<Entity> team;
    private final List<Entity> winners;

    public Team(int size) {
        team = new ArrayList<>();
        winners = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int r = random.nextInt(3);
            if (r == 0) {
                team.add(new Human("Human" + i));
            } else if (r == 1) {
                team.add(new Robot(size + i));
            } else if (r == 2) {
                team.add(new Cat("Cat" + i));
            }
        }
    }

    public List<Entity> getTeam() {
        return team;
    }

    public List<Entity> getWinners() {
        return winners;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team=" + team +
                "\n" +
                ", winners=" + winners +
                '}';
    }
}
