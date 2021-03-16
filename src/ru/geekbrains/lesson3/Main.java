package ru.geekbrains.lesson3;

import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать сколько раз встречается каждое слово.
 */

public class Main {
    private static final String[] wordsArray = {"Apple", "Grapefruit", "Mango", "Watermelon",
            "Cherry", "Tomato", "Apple", "Potato", "Lemon", "Cherry", "Sweet cherry", "Orange", "Grapefruit", "Apple", "Mango"};

    public static void main(String[] args) {

        showUniqWords(wordsArray);
        System.out.println();
        uniqWordsCounter(wordsArray);
        System.out.println();
        showUniqWordsSorted(wordsArray);
        System.out.println();

        PhoneBook book = new PhoneBook();

        book.add("+8-800-555-35-35", "Igor");
        book.add("+8-800-322-35-35", "Jeka");
        book.add("+8-800-322-35-35", "Uriy");
        book.add("+2-800-222-35-35", "Jeka");
        book.add("+2-800-222-35-35", "Vadim");
        book.add("+2-800-222-35-3", "Vadim А");
        book.add("+2-800-555-35-35", "Vitaliy");
        System.out.println();

        book.get("Jeka");
        System.out.println();
        book.get("Vadim");
    }

    public static void showUniqWords(String[] words) {
        Set<String> uniqWords = new HashSet<>(Arrays.asList(words));

        for (String s : uniqWords) {
            System.out.println(s);
        }
    }

    public static void showUniqWordsSorted(String[] words) {
        Set<String> uniqWords = new TreeSet<>(Arrays.asList(words));

        for (String s : uniqWords) {
            System.out.println(s);
        }
    }

    public static void uniqWordsCounter(String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();

        for (String word : words) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            System.out.printf("%s -- %d\n", entry.getKey(), entry.getValue());
        }
    }


}
