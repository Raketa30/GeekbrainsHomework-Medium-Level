package ru.geekbrains.lesson3;

import java.util.HashMap;
import java.util.Map;

/**
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 */

public class PhoneBook {
    Map<String, String> phoneBookMap;

    public PhoneBook() {
        this.phoneBookMap = new HashMap<>();

    }

    public PhoneBook(Map<String, String> phoneBookMap) {
        this.phoneBookMap = phoneBookMap;
    }

    public boolean add(String number, String name) {
        if (phoneBookMap.containsKey(number)) {
            System.out.println("Phone book contains this number -> " + number);
            return false;
        }
        if (!regexPhoneNumber(number)) {
            System.out.println("Wrong number format, please use - > +X-XXX-XXX-XX-XX");
            return false;
        }

        phoneBookMap.put(number, name);
        System.out.println(number + " " + name + ", added to phone book");
        return true;

    }

    public boolean get(String name) {
        if (phoneBookMap.containsValue(name)) {
            for (Map.Entry<String, String> entry : phoneBookMap.entrySet()) {
                if (entry.getValue().equals(name)) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
            return true;
        }

        System.out.println("Nothing for this request");
        return false;
    }

    private boolean regexPhoneNumber(String number) {
        String regex = "\\+\\d[-]\\d{3}[-]\\d{3}[-]\\d{2}[-]\\d{2}";
        return number.matches(regex);
    }
}
