package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.exception.MyDataArrayException;
import ru.geekbrains.lesson2.exception.MySizeArrayException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String[][]> list;

    static {
        list = new ArrayList<>();
        String[][] correctNumbers = {
                {"23", "42", "12", "23"},
                {"444", "543", "33", "1"},
                {"23", "234", "21", "43"},
                {"33", "3", "2", "33"}
        };
        String[][] numbersWithWrongData = {
                {"23", "42", "12", "23"},
                {"444", "543", "33", "1"},
                {"23", "234", "21", "43"},
                {"33", "3", "2", "a"}
        };
        String[][] numbersWithWrongSize = {
                {"23", "42", "12", "23"},
                {"444", "543", "33", "1"},
                {"23", "234", "21", "43"},
                {"33", "3", "2"}
        };

        list.add(correctNumbers);
        list.add(numbersWithWrongData);
        list.add(numbersWithWrongSize);
    }

    public static void main(String[] args) throws InterruptedException {
        Custom custom = new Custom(4, 4);

        for(String[][] s : list) {
            try {
                System.out.println(custom.count(s));

            } catch (MySizeArrayException | MyDataArrayException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(300); //
        }
    }
}
