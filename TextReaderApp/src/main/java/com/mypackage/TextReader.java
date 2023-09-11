package com.mypackage;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextReader {

    private static final int IDLE = 0;
    private static final int WALKAROUND_START = 1;
    private static final int WALKAROUND_STEP_1 = 2;
    private static final int WALKAROUND_STEP_2 = 3;
    private static final int WALKAROUND_STEP_3 = 4;
    private static final int WALKAROUND_STEP_4 = 5;
    private static final int WALKAROUND_STEP_5 = 6;
    private static final int WALKAROUND_STEP_6 = 7;
    private static final int WALKAROUND_STEP_7 = 8;
    private static final int WALKAROUND_STEP_8 = 9;
    private static final int WALKAROUND_STEP_9 = 10;
    private static final int WALKAROUND_STEP_10 = 11;
    private static final int WALKAROUND_STEP_11 = 12;
    private static final int WALKAROUND_STEP_12 = 13;
    private static final int WALKAROUND_STEP_13 = 14;
    private static final int WALKAROUND_STEP_14 = 15;
    private static final int WALKAROUND_STEP_15 = 16;
    private static final int WALKAROUND_STEP_16 = 17;
    private static final int WALKAROUND_STEP_17 = 18;
    private static final int WALKAROUND_STEP_18 = 19;
    private static final int WALKAROUND_STEP_19 = 20;
    private static final int WALKAROUND_STEP_20 = 21;
    private static final int WALKAROUND_STEP_21 = 22;
    private static final int WALKAROUND_STEP_22 = 23;
    private static final int WALKAROUND_STEP_23 = 24;
    private static final int WALKAROUND_STEP_24 = 25;
    private static final int WALKAROUND_STEP_25 = 26;
    private static final int WALKAROUND_STEP_26 = 27;
    private static final int WALKAROUND_STEP_27 = 28;
    private static final int WALKAROUND_STEP_28 = 29;
    private static final int WALKAROUND_STEP_29 = 30;
    private static final int WALKAROUND_COMPLETE = 31;

    private static int currentState = IDLE;
    private static String[] currentChecklist;
    private static int checklistIndex = 0;

    private static void sleepFor(int milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    private static void speakAndDisplayMessage(JTextPane textPane, String message, Color color) {
        String timeStamp = new SimpleDateFormat("hh:mm:ss a").format(new Date());
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();
        StyleConstants.setForeground(set, color);
        try {
            doc.insertString(doc.getLength(), timeStamp + " - " + message + "\n", set);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (color.equals(Color.MAGENTA) || color.equals(Color.BLACK)) {
            try {
                Runtime.getRuntime().exec(new String[]{"say", "-v", "Daniel", message});
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static void processKeyStroke(int keyCode, JTextPane textPane) {
        /*if (currentState == BUTTON_PRESSED) {
            if (keyCode == KeyEvent.VK_ENTER) {
                currentState = IN_CHECKLIST;
                speakAndDisplayMessage(textPane, currentChecklist[checklistIndex], Color.RED);
                Color darkGreen = new Color(0, 100, 0);
                speakAndDisplayMessage(textPane, "ENTER - Confirm", darkGreen);
                speakAndDisplayMessage(textPane, "1 - Option A", darkGreen);
                speakAndDisplayMessage(textPane, "2 - Option B", darkGreen);
                speakAndDisplayMessage(textPane, "3 - EXIT", darkGreen);
            } else if (keyCode == KeyEvent.VK_1) {
                currentState = IDLE;
            }
        } else if (currentState == IN_CHECKLIST) {
            if (keyCode == KeyEvent.VK_ENTER) {
                checklistIndex++;
                if (checklistIndex < currentChecklist.length) {
                    speakAndDisplayMessage(textPane, currentChecklist[checklistIndex], Color.RED);
                } else {
                    currentState = IDLE;
                    checklistIndex = 0;
                }
            } else if (keyCode == KeyEvent.VK_3) {
                currentState = IDLE;
                checklistIndex = 0;
            }
        }
    */}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Reader");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JSplitPane splitPane = new JSplitPane();
        frame.add(splitPane, BorderLayout.CENTER);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        splitPane.setLeftComponent(scrollPane);

        JTextField textField = new JTextField();
        textField.setColumns(20);
        frame.add(textField, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 5, 5, 5));
        String[][] allButtons = {
           {"Walkaround", "Pre-Power Checklist", "Cockpit Prep Checklist", "Pre-Boarding", "MCDU: Init A", "EFB", "MCDU: Flight Plan", "Flight Director", "MCDU: Init B", "MCDU: Radnav", "MCDU: Perf", "IFR Clearance"},
            {"Pushback & Engines", "Taxi", "Pre-Takeoff", "Takeoff"},
            {"After Takeoff", "Cruise", "Descent Prep", "Descent"},
            {"Approach", "Landing"},
            {"After Landing Checklist", "Taxi", "Parking", "Shutdown"}
        };

        // Determine max rows
        int maxRows = 0;
        for (String[] buttonGroup : allButtons) {
            maxRows = Math.max(maxRows, buttonGroup.length);
        }

        // Add headers
        String[] headers = {"Flight Prep", "Taxi", "Flight", "Landing", "After Landing"};
        for (String header : headers) {
            JLabel label = new JLabel(header, SwingConstants.CENTER);
            buttonPanel.add(label);
        }

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < 5; col++) {
                if (row < allButtons[col].length) {
                    JButton button = new JButton(allButtons[col][row]);
                    buttonPanel.add(button);
                    button.addActionListener(e -> {
                        // Example checklist for Walkaround. You can customize it as per your need.
                        if ("Walkaround".equals(e.getActionCommand())) {
                            currentState = WALKAROUND_START;
                            speakAndDisplayMessage(textPane, "Let's begin the walk around.", Color.MAGENTA);
                            Color darkGreen = new Color(0, 100, 0);
                            speakAndDisplayMessage(textPane, "A - Begin", darkGreen);
                            speakAndDisplayMessage(textPane, "B - Exit", Color.RED);

                            
                        }
                        else if ("Pre-Power Checklist".equals(e.getActionCommand())) {
                            currentChecklist = new String[]{"Step 1 of Walkaround", "Step 2 of Walkaround"};
                            speakAndDisplayMessage(textPane, "Let's begin the walk around.", Color.RED);
                            Color darkGreen = new Color(0, 100, 0);
                            speakAndDisplayMessage(textPane, "ENTER - BOO", darkGreen);
                            speakAndDisplayMessage(textPane, "1 - Exit", darkGreen);
                        }
                        // Add similar logic for other buttons here.
                    });
                } else {
                    buttonPanel.add(new JLabel());
                }
            }
        }

        JPanel newButtonRowPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        String[] newButtons = {"A", "B", "C", "D"};

        for (String label : newButtons) {
            JButton button = new JButton(label);
            newButtonRowPanel.add(button);
            button.addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                switch (actionCommand) {
                    case "A":
                        // Code for button A
                        switch (currentState) {
    case WALKAROUND_START:
        currentState = WALKAROUND_STEP_1;
        speakAndDisplayMessage(textPane, "Nose tyres.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_1:
        currentState = WALKAROUND_STEP_2;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Nose struts.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_2:
        currentState = WALKAROUND_STEP_3;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Nose pitot tubes.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_3:
        currentState = WALKAROUND_STEP_4;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Nose static ports.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_4:
        currentState = WALKAROUND_STEP_5;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left landing gear tyres.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_5:
        currentState = WALKAROUND_STEP_6;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left landing gear struts.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_6:
        currentState = WALKAROUND_STEP_7;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left landing gear brake wear indicators.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_7:
        currentState = WALKAROUND_STEP_8;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left wing leading/trailing edges.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_8:
        currentState = WALKAROUND_STEP_9;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left wing flaps.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_9:
        currentState = WALKAROUND_STEP_10;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left wing slats.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_10:
        currentState = WALKAROUND_STEP_11;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left wing fuel/vent ports.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_11:
        currentState = WALKAROUND_STEP_12;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left engine fan blades.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_12:
        currentState = WALKAROUND_STEP_13;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left engine intakes.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_13:
        currentState = WALKAROUND_STEP_14;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Left engine exhaust.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_14:
        currentState = WALKAROUND_STEP_15;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Tail horizontal stabilizers.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_15:
        currentState = WALKAROUND_STEP_16;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Tail vertical stabilizers.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_16:
        currentState = WALKAROUND_STEP_17;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Tail A.P.U. exhaust.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_17:
        currentState = WALKAROUND_STEP_18;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right engine fan blades.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_18:
        currentState = WALKAROUND_STEP_19;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right engine intakes.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_19:
        currentState = WALKAROUND_STEP_20;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right engine exhaust.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_20:
        currentState = WALKAROUND_STEP_21;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right wing leading and trailing edges.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_21:
        currentState = WALKAROUND_STEP_22;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right wing flaps.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_22:
        currentState = WALKAROUND_STEP_23;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right wing slats.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_23:
        currentState = WALKAROUND_STEP_24;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right wing fuel/vent ports.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_24:
        currentState = WALKAROUND_STEP_25;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right landing gear tyres.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_25:
        currentState = WALKAROUND_STEP_26;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right landing gear struts.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_26:
        currentState = WALKAROUND_STEP_27;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Right landing gear brake wear indicators.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_27:
        currentState = WALKAROUND_STEP_28;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Front fuselage windows.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_28:
        currentState = WALKAROUND_STEP_29;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Front fuselage doors.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_STEP_29:
        currentState = WALKAROUND_COMPLETE;
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Front fuselage emergency exits.", Color.MAGENTA);
        speakAndDisplayMessage(textPane, "A - Check", Color.BLUE);
        speakAndDisplayMessage(textPane, "B - Exit", Color.RED);
        break;
    case WALKAROUND_COMPLETE:
        currentState = IDLE; // Final state or return to start
        speakAndDisplayMessage(textPane, "Check.", Color.BLACK);
        sleepFor(2000);
        speakAndDisplayMessage(textPane, "Walkaround Complete.", Color.MAGENTA);
        break;
                        }
                        break;
                    case "B":
                        // Code for button B
                        speakAndDisplayMessage(textPane, "Button B Pressed", Color.MAGENTA);
                        break;
                    case "C":
                        // Code for button C
                        speakAndDisplayMessage(textPane, "Button C Pressed", Color.MAGENTA);
                        break;
                    case "D":
                        // Code for button D
                        speakAndDisplayMessage(textPane, "Button D Pressed", Color.MAGENTA);
                        break;
                }
            });
        }

        JPanel combinedButtonPanel = new JPanel(new BorderLayout());
        combinedButtonPanel.add(buttonPanel, BorderLayout.CENTER);
        combinedButtonPanel.add(newButtonRowPanel, BorderLayout.SOUTH);

        splitPane.setRightComponent(combinedButtonPanel);
        splitPane.setDividerLocation(300);

        textField.addActionListener(e -> {
            String message = textField.getText();
            speakAndDisplayMessage(textPane, message, Color.BLACK);
            textField.setText("");
        });

        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                processKeyStroke(e.getKeyCode(), textPane);
            }
        });

        frame.setVisible(true);
    }
}
