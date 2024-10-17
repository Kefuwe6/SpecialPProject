/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tlesi
 */
public class EasyKanban {
     private Login loginSystem;
    private List<Task> tasks;
    private boolean isLoggedIn;

    public EasyKanban() {
        this.loginSystem = new Login();
        this.tasks = new ArrayList<>();
        this.isLoggedIn = false;
    }

    public void run() {
        while (true) {
            if (!isLoggedIn) {
                int choice = Integer.parseInt(JOptionPane.showInputDialog(
                    """
                    1. Register
                    2. Login
                    3. Quit
                    Please select an option:"""));

                switch (choice) {
                    case 1 -> registerNewUser();
                    case 2 -> login();
                    case 3 -> {
                        return;
                    }
                }
            } else {
                showMainMenu();
            }
        }
    }

    private void registerNewUser() {
        String username = JOptionPane.showInputDialog("Enter username (must contain underscore and be <= 5 chars):");
        String password = JOptionPane.showInputDialog("Enter password (min 8 chars, must contain capital, number, and special char):");
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");

        String result = loginSystem.registerUser(username, password, firstName, lastName);
        JOptionPane.showMessageDialog(null, result);
    }

    private void login() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        String loginStatus = loginSystem.returnLoginStatus(username, password);
        JOptionPane.showMessageDialog(null, loginStatus);

        if (loginSystem.loginUser(username, password)) {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        }
    }

    private void showMainMenu() {
        int choice = Integer.parseInt(JOptionPane.showInputDialog(
            """
            1. Add tasks
            2. Show report
            3. Quit
            Choose an option:"""));

        switch (choice) {
            case 1 -> addTasks();
            case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon");
            case 3 -> isLoggedIn = false;
        }
    }

    private void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks would you like to add?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 chars):");
            String developerDetails = JOptionPane.showInputDialog("Enter developer full name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (hours):"));
            
            String[] statusOptions = {"To Do", "Done", "Doing"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, 
                "Select task status:", "Task Status",
                JOptionPane.QUESTION_MESSAGE, null,
                statusOptions, statusOptions[0]);

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            
            if (!task.checkTaskDescription()) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a task description of less than 50 characters");
                i--; // Retry this task
                continue;
            }

            tasks.add(task);
            JOptionPane.showMessageDialog(null, 
                "Task successfully captured\n\n" + task.printTaskDetails());
        }

        JOptionPane.showMessageDialog(null, 
            "Total hours for all tasks: " + Task.returnTotalHours());
    }

    public static void main(String[] args) {
        new EasyKanban().run();
    }
}


