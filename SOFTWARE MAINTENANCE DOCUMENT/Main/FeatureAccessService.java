import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main feature menu after user type is determined
 */
public class FeatureAccessService {

    //hold the graph and data
    static GraphVisualisation graphVisualisation;
    
    /**
     * Displays the menu
     * @param user to display the appropriate options for
     */
    public static void displayFeatureOptions(User user) {

        graphVisualisation = new GraphVisualisation();

        boolean loop = true;

        while (loop) {

            System.out.println();
            System.out.println("Available Features:");
            System.out.println("---------------------------------------------------------");

            if (user.getUserType() == UserType.encostVerified) {
                System.out.println("0. Close Program\r\n"+
                        "1. Graph Visualization\r\n" +
                        "2. Upload Custom Dataset\r\n" +
                        "3. View Summary Statistics");
            } else {
                System.out.println("0. Close Program\r\n"+"1. Graph Visualization");
            }
            System.out.print("Selected Option: ");

            String userInput = "";

            InputStreamReader isr = new InputStreamReader(System.in);

            BufferedReader bf = new BufferedReader(isr);

            try {
                userInput = bf.readLine();
            } catch (IOException e) {
                System.out.println("invalid input");
                return;
            }

            if (userInput == null){
                System.out.println("invalid input");
                return;
            }

            else if (userInput.equals("0")){
                System.out.println("Closing Program");
                return;
            }

            else if (userInput.equals("1")){
                System.out.println("graph visualisation option message");
                graphVisualisation.displayGraph();
                loop = false;
            }

            else if (userInput.equals("2") && user.getUserType() == UserType.encostVerified){
                System.out.println("Upload custom dataset option message");
                System.out.println("Low Priority, Not required to be implemented");
            }

            else if (userInput.equals("3") && user.getUserType() == UserType.encostVerified){
                System.out.println("View summary statistics option message");
                //System.out.println("Low Priority, Not required to be implemented");
                DataSet data = new DataSet();
                data.printSummaryStatistics();

            } 

            else {
                System.out.println("invalid input");
            }

        }

    }
}
