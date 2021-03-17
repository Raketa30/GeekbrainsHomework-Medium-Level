package ru.geekbrains.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    private final List<ActionUnit> team;
    private final List<ActionUnit> winners;

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

    public List<ActionUnit> getTeam() {
        return team;
    }

    public List<ActionUnit> getWinners() {
        return winners;
    }

}
