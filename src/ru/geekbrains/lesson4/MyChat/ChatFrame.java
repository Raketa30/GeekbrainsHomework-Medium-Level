package ru.geekbrains.lesson4.MyChat;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLayout(new BorderLayout());


        JTextArea chatArea = new JTextArea();
        InputTextPanel textPanel = new InputTextPanel(chatArea);

        chatArea.setAutoscrolls(true);
        chatArea.setEditable(false);


        add(chatArea, BorderLayout.CENTER);
        add(textPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
