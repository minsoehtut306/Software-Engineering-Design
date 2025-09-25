import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runs the get user type menu, and moves to the next step
 */
public class UserManager {

    static User user;

    /**
     * Ask the user for their type, and store.
     * then navigates to next step in the menu flow
     * @param user to set the type of
     */
    public static void promptUserType() {

        //create the user object
		user = new User();

        boolean loop = true;

        UserType selectedType = null;

        while (loop) {

            System.out.println("Welcome to the Encost Smart Graph Project!");
            System.out.print("Specify User Type ('Encost Employee' or 'Community'): ");

            String userInput = "";

            InputStreamReader isr = new InputStreamReader(System.in);

            BufferedReader bf = new BufferedReader(isr);

            try {
                userInput = bf.readLine();
            } catch (IOException e) {
                return;
            }

            userInput = userInput.toLowerCase();
            
            if (userInput.equals("encost employee")) {
                selectedType = UserType.values()[0];
                loop = false;
            } else if (userInput.equals("community")) {
                selectedType = UserType.values()[2];
                loop = false;
            } else {
                System.out.println("Invalid Input");
            }           
        }

        user.setUserType(selectedType);

        navigateNextStep(user);
    }

    /**
     * Depending on the current user, navigate to login or options menu
     * @param user to read the type of
     */
    private static void navigateNextStep(User user) {
        if (user.getUserType() == UserType.encostUnverified) {
            LoginService.promptLogin(user);
        }
        
        FeatureAccessService.displayFeatureOptions(user);
    }
}
