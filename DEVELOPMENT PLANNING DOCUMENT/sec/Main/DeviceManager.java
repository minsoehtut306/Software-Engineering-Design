package sec.Main;

import java.io.*;
import java.util.*;

/**
 * DeviceManager handles the loading and management of smart home device data
 * from CSV files. It also classifies devices into predefined categories.
 */
public class DeviceManager {

    /** Internal list of loaded devices */
    private final List<Device> devices = new ArrayList<>();

    /**
     * Loads devices from the specified CSV file.
     * Each line is parsed into a Device object and classified by type.
     *
     * @param filepath the path to the dataset file
     * @return true if loading is successful; false if an error occurs
     */
    public boolean loadDataset(String filepath) {
        devices.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String header = br.readLine(); // Skip the header row

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue; // Skip malformed lines

                // Extract fields
                String id = parts[0].trim();
                String name = parts[2].trim();
                String type = parts[3].trim();
                String household = parts[4].trim();
                String router = parts[5].trim();
                boolean sends = parts[6].trim().equalsIgnoreCase("Yes");
                boolean receives = parts[7].trim().equalsIgnoreCase("Yes");

                // Create and categorize device
                Device device = new Device(id, name, type, household, router, sends, receives);
                device.category = classifyDevice(type);

                devices.add(device);
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns the list of devices loaded from the dataset.
     *
     * @return List of Device objects
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Classifies a device into one of the DeviceCategory types based on its type string.
     *
     * @param type the device type text (e.g., "Smart Bulb", "Router")
     * @return DeviceCategory enum value corresponding to the type
     */
    private DeviceCategory classifyDevice(String type) {
        type = type.toLowerCase();
        if (type.contains("light") || type.contains("bulb")) return DeviceCategory.LIGHTING;
        if (type.contains("router")) return DeviceCategory.NETWORK;
        if (type.contains("hub") || type.contains("controller")) return DeviceCategory.CONTROLLER;
        if (type.contains("kettle") || type.contains("jug") || type.contains("appliance")) return DeviceCategory.APPLIANCE;
        return DeviceCategory.OTHER;
    }
}
