package ru.geekbrains.lesson7.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public ChatClient(String name) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, 8000);
            System.out.println("Connected to chat..");
            System.out.println("Please enter auth message: -auth login password");

            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 Scanner scanner = new Scanner(System.in)) {

                new Thread(() -> {
                    try {
                        while (true) {
                            String messageFromServer = in.readUTF();
                            System.out.println(messageFromServer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();

                while (true) {
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("quit")) {
                        break;
                    }
                    out.writeUTF(String.format("%s:> %s", name, message));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
