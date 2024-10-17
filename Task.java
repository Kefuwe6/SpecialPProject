/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author tlesi
 */
public final class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;
    private static int taskCounter = 0;
    private static int totalHours = 0;

    public Task(String taskName, String taskDescription, String developerDetails, 
                int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskCounter++;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        totalHours += taskDuration;
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        String[] devNames = developerDetails.split(" ");
        String lastName = devNames[devNames.length - 1];
        return (taskName.substring(0, 2) + ":" + taskNumber + ":" + 
                lastName.substring(lastName.length() - 3)).toUpperCase();
    }

    public String printTaskDetails() {
        return String.format("""
            Task Status: %s
            Developer Details: %s
            Task Number: %d
            Task Name: %s
            Task Description: %s
            Task ID: %s
            Duration: %d hours""", 
            taskStatus, developerDetails, taskNumber, 
            taskName, taskDescription, taskID, taskDuration);
    }

    public static int returnTotalHours() {
        return totalHours;
    }

    // Reset for testing purposes
    public static void resetTaskCounter() {
        taskCounter = 0;
        totalHours = 0;
    }
}

