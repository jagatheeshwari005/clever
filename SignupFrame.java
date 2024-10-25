package com.todolist;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SignupFrame extends Frame implements ActionListener {
    private TextField usernameField, passwordField;
    private Button submitButton, backButton;
    private Image backgroundImage; // Background image variable

    // Static fields to store the saved username and password
    public static String savedUsername;
    public static String savedPassword;

    public SignupFrame() {
        setTitle("Signup");
        setSize(300, 200);
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 248, 255)); // Background color

        // Load the background image with the correct file path
        try {
            backgroundImage = ImageIO.read(new File("D:\\Todolist\\src\\com\\todolist\\picture.jpg"));
        } catch (IOException e) {
            System.out.println("Background image not found.");
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        usernameField = new TextField(15);
        passwordField = new TextField(15);
        passwordField.setEchoChar('*'); // Hide password input

        submitButton = new Button("Submit");
        backButton = new Button("Back");

        // Add components to the layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        // Buttons panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 248, 255)); // Background color

        setButtonStyles(submitButton);
        setButtonStyles(backButton);

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        // Add action listeners
        submitButton.addActionListener(this);
        backButton.addActionListener(e -> {
            dispose(); // Close SignupFrame
            new Home(); // Return to Home
        });

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Override paint method to draw the background image
    @Override
    public void paint(Graphics g) {
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Repaint the components on top of the background
        super.paint(g);
    }

    // Set common button styles
    private void setButtonStyles(Button button) {
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setForeground(Color.WHITE); // White text
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold text
        button.setPreferredSize(new Dimension(100, 40)); // Set size for buttons
        button.setFocusable(false); // Remove focus rectangle
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate username and password conditions
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Signup Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Save the username and password
        savedUsername = username;
        savedPassword = password;

        JOptionPane.showMessageDialog(this,
                "Signup successful! You can now login.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

        dispose(); // Close SignupFrame
        new Home(); // Go back to Home
    }
}
