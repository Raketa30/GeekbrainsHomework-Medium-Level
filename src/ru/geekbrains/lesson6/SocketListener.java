package ru.geekbrains.lesson6;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketListener implements Runnable {
    private final Socket socket;
    private final Thread.UncaughtExceptionHandler handler;

    public SocketListener(Socket socket) {
        this.socket = socket;
        this.handler = (t, e) -> {
            System.out.println(t.getName());
            e.printStackTrace();
        };
    }

    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream())) {

            while (true) {
                String inputMessage = inputStream.readUTF();
                System.out.print(socket.getInetAddress().toString() + "> ");
                System.out.println(inputMessage);
            }

        } catch (IOException e) {
            handler.uncaughtException(Thread.currentThread(), e);
        }
    }
}
