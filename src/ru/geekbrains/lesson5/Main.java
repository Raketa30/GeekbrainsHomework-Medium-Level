package ru.geekbrains.lesson5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int size = 10_000_000;
        float[] first = doWithoutThreads(size);
        float[] second = doWithThreads(size);

        System.out.println(Arrays.equals(first, second));
    }

    public static float[] doWithoutThreads(int size) {
        float[] arr = createAndFillArr(size);

        long start = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (float) (i / 5))
                    * Math.cos(0.2f + (float) (i / 5)) * Math.cos(0.4f + (float) (i / 5)));
        }

        long end = System.currentTimeMillis();

        System.out.println("first method : " + (end - start));

        return arr;
    }

    public static float[] doWithThreads(int size) throws InterruptedException {
        float[] arr = createAndFillArr(size);

        int h = size / 2;

        long start = System.currentTimeMillis();

        Thread firstThread = new Thread(() -> {
            float[] firstPart = Arrays.copyOfRange(arr, 0, h);

            for (int i = 0; i < firstPart.length; i++) {
                firstPart[i] = (float) (firstPart[i] * Math.sin(0.2f + (float) (i / 5))
                        * Math.cos(0.2f + (float) (i / 5)) * Math.cos(0.4f + (float) (i / 5)));
            }
            System.arraycopy(firstPart, 0, arr, 0, h);
        });
        firstThread.start();

        Thread secondThread = new Thread(() -> {
            float[] secondPart = Arrays.copyOfRange(arr, h, size);

            for (int i = 0; i < secondPart.length; i++) {
                secondPart[i] = (float) (secondPart[i] * Math.sin(0.2f + (float) ((i + h) / 5))
                        * Math.cos(0.2f + (float) ((i + h) / 5)) * Math.cos(0.4f + (float) ((i + h) / 5)));
            }

            System.arraycopy(secondPart, 0, arr, h, h);
        });
        secondThread.start();

        firstThread.join();  //?? актуально?
        secondThread.join();

        long end = System.currentTimeMillis();

        System.out.println("second method : " + (end - start));

        return arr;
    }

    private static float[] createAndFillArr(int size) {
        float[] arr = new float[size];
        Arrays.fill(arr, 1f);

        return arr;
    }
}
