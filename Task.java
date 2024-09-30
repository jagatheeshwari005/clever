package com.todolist;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Add serialVersionUID

    private int taskId;
    private String taskName;
    private String dueDate;
    private int priority;
    private boolean isComplete;

    // Constructor
    public Task(int taskId, String taskName, String dueDate, int priority) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isComplete = false; // Default to not complete
    }

    // Getters
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    // Method to set task as complete
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Name: " + taskName + ", Due Date: " + dueDate + 
               ", Priority: " + priority + ", Complete: " + isComplete;
    }
}