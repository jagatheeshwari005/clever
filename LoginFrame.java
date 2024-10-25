package com.todolist;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class LoginFrame extends Frame implements ActionListener {
    private TextField usernameField, passwordField;
    private Button loginButton, backButton;
    private Image backgroundImage; // Background image variable

    public LoginFrame() {
        setTitle("Login");
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

        loginButton = new Button("Login");
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

        setButtonStyles(loginButton);
        setButtonStyles(backButton);

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        // Add action listeners
        loginButton.addActionListener(this);
        backButton.addActionListener(e -> {
            dispose(); // Close LoginFrame
            new Home(); // Return to Home page
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

        // Check if the username and password match the saved credentials
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!username.equals(SignupFrame.savedUsername)) {
            JOptionPane.showMessageDialog(this,
                    "Incorrect username. Please try again.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
        } else if (!password.equals(SignupFrame.savedPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Incorrect password. Please try again.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Close the current frame
            dispose();

            // Return to the Home page
            new Home(); // Opens the home frame after login
        }
    }
}
