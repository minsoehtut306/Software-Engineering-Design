import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Ask the user for their credentials, and verify them
 */
public class LoginService {

    /**
     * Authenticate the username / password pair for a test user
     * @param userName to verify the password for
     * @param password to verify for the given user
     * @return if password was correct
     */
    public static Boolean authenticateUser(String userName, String password) {
        User testUser = new User(UserType.encostUnverified);
        testUser.userName = userName;
        return authenticateUser(testUser, password);
    }

    /**
     * Authenticate the username / password pair
     * @param user to verify the password for
     * @param password to verify for the given user
     * @return if password was correct
     */
    private static Boolean authenticateUser(User user, String password) {
        if (AuthenticationManager.authenticate(user.userName, password)) {
            user.setUserType(UserType.encostVerified);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Test method, verify that an unverified user sees the correct menu options of promptLogin(User user)
     */
    public static void promptLogin() {
        User testUser = new User(UserType.encostUnverified);
        promptLogin(testUser);
    }

    /**
     * Ask the user for their credentials, and update UserType if credentials correct
     * @param user to set type of
     */
    public static void promptLogin(User user) {

        boolean loop = true;

        while (loop) {

            System.out.print("Enter your username and password (Seperated By space or comma): ");

            String userInput = "";

            InputStreamReader isr = new InputStreamReader(System.in);
            
            BufferedReader bf = new BufferedReader(isr);

            try {
                userInput = bf.readLine();
            } catch (IOException e) {
                return;
            }

            if (userInput == null){
                System.out.println("invalid");
                return;
            }

            String pattern = "^(\\w+)[ ,](\\w+)$";

            if (Pattern.matches(pattern, userInput)) {

                String[] inputs = userInput.split("[ ,]");

                String userName = inputs[0];
                String password = inputs[1];

                System.out.println("authenticating");

                user.userName = userName;
                boolean status = authenticateUser(user, password);

                if (status) {
                    loop = false;
                } else {
                    notifyUser();
                }
            } else {
                System.out.println("Input format invalid");
            }
        }

        System.out.println("Authentication Success");
    }

    /**
     * Notfiy user of a bad username / password input
     */
    public static void notifyUser() {
        System.out.println("Incorrect username and/or password, please retry");
    }
}
