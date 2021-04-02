package ru.geekbrains.lesson6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public ChatClient() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Client: " + address);
            Socket socket = new Socket(address, 8000);

            Thread serverListener = new Thread(new SocketListener(socket));
            serverListener.start();

            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Please enter message..");
                while (true) {
                    String message = scanner.nextLine();

                    if (message.equalsIgnoreCase("quit")) {
                        serverListener.interrupt();
                        break;
                    }

                    out.writeUTF(message);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
