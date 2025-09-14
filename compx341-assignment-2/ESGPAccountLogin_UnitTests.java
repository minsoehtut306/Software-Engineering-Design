import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ESGP Account Login
 * Functional Unit Tests for TC07–TC11 (Login behavior based on credential
 * validity)
 */
public class ESGPAccountLogin_UnitTests {

    // Contract Interface
    interface Login {
        boolean authenticate(String username, String password);
    }

    // Mock Implementation for testing
    Login mockLogin = new Login() {
        public boolean authenticate(String username, String password) {
            return "encostUser".equals(username) && "encostPass".equals(password);
        }
    };

    @Test
    public void TC07_ValidUsernameAndPassword() {
        assertTrue(mockLogin.authenticate("encostUser", "encostPass"), "Login should succeed with valid credentials");
    }

    @Test
    public void TC08_InvalidUsernameValidPassword() {
        assertFalse(mockLogin.authenticate("wrongUser", "encostPass"), "Login should fail with invalid username");
    }

    @Test
    public void TC09_ValidUsernameInvalidPassword() {
        assertFalse(mockLogin.authenticate("encostUser", "wrongPass"), "Login should fail with invalid password");
    }

    @Test
    public void TC10_InvalidUsernameAndPassword() {
        assertFalse(mockLogin.authenticate("wrong", "1234"), "Login should fail with both credentials wrong");
    }

    @Test
    public void TC11_EmptyCredentials() {
        assertFalse(mockLogin.authenticate("", ""), "Login should fail with empty credentials");
    }
}
