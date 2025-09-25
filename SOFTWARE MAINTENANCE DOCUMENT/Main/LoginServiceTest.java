import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The LoginServiceTest class is designed to test the LoginService functionality,
 * ensuring robust handling of user authentication. This suite includes a variety of tests that 
 * simulate user input to the login prompt and validate the correct responses to various input scenarios.
 */
class LoginServiceTest {
    
    // ByteArrayOutputStream to capture the system's output during tests
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    // Save the original System.out to restore it after tests
    private final PrintStream originalOut = System.out;

    // ByteArrayInputStream to feed predefined input during tests; 
    // initialised as null here as it will be set in the relevant test methods
    private final ByteArrayInputStream inContent = null;

    // Global login service instance used by all tests
    private LoginService loginService; 

    @BeforeEach
    public void setUp() {
        // Redirects System.out to capture test outputs.
        System.setOut(new PrintStream(outContent));
        // Initialises the login service instance
        loginService = new LoginService();
    }

    @AfterEach
    public void restoreStreams() {
        // Restores System.out to prevent side effects.
        System.setOut(originalOut);

        // Resets System.in if altered during tests.
        if (inContent != null) {
            System.setIn(System.in);
        }
    }

    @Test
    @DisplayName("Test login with a space delimiter separating the username and password")
    void promptLoginTestWithSpaceDelimiter() {

        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "username password";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  

        // Assert
        // Checks output to confirm the receipt of valid credentials and that the 
        // login service has proceeded to the authentication phase.
        assertTrue(outContent.toString().contains("authenticating"), 
                  "Output should confirm that authentication is being processed.");
    }
   
    @Test
    @DisplayName("Test login with line feed delimiter separating the username and password")
    void promptLoginTestWithLineFeedDelimiter() {

        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "username\npassword";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  

        // Assert
        // Checks output to confirm the receipt of valid credentials and that the 
        // login service has proceeded to the authentication phase.
        assertTrue(outContent.toString().contains("invalid"), 
                   "Output should indicate the input was invalid");
    }
    
    @Test
    @DisplayName("Test login with comma delimiter separating the username and password")
    void promptLoginTestWithCommaDelimiter() {

        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "username,password";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  

        // Assert
        // Checks output to confirm the receipt of valid credentials and that the 
        // login service has proceeded to the authentication phase.
        assertTrue(outContent.toString().contains("authenticating"), 
                  "Output should confirm that authentication is being processed.");
    }

    @Test
    @DisplayName("Test login with pipe delimiter separating the username and password")
    void promptLoginTestWithPipeDelimiter() {

        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "username,password";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  

        // Assert
        // Checks output to confirm the receipt of valid credentials and that the 
        // login service has proceeded to the authentication phase.
        assertTrue(outContent.toString().contains("authenticating"), 
                  "Output should confirm that authentication is being processed.");
    }

    @Test
    @DisplayName("Test login with invalid input")
    void promptLoginTestWithInvalidInput() {
        
        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "usernamepassword";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

         // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  
        
        // Assert
        // Verify that the output includes a notification of invalid input,
        // prompting the user to correct their entry.
        assertTrue(outContent.toString().contains("invalid"),
                   "Output should indicate the input was invalid"); 
    }

    @Test
    @DisplayName("Test login with empty input")
    void promptLoginTestWithEmptyInput() {
        
        // Arrange 
        // This method reads from the command line (System.in), so we are mimicking this 
        // by redirecting our input as a byte stream to simulate the user's actions
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        // This method needs to read from System.in and write to System.out
        loginService.promptLogin();  
        
        // Assert
        // Verify that the output includes a notification of invalid input,
        // prompting the user to correct their entry.
        assertTrue(outContent.toString().contains("invalid"), 
                  "Output should indicate the input was invalid"); 
    }

    @Test
    @DisplayName("Test authenticateUser() with valid credentials")
    void authenticateUserTestValidCredentials() {
        
        // Arrange
        String username = "encostUserA"; // Verified encost user 
        String password = "password789";  
        
        // Act
        boolean result = loginService.authenticateUser(username, password); 
        
        // Assert
        // Assuming authenticateUser returns a boolean indicating success
        assertTrue(result,"Authentication should succeed with valid credentials.");
    }
    
    @Test
    @DisplayName("Test authenticateUser() with invalid credentials")
    void authenticateUserTestInvalidCredentials() {
        
        // Arrange
        String username = "invalidUsername"; 
        String password = "invalidPassword"; 
        
        // Act
        boolean result = loginService.authenticateUser(username, password); 
        
        // Assert
        // Assuming authenticateUser returns a boolean indicating failure
        assertFalse(result, "Authentication should fail with invalid credentials.");
    }
    
    @Test
    @DisplayName("Test authenticateUser() with empty credentials")
    void authenticateUserEmptyCredentials() {
        
        // Arrange
        String username = ""; 
        String password = ""; 

        // Act
        boolean result = loginService.authenticateUser(username, password); 
        
        // Assert
        // Assuming authenticateUser returns a boolean indicating failure
        assertFalse(result, "Authentication should fail with empty invalid credentials.");
    }
}