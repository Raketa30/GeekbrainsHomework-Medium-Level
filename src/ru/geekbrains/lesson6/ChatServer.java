package ru.geekbrains.lesson6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public ChatServer() {

        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            System.out.println("Server connected..");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected..");
            Thread chatListener = new Thread(new SocketListener(clientSocket));
            chatListener.start();

            try (DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Please enter message..");
                while (true) {
                    String message = scanner.nextLine();

                    if (message.equalsIgnoreCase("quit")) {
                        chatListener.interrupt();
                        break;
                    }

                    out.writeUTF(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
