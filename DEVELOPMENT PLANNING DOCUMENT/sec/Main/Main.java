package sec.Main;

/**
 * Main class for launching the ESGP (Encost Smart Graph Project) application.
 * It creates and starts the ConsoleUI, which handles user interaction.
 */
public class Main {
    /**
     * Entry point for the ESGP system.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ConsoleUI app = new ConsoleUI();
        app.start();
    }
}
