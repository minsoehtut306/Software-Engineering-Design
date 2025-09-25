package sec.Test;

import org.junit.jupiter.api.Test;
import sec.Main.ESGPAccessManager;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ESGPAccessManager, which handles Encost user authentication.
 * Tests include valid login, invalid login, and quit scenarios.
 */
public class ESGPAccessManagerTest {

    /**
     * Simulates a successful login with valid credentials.
     * Expects the login method to return true.
     */
    @Test
    public void testValidLogin() {
        String input = "encostUserA\npassword789\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = ESGPAccessManager.login(scanner);
        assertTrue(result, "Valid credentials should result in successful login");
    }

    /**
     * Simulates a login attempt with incorrect username and password.
     * Expects the login method to return false.
     */
    @Test
    public void testInvalidLogin() {
        String input = "wrongUser\nwrongPassword\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = ESGPAccessManager.login(scanner);
        assertFalse(result, "Invalid credentials should result in login failure");
    }

    /**
     * Simulates the user entering 'quit' during the login prompt.
     * Expects the login method to terminate and return false.
     */
    @Test
    public void testQuitEarly() {
        String input = "quit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = ESGPAccessManager.login(scanner);
        assertFalse(result, "Typing 'quit' should exit login process");
    }
}
