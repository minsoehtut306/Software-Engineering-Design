import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * UserCategorisation_UnitTests
 * Functional Unit Tests for TC01–TC06 (credential format validation)
 */
public class UserCategorisation_UnitTests {

    interface CredentialValidator {
        boolean isUsernameValid(String username);

        boolean isPasswordValid(String password);
    }

    CredentialValidator mockValidator = new CredentialValidator() {
        public boolean isUsernameValid(String username) {
            return username != null && username.length() >= 5 && username.length() <= 15;
        }

        public boolean isPasswordValid(String password) {
            return password != null && password.length() >= 8 && password.matches(".*[A-Z].*");
        }
    };

    // Username Validations
    @Test
    public void TC01_ValidUsername() {
        assertTrue(mockValidator.isUsernameValid("encostUser"));
    }

    @Test
    public void TC02_UsernameTooShort() {
        assertFalse(mockValidator.isUsernameValid("abc"));
    }

    @Test
    public void TC03_UsernameTooLong() {
        assertFalse(mockValidator.isUsernameValid("thisisaverylongusername"));
    }

    // Password Validations
    @Test
    public void TC04_ValidPassword() {
        assertTrue(mockValidator.isPasswordValid("Encost123"));
    }

    @Test
    public void TC05_PasswordTooShort() {
        assertFalse(mockValidator.isPasswordValid("abcD12"));
    }

    @Test
    public void TC06_PasswordNoUppercase() {
        assertFalse(mockValidator.isPasswordValid("encost123"));
    }
}
