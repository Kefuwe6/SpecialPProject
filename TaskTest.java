/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tlesi
 */
public class TaskTest {
    
   private Task task1;
    private Task task2;
    
    @Before
    public void setUp() {
        // Reset static counters before each test
        Task.resetTaskCounter();
        
        // Create test tasks using the provided test data
        task1 = new Task(
            "Login Feature",
            "Create Login to authenticate users",
            "Robyn Harrison",
            8,
            "To Do"
        );
        
        task2 = new Task(
            "Add Task Feature",
            "Create Add Task feature to add task users",
            "Mike Smith",
            10,
            "Doing"
        );
    }
    
    @Test
    public void testTaskDescriptionSuccess() {
        assertTrue("Task description under 50 chars should be valid", 
            task1.checkTaskDescription());
    }
    
    @Test
    public void testTaskDescriptionFailure() {
        Task taskWithLongDesc = new Task(
            "Test Task",
            "This is a very long task description that should exceed fifty characters and fail the validation check",
            "John Doe",
            5,
            "To Do"
        );
        assertFalse("Task description over 50 chars should be invalid", 
            taskWithLongDesc.checkTaskDescription());
    }
    
    @Test
    public void testTaskIDGeneration() {
        assertEquals("Task ID for first test case should match", 
            "LO:0:SON", task1.createTaskID());
        assertEquals("Task ID for second test case should match", 
            "AD:1:ITH", task2.createTaskID());
    }
    
    @Test
    public void testTaskIDLoop() {
        String[] expectedIDs = {"CR:0:IKE", "CR:1:ARD", "CR:2:THA", "CR:3:NDA"};
        String[] developers = {"Mike Pike", "John Richard", "Sam Thailand", "Joe Panda"};
        
        Task.resetTaskCounter();
        
        for (int i = 0; i < expectedIDs.length; i++) {
            Task task = new Task(
                "Create Report",
                "Test Description",
                developers[i],
                5,
                "To Do"
            );
            assertEquals("Task ID should match in loop iteration " + i, 
                expectedIDs[i], task.createTaskID());
        }
    }
    
    @Test
    public void testTotalHoursBasic() {
        assertEquals("Total hours should be sum of first two tasks", 
            18, Task.returnTotalHours());
    }
    
    @Test
    public void testTotalHoursExtended() {
        Task.resetTaskCounter();
        int[] durations = {10, 12, 55, 11, 1};
        
        for (int duration : durations) {
            Task task = new Task(
                    "Test Task",
                    "Test Description",
                    "John Doe",
                    duration,
                    "To Do"
            );
        }
        
        assertEquals("Total hours should be sum of all durations", 
            89, Task.returnTotalHours());
    }
}


    
    // This class remains empty,
    // it is used only as a holder for the above annotations

