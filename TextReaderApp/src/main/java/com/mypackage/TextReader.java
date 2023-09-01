package com.mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextReader {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Reader");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        textField.setColumns(20);
        frame.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeStamp = new SimpleDateFormat("hh:mm:ss a").format(new Date());
                String message = textField.getText();
                textArea.append(timeStamp + " - " + message + "\n");

                // Auto-scroll to the latest message
                textArea.setCaretPosition(textArea.getDocument().getLength());

                try {
                    Runtime.getRuntime().exec(new String[]{"say", "-v", "Daniel", message});
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                textField.setText("");
            }
        });

        frame.setVisible(true);
    }
}