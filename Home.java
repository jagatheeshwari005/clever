package com.todolist;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Home extends Frame implements ActionListener {
    private Button signupButton, loginButton, taskManagerButton, exitButton;
    private Image backgroundImage;

    public Home() {
        setTitle("To-Do List Application");
        setSize(600, 400); // Adjust the size to fit the image properly
        setLayout(new GridBagLayout()); // Use GridBagLayout for better alignment
        setBackground(new Color(240, 248, 255)); // Background color

        // Load the background image with the correct file path
        try {
            backgroundImage = ImageIO.read(new File("D:\\Todolist\\src\\com\\todolist\\picture.jpg"));
        } catch (IOException e) {
            System.out.println("Background image not found.");
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Place components vertically

        signupButton = createButton("Signup");
        loginButton = createButton("Login");
        taskManagerButton = createButton("Task Manager");
        exitButton = createButton("Exit");

        add(signupButton, gbc);
        add(loginButton, gbc);
        add(taskManagerButton, gbc);
        add(exitButton, gbc);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Repaint the components on top of the background
        super.paint(g);
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(Color.WHITE); // Set button color to white
        button.setForeground(Color.BLACK); // Set text color to black
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold text
        button.setFocusable(false); // Remove focus rectangle
        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            new SignupFrame();  // Opens SignupFrame when "Signup" is clicked
        } else if (e.getSource() == loginButton) {
            new LoginFrame();   // Opens LoginFrame when "Login" is clicked
        } else if (e.getSource() == taskManagerButton) {
            new Main(); // Opens Task Manager when "Task Manager" is clicked
        } else if (e.getSource() == exitButton) {
            dispose();          // Exits the application when "Exit" is clicked
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
