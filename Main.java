package com.todolist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.io.Serializable; // Import Serializable

public class Main extends Frame implements ActionListener, Serializable {
    private static final long serialVersionUID = 1L; // Add this line

    private TaskManager taskManager = new TaskManager();
    private Image backgroundImage;

    // GUI Components
    private TextField taskNameField, dueDateField, priorityField, removeTaskField, completeTaskField;
    private TextArea taskDisplayArea;

    public Main() {
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("resources/background.jpg");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add Task Section
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new Label("Task Name:"), gbc);

        gbc.gridx = 1;
        taskNameField = new TextField(20);
        add(taskNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new Label("Due Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        dueDateField = new TextField(10);
        add(dueDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new Label("Priority (1-10):"), gbc);

        gbc.gridx = 1;
        priorityField = new TextField(5);
        add(priorityField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        Button addButton = new Button("Add Task");
        add(addButton, gbc);
        addButton.addActionListener(this);

        // Remove Task Section
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new Label("Task ID to Remove:"), gbc);

        gbc.gridx = 1;
        removeTaskField = new TextField(5);
        add(removeTaskField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        Button removeButton = new Button("Remove Task");
        add(removeButton, gbc);
        removeButton.addActionListener(this);

        // Complete Task Section
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new Label("Task ID to Complete:"), gbc);

        gbc.gridx = 1;
        completeTaskField = new TextField(5);
        add(completeTaskField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        Button completeButton = new Button("Mark Task Complete");
        add(completeButton, gbc);
        completeButton.addActionListener(this);

        // Display Tasks Section
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        taskDisplayArea = new TextArea(10, 50);
        add(taskDisplayArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        Button viewTasksButton = new Button("View All Tasks");
        add(viewTasksButton, gbc);
        viewTasksButton.addActionListener(this);

        // Frame Settings
        setTitle("Task Manager");
        setSize(600, 400);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Method to paint the background color and image
    @Override
    public void paint(Graphics g) {
        // Set the background color to a light blue
        g.setColor(new Color(173, 216, 230));  // Light blue color
        g.fillRect(0, 0, getWidth(), getHeight());  // Fill the entire background

        // Draw the background image on top of the background color
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Ensure components are drawn on top of the background
        super.paint(g);
    }

    // Handle Button Click Events
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Add Task")) {
            String taskName = taskNameField.getText();
            String dueDate = dueDateField.getText();
            String priorityStr = priorityField.getText();

            try {
                int priority = Integer.parseInt(priorityStr);
                taskManager.addTask(taskName, dueDate, priority);
                JOptionPane.showMessageDialog(this, "Task added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for priority.");
            }

        } else if (command.equals("Remove Task")) {
            try {
                int taskId = Integer.parseInt(removeTaskField.getText());
                taskManager.removeTask(taskId);
                JOptionPane.showMessageDialog(this, "Task removed successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for task ID.");
            }

        } else if (command.equals("Mark Task Complete")) {
            try {
                int taskId = Integer.parseInt(completeTaskField.getText());
                taskManager.markTaskComplete(taskId);
                JOptionPane.showMessageDialog(this, "Task marked complete!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for task ID.");
            }

        } else if (command.equals("View All Tasks")) {
            taskDisplayArea.setText("");  // Clear previous text
            for (Task task : taskManager.getTasks()) {
                taskDisplayArea.append(task.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new Main();  // Launch the GUI
    }
}
