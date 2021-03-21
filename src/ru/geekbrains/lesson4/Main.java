package ru.geekbrains.lesson4;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        doTaskOne();

        //2
        Set<String> words = new HashSet<>();
        words.add("One");
        words.add("Two");
        words.add("Three");

        Consumer<String> stringConsumer = System.out::println;
        forItem(words, stringConsumer);

        //3
        Supplier<Integer> integerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(5);
        };

        System.out.println(doubleUp(4, integerSupplier));

        //4
        String x = "76cxcnsbvnsmcnddcc";

        Optional<String> opt = findAllChars(x, 'c');

        opt.ifPresent(System.out::println);
    }

    /**
     * Создать коллекцию типа List. Наполнить ее значениями и вывести значения в консоль
     * при помощи ее метода forEach.
     */
    public static void doTaskOne() {
        List<String> words = new ArrayList<>();
        words.add("One");
        words.add("Two");
        words.add("Three");

        words.forEach(System.out::println);
    }

    /**
     * Создать утилитарный метод forItem. Метод принимает два параметра:
     * Коллекция Set<String> и консьюмер типа Consumer<String>. Внутри метода проитерироваться по коллекции
     * и для каждого элемента выполнить метод консьюмера accept, который выводит значение элемента в консоль.
     */
    public static void forItem(Set<String> set, Consumer<String> cons) {
        set.forEach(cons);
    }

    /**
     * Создать утилитарный метод doubleUp. Метод принимает два параметра: значение типа int
     * и консьюмер типа Supplier<Integer>. Внутри метода выполнить метод саплаера get, который
     * возвращает множитель и затем переданное значение на него умножается.
     * Фукнция возращает результат произведения.
     */
    public static int doubleUp(int index, Supplier<Integer> supplier) {
        return supplier.get() * index;
    }

    /**
     * Создать метод findAllChars. Метод принимает два параметра: String target и char toFind.
     * Первый параметр является входной строкой, а второй - символ, который необходимо найти в
     * входящей строке. Учесть что искомый символ может повторяется (напр.: 'ccch'). Необходимо
     * найти все повторения и вернуть в виде конкатенированной строки обвернутый в Optional.
     * Если ни одного совпадения не найдено, тогда необходимо вернуть пустой Optional.
     * Пример выполнения: Optional<String> opt = findAllChars("ccch", 'c'); opt.get(); // вернет "ссс".
     */

    public static Optional<String> findAllChars(String target, char toFind) {
        return Optional.of(
                target.chars()
                .filter(ch -> ch == toFind)
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.joining(""))
        );
    }
}


