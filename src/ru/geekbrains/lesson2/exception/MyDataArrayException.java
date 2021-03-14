package ru.geekbrains.lesson2.exception;

public class MyDataArrayException extends RuntimeException {

    public MyDataArrayException() {
    }

    public MyDataArrayException(String message) {
        super(message);
    }

    public MyDataArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
