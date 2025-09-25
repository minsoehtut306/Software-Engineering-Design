package sec.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SummaryReporter provides statistical summaries of device data.
 * It supports both console-friendly output and structured category/household summaries.
 */
public class SummaryReporter {

    /**
     * Prints summary statistics of the given list of devices to the console.
     * Includes total count, breakdown by type, per-household distribution,
     * and send/receive capabilities.
     *
     * @param devices list of Device objects to summarize
     */
    public void printSummary(List<Device> devices) {
        if (devices == null || devices.isEmpty()) {
            System.out.println("No devices found.");
            return;
        }

        System.out.println("\n====== Summary Statistics ======");

        int total = devices.size();
        Map<String, Integer> typeCount = new HashMap<>();
        Map<String, Integer> householdCount = new HashMap<>();
        int sendCount = 0, receiveCount = 0;

        // Aggregate stats
        for (Device d : devices) {
            // Count by device type (lowercased for consistency)
            String type = d.deviceType.toLowerCase();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);

            // Count per household ID
            String household = d.householdID;
            householdCount.put(household, householdCount.getOrDefault(household, 0) + 1);

            if (d.sends) sendCount++;
            if (d.receives) receiveCount++;
        }

        // Output totals
        System.out.println("Total devices: " + total);

        // Output device type distribution
        System.out.println("\nDevices by Type:");
        for (String type : typeCount.keySet()) {
            System.out.printf("  %-20s %d\n", type, typeCount.get(type));
        }

        // Output household distribution
        System.out.println("\nDevices per Household:");
        for (String hh : householdCount.keySet()) {
            System.out.printf("  %-10s %d\n", hh, householdCount.get(hh));
        }

        // Output data capabilities
        System.out.println("\nSend-enabled devices: " + sendCount);
        System.out.println("Receive-enabled devices: " + receiveCount);
        System.out.println("================================\n");
    }

    /**
     * Returns a map of device category counts.
     *
     * @param devices list of Device objects
     * @return Map from DeviceCategory to number of devices in that category
     */
    public Map<DeviceCategory, Long> getCategoryCounts(List<Device> devices) {
        return devices.stream()
            .collect(Collectors.groupingBy(Device::getCategory, Collectors.counting()));
    }

    /**
     * Returns a map of household ID counts.
     *
     * @param devices list of Device objects
     * @return Map from household ID to device count
     */
    public Map<String, Long> getHouseholdCounts(List<Device> devices) {
        return devices.stream()
            .collect(Collectors.groupingBy(d -> d.householdID, Collectors.counting()));
    }
}
