package com.todolist;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();  // List to store tasks
    private int taskCounter = 1;

    // Method to add a task
    public void addTask(String taskName, String dueDateStr, int priority) {
        Task task = new Task(taskCounter, taskName, dueDateStr, priority);
        tasks.add(task);
        taskCounter++;
    }

    // Method to remove a task by ID
    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    // Method to mark a task as complete
    public void markTaskComplete(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setComplete(true);
                break;
            }
        }
    }

    // Method to get the list of tasks (important for fixing the error)
    public List<Task> getTasks() {
        return tasks;  // Return the list of tasks
    }
}