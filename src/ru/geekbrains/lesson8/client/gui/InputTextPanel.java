package ru.geekbrains.lesson8.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputTextPanel extends JPanel {

    public InputTextPanel(JTextArea chatArea) {
        JTextField textField = new JTextField();
        JButton button = new JButton("Enter");

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(490, 40));

        button.setPreferredSize(new Dimension(50, 40));

        button.addActionListener(e -> {
            if (!textField.getText().equals("")) {
                chatArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && !textField.getText().equals("")) {
                    chatArea.append(textField.getText() + "\n");
                    textField.setText("");
                }
            }
        });

        add(textField, BorderLayout.CENTER);
        add(button, BorderLayout.EAST);
    }

}
