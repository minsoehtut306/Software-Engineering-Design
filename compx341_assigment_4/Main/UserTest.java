import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

/**
 * The UserTest class tests the functionality related to setting and retrieving user types
 * in the User class. The tests ensure that user types are accurately set and retrieved, 
 * reflecting the correct behavior as per the system's requirements. 
 */
public class UserTest {
    
    @Test
    @DisplayName("Testing setUserType() on community user type which is dependent on getUserType()") 
    public void getUserTypeTestCommunityType() {

         // Arrange
        User user = new User(); 
        UserType userType = UserType.community; // UserType enumeration: 0 = encost unverified, 1 = encost verified, 2 = community

        // Act
        user.setUserType(userType);

        // Assert
        assertEquals(userType, user.getUserType(), "The user type should be set to community");
    }

    @Test
    @DisplayName("Testing getUserType() on encost verified user type dependent on getUserType()") 
    public void getUserTypeTestEncostType() {

         // Arrange
        User user = new User(); 
        UserType userType = UserType.encostVerified; // UserType enumeration: 0 = encost unverified, 1 = encost verified, 2 = community

        // Act
        user.setUserType(userType);

        // Assert
        assertEquals(userType, user.getUserType(), "The user type should be set to encost verified");
    }

    @Test
    @DisplayName("Testing getUserType() on encost unverified user type dependent on getUserType()") 
    public void getUserTypeTestEncostUnverifiedType() {

         // Arrange
        User user = new User(); 
        UserType userType = UserType.encostUnverified; // UserType enumeration: 0 = encost unverified, 1 = encost verified, 2 = community

        // Act
        user.setUserType(userType);

        // Assert
        assertEquals(userType, user.getUserType(), "The user type should be set to encost unverified");
    }

    @Test
    @DisplayName("Testing getUserType() on invalid user type dependent on getUserType()") 
    public void getUserTypeTestInvalidType() {

         // Arrange
        User user = new User(); 
        UserType userType = null; // UserType enumeration: 0 = encost unverified, 1 = encost verified, 2 = community

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            user.setUserType(userType);
        }, "setUserType should throw IllegalArgumentException for invalid user types");
    }

}
