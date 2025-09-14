package sec.Main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ESGPAccessManager handles authentication for Encost users.
 * It loads user credentials from a local file and validates login attempts.
 */
public class ESGPAccessManager {

    /** Path to the file containing Encost user credentials */
    private static final String USER_FILE = "resources/users.txt";

    /** Stores username-password pairs loaded from file */
    private static final Map<String, String> userMap = new HashMap<>();

    // Static block loads the user list once on class load
    static {
        loadUsers();
    }

    /**
     * Loads usernames and passwords from a CSV-style text file.
     * Each line must contain two values: username,password
     */
    private static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    userMap.put(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user file: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to log in with a username and password via the console.
     *
     * @param scanner Scanner object for reading input from the console
     * @return true if login is successful; false otherwise (or if user types 'quit')
     */
    public static boolean login(Scanner scanner) {
        System.out.print("Enter username (or 'quit' to exit): ");
        String username = scanner.nextLine().trim();
        if (username.equalsIgnoreCase("quit")) return false;

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (authenticate(username, password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid credentials. Returning to main menu.");
            return false;
        }
    }

    /**
     * Validates the username and password against the loaded credentials.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if both match; false otherwise
     */
    private static boolean authenticate(String username, String password) {
        return userMap.containsKey(username) && userMap.get(username).equals(password);
    }
}
