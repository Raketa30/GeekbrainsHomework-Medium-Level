package ru.geekbrains.lesson7.server.exceptions;

public class ChatServerException extends RuntimeException {

    public ChatServerException(String message) {
        super(message);
    }

    public ChatServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
