package sec.Main;

import java.util.Scanner;
import java.util.List;

/**
 * ConsoleUI provides the user interface for the ESGP system.
 * It handles both Community and Encost user interactions, including
 * dataset loading, graph visualisation, and summary reporting.
 */
public class ConsoleUI {

    private final Scanner scanner;
    private final DeviceManager deviceManager;

    /**
     * Initializes the Console UI with a Scanner and DeviceManager.
     */
    public ConsoleUI() {
        scanner = new Scanner(System.in);
        deviceManager = new DeviceManager();
    }

    /**
     * Starts the UI interaction loop for user selection and login.
     */
    public void start() {
        printWelcome();
        while (true) {
            System.out.println("\nSelect user type:");
            System.out.println("1. Community User");
            System.out.println("2. Encost User");
            System.out.print("Enter choice (or type 'quit' to exit): ");

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("quit")) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }

            switch (input) {
                case "1":
                    handleCommunityUser();
                    break;
                case "2":
                    handleEncostUser();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prints a welcome banner on application start.
     */
    private void printWelcome() {
        System.out.println("=================================");
        System.out.println("  Welcome to ESGP - Smart Graph");
        System.out.println("=================================");
    }

    /**
     * Handles logic for Community users:
     * Loads a default dataset, shows device list, and offers graph display.
     */
    private void handleCommunityUser() {
        System.out.println("\n[Logged in as COMMUNITY USER]");

        String filepath = "resources/Encost Smart Homes Dataset (small).txt";
        if (deviceManager.loadDataset(filepath)) {
            List<Device> devices = deviceManager.getDevices();

            System.out.println("\n--- Devices in Dataset ---");
            for (Device d : devices) {
                System.out.println(d.deviceID + " | " + d.deviceName + " | " + d.deviceType);
            }

            System.out.print("\nWould you like to visualise the device graph? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("yes")) {
                GraphManager gm = new GraphManager();
                gm.buildAndDisplayGraph(devices);

                SummaryReporter sr = new SummaryReporter();
                sr.printSummary(devices);
            }
        } else {
            System.out.println("Failed to load dataset.");
        }
    }

    /**
     * Handles logic for Encost users:
     * Includes login, dataset loading, visualisation, and summary options.
     */
    private void handleEncostUser() {
        System.out.println("\n[ENCOST USER selected]");

        boolean success = ESGPAccessManager.login(scanner);
        if (!success) return;

        List<Device> devices = null;

        // Encost user options loop
        while (true) {
            System.out.println("\n==== Encost User Menu ====");
            System.out.println("1. Load a dataset");
            System.out.println("2. Visualise graph");
            System.out.println("3. View summary statistics");
            System.out.println("4. Quit to main menu");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit") || input.equals("4")) {
                System.out.println("Returning to main menu...");
                return;
            }

            switch (input) {
                case "1":
                    System.out.println("Choose dataset type:");
                    System.out.println("1. Default dataset");
                    System.out.println("2. Custom path");
                    System.out.print("Enter choice (or type 'quit' to cancel): ");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (choice.equals("quit")) {
                        System.out.println("Cancelled dataset load.");
                        continue;
                    }

                    String filepath = null;
                    if (choice.equals("1")) {
                        filepath = "resources/Encost Smart Homes Dataset (bigger).txt";
                    } else if (choice.equals("2")) {
                        System.out.print("Enter custom dataset path (or 'quit' to cancel): ");
                        filepath = scanner.nextLine().trim();
                        if (filepath.equalsIgnoreCase("quit")) {
                            System.out.println("Cancelled custom path.");
                            continue;
                        }
                        filepath = filepath.replace("\"", "");
                    } else {
                        System.out.println("Invalid dataset option.");
                        break;
                    }

                    if (deviceManager.loadDataset(filepath)) {
                        devices = deviceManager.getDevices();
                        System.out.println("Dataset loaded successfully.");
                    } else {
                        System.out.println("Dataset loading failed.");
                    }
                    break;

                case "2":
                    if (devices == null || devices.isEmpty()) {
                        System.out.println("No dataset loaded. Please load data first.");
                    } else {
                        GraphManager gm = new GraphManager();
                        gm.buildAndDisplayGraph(devices);
                    }
                    break;

                case "3":
                    if (devices == null || devices.isEmpty()) {
                        System.out.println("No dataset loaded. Please load data first.");
                    } else {
                        SummaryReporter sr = new SummaryReporter();
                        sr.printSummary(devices);
                    }
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
