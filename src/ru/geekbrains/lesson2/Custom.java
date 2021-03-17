package ru.geekbrains.lesson2;

import ru.geekbrains.lesson2.exception.MyDataArrayException;
import ru.geekbrains.lesson2.exception.MySizeArrayException;

public class Custom {
    private final int xAxis;
    private final int yAxis;

    public Custom(int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public int count(String[][] numbers) {
        if (!checkLength(numbers)) {
            throw new MySizeArrayException("Wrong length");
        }

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                try {
                    sum += Integer.parseInt(numbers[i][j]);

                } catch (NumberFormatException ex) {
                    throw new MyDataArrayException("Wrong format in cell -> [" + i + "][" + j + "]", ex);
                }
            }
        }
        return sum;
    }

    private boolean checkLength(String[][] numbers) {
        if (numbers.length != xAxis) {
            return false;
        }

        for (String[] s : numbers) {
            if (s.length != yAxis) {
                return false;
            }
        }

        return true;
    }

}
