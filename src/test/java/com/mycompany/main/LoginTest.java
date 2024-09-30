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
public class LoginTest {
    
   private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testCheckUserName() {
        assertTrue(login.checkUserName("tyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        assertFalse(login.checkUserName("nounderscore"));
        assertFalse(login.checkUserName("too_long"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
        assertFalse(login.checkPasswordComplexity("short1!"));
        assertFalse(login.checkPasswordComplexity("nocapital1!"));
        assertFalse(login.checkPasswordComplexity("NOCAPITAL1!"));
        assertFalse(login.checkPasswordComplexity("NoNumber!"));
        assertFalse(login.checkPasswordComplexity("NoSpecial1"));
    }

    @Test
    public void testRegisterUser() {
        assertEquals("User registered successfully!", 
                     login.registerUser("tyl_1", "Ch&&sec@ke99!", "Tyler", "Durden"));
        
        assertTrue(login.loginUser("tyl_1", "Ch&&sec@ke99!"));
        
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.", 
                     login.registerUser("invaliduser", "Ch&&sec@ke99!", "Invalid", "User"));
        
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", 
                     login.registerUser("val_1", "weak", "Weak", "Password"));
    }

    @Test
    public void testLoginUser() {
        login.registerUser("tyl_1", "Ch&&sec@ke99!", "Tyler", "Durden");
        
        assertTrue(login.loginUser("tyl_1", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("tyl_1", "wrongpassword"));
        assertFalse(login.loginUser("nonexistent", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatus() {
        login.registerUser("tyl_1", "Ch&&sec@ke99!", "Tyler", "Durden");
        
        assertEquals("Welcome Tyler, Durden it is great to see you again.", 
                     login.returnLoginStatus("tyl_1", "Ch&&sec@ke99!"));
        
        assertEquals("Username or password incorrect, please try again", 
                     login.returnLoginStatus("tyl_1", "wrongpassword"));
        
        assertEquals("Username or password incorrect, please try again", 
                     login.returnLoginStatus("nonexistent", "Ch&&sec@ke99!"));
    }
}
    