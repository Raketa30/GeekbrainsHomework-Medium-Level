package ru.geekbrains.lesson1;

import ru.geekbrains.lesson1.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
    private final List<Object> barriers;

    public Course(int size) {
        barriers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                barriers.add(new Track());
            } else {
                barriers.add(new Wall());
            }
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "lets=" + barriers +
                '}';
    }

    public void dolt(Team team) throws InterruptedException {
        for (Entity entity : team.getTeam()) {
            entity.display();
            for (Object barrier : barriers) {
                if (barrier instanceof Wall) {
                    System.out.print("In forward ");
                    ((Wall) barrier).getWallInfo();

                    if (!entity.performJump(((Wall) barrier).getHeight())) {
                        break;
                    }
                    Thread.sleep(100);

                } else if (barrier instanceof Track) {
                    ((Track) barrier).getTrackInfo();

                    if (!entity.performRun(((Track) barrier).getLength())) {
                        break;
                    }
                }
            }

            entity.display();
            if (!entity.isTired()) {
                team.getWinners().add(entity);
                System.out.println("FINISH!!");
            }
            System.out.println();
        }
    }

    static class Track {
        private final int length;

        public Track() {
            length = (int) (Math.random() * 10 + 5);
        }

        public int getLength() {
            return length;
        }

        public void getTrackInfo() {
            System.out.println("Next track " + length + " m");
        }

        @Override
        public String toString() {
            return "Track{" +
                    "length=" + length +
                    '}';
        }
    }

     static class Wall{
        private final double height;

        public Wall() {
            Random random = new Random();
            int r = random.nextInt(2);

            if (r == 0) {
                height = 0.5;
            } else {
                height = 1.0;
            }
        }

        public double getHeight() {
            return height;
        }

        public void getWallInfo() {
            System.out.println("Wall " + height + " m");
        }

        @Override
        public String toString() {
            return "Wall{" +
                    "height=" + height +
                    '}';
        }
    }


}
