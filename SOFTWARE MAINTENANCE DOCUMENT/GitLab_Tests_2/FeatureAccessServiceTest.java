import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

/**
 * The FeatureAccessServiceTest class tests the functionality of the FeatureAccessService
 * in the context of user interactions, particularly focusing on how different user types
 * access different features within the system. This class employs Mockito to simulate
 * user interactions and uses assertions to ensure that the correct features are displayed
 * for different types of users, and that appropriate messages are displayed based on user inputs.
 */
public class FeatureAccessServiceTest {
        
    // ByteArrayOutputStream to capture the system's output during tests
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    // Save the original System.out to restore it after tests
    private final PrintStream originalOut = System.out;

    // ByteArrayInputStream to feed predefined input during tests; 
    // initialised as null here as it will be set in the relevant test methods
    private final ByteArrayInputStream inContent = null;
    
    // Global feature access service instance used by all tests
    private FeatureAccessService featureAccessService;

    @BeforeEach
    public void setUp() {
        // Redirects System.out to capture test outputs.
        System.setOut(new PrintStream(outContent));
        featureAccessService = new FeatureAccessService();
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
    @DisplayName("Test displayFeatureOptions() displays the correct features for an Encost Verified user"+ 
    ", dependant on the User class")
    void displayFeatureOptionsTestEncostUserAccess() {

        // Arrange 
        // To display the feature options, a mock user instance has to be made and 
        // it should return the encost user type 
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostVerified);

        // Act
        // This method needs to read from System.in and write to System.out
        featureAccessService.displayFeatureOptions(mockUser);  

        // Assert
        // Checks output to confirm the correct feature options have been displayed
        assertTrue(outContent.toString().contains("1. Graph Visualization\r\n" + 
                                                  "2. Upload Custom Dataset\r\n" + 
                                                  "3. View Summary Statistics"), 
                  "Output should display the 3 feature options visible to an Encost verified user.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions() displays the correct features for a Community user"+ 
    ", dependant on the User class")
    void displayFeatureOptionsTestCommunityAccess() {

        // Arrange 
        // To display the feature options, a mock user instance has to be made and 
        // it should return the community user type 
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.community);

        // Act
        // This method needs to read from System.in and write to System.out
        featureAccessService.displayFeatureOptions(mockUser);  

        // Assert
        // Checks output to confirm the correct feature options have been displayed
        assertTrue(outContent.toString().contains("1. Graph Visualization"), 
                  "Output should display the single feature option visible to a Community User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions() displays the correct features for an unverified Encost user" + 
    ", dependant on the User class")
    void displayFeatureOptionsUnverifiedEncostAccess() {

        // Arrange 
        // To display the feature options, a mock user instance has to be made and 
        // it should return the encost unverified user type 
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostUnverified);

        // Act
        // This method needs to read from System.in and write to System.out
        featureAccessService.displayFeatureOptions(mockUser);  

        // Assert
        // Checks output to confirm the correct feature options have been displayed
        assertTrue(outContent.toString().contains("1. Graph Visualization"), 
                  "Output should display the single feature option visible to an unverified Encost User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to the accepted " +
    "input from a Community user")
    void displayFeatureOptionsTestCommunityValidInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.community);
        String input = "1"; // The only option for a community user (graph visualisation)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message to indicate the graph visualisation 
        // feature has been loaded has been displayed
        assertTrue(outContent.toString().contains("graph visualisation option message"),
                   "Should display graph visualisation option for Community User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to unaccepted inputs from " + 
    "a Community user, dependant on the User class")
    void displayFeatureOptionsTestCommunityInvalidInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.community);
        String input = "2"; // Any other option is unavailable to a Community User
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message informs the community user of an invalid 
        // input
        assertTrue(outContent.toString().contains("invalid"),
                   "Should display an error message indicating an invalid input.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to empty inputs from " + 
    "a Community user, dependant on the User class")
    void displayFeatureOptionsTestCommunityEmptyInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.community);
        String input = ""; // Empty input provided
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message informs the community user of an invalid 
        // input
        assertTrue(outContent.toString().contains("invalid"),
                   "Should display an error message indicating an invalid input.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to the accepted input from " +
    "an unverified Encost user, dependant on the User class")
    void displayFeatureOptionsTestEncostUnverifiedValidInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostUnverified);
        String input = "1"; // The only option for an encost unverified user (graph visualisation)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message to indicate the graph visualisation 
        // feature has been loaded has been displayed
        assertTrue(outContent.toString().contains("graph visualisation option message"),
                   "Should display graph visualisation option for Community User.");
    }
    
    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to unaccepted inputs from " + 
    "a Encost Unverified user, dependant on the User class")
    void displayFeatureOptionsTestEncostUnverifiedInvalidInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostUnverified);
        String input = "2"; // Any other option is unavailable to a Encost Unverified User
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        // Act
        featureAccessService.displayFeatureOptions(mockUser);
        
        // Assert
        // Checks whether the corresponding message informs the encost unverified user 
        // of an invalid input
        assertTrue(outContent.toString().contains("invalid"),
        "Should display an error message indicating an invalid input.");
    }
    
    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to the accepted input from" + 
    " a verified Encost user")
    void displayFeatureOptionsTestEncostVerifiedGraphInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostVerified);
        String input = "1"; // One of three options for the Encost User (Graph Visualisation = 1)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message to indicate the graph visualisation 
        // feature has been loaded has been displayed
        assertTrue(outContent.toString().contains("graph visualisation option message"),
                   "Should display graph visualisation option for Enocst User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to the accepted custom datasaet input from" +
    " a verified Encost user, dependant on the User class")
    void displayFeatureOptionsTestEncostVerifiedStatsInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostVerified);
        String input = "2"; // One of three options for the Encost User (Custom Dataset = 2)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message to indicate the graph visualisation 
        // feature has been loaded has been displayed
        assertTrue(outContent.toString().contains("custom dataset option message"),
                   "Should display summary statistics option for Encost User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to the accepted Stats input from" +
    " a verified Encost user, dependant on the User class")
    void displayFeatureOptionsTestEncostVerifiedStatsInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostVerified);
        String input = "3"; // One of three options for the Encost User (Summary Stats = 3)
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message to indicate the graph visualisation 
        // feature has been loaded has been displayed
        assertTrue(outContent.toString().contains("summary statistics option message"),
                   "Should display summary statistics option for Encost User.");
    }

    @Test
    @DisplayName("Test displayFeatureOptions responds corerctly to unaccepted" + 
    " inputs from a verified Encost user, dependant on the User class")
    void displayFeatureOptionsTestEncostInvalidInput() {
        // Arrange
        User mockUser = mock(User.class);
        when(mockUser.getUserType()).thenReturn(UserType.encostVerified);
        String input = "A"; // Any other option is unavailable to a Community User
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Act
        featureAccessService.displayFeatureOptions(mockUser);

        // Assert
        // Checks whether the corresponding message informs the encost user 
        // of an invalid input
        assertTrue(outContent.toString().contains("invalid"),
                   "Should display an error message indicating an invalid input.");
    }
}
