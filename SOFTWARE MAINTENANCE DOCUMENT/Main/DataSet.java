import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


/**
 * Holds the File() of the dataset
 */
public class DataSet {
    private File encostData;

    /**
     * Create the dataset with the default encost data
     */
    public DataSet(){
        encostData = new File("EncostSmartHomesDataset.txt");
    }

    /**
     * Load a custom dataset
     * @param custom dataset path
     */
    public void setDataSet (String custom){
        encostData = new File(custom);
        if (!encostData.exists()){
            throw new RuntimeException("unable to find dataset");
        }
    }

    /**
     * load a file and verify it is in the correct format
     * @param custom dataset path
     * @return is the dataset valid
     */
    public Boolean checkFormat(String custom){

        File checkFile = new File(custom);

        try (Scanner scanner = new Scanner(checkFile)) {
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                if (row.length != 8){
                    throw new RuntimeException("Invalid Row");
                }

                for (String str : row) {
                    if (str.isEmpty()) {
                        throw new RuntimeException("Invalid Row");
                    }
                }                

            }
        } catch (Exception e){
            throw new RuntimeException("Invalid Row");
        }

        return true;
    }

    /**
     * @return the current File() dataset
     */
    public File getDataSet(){
        return encostData;
    }

    /**
     * Check if the current dataset is in a valid format
     * @return is the dataset valid
     */
    public Boolean checkFormat(){

        try (Scanner scanner = new Scanner(encostData)) {
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                if (row.length != 8){
                    throw new RuntimeException("Invalid Row");
                }

                for (String str : row) {
                    if (str.isEmpty()) {
                        throw new RuntimeException("Invalid Row");
                    }
                }   

            }
        } catch (Exception e){
            throw new RuntimeException("Invalid Row");
        }

        return true;
    }

    /**
     * Print a summary of the dataset including:
     * - Number of devices per type
     * - Total number of devices
     * - Number of unique households
     * - Number of devices that can send & receive data
     */
    public void printSummaryStatistics() {
        Map<String, Integer> deviceTypeCount = new HashMap<>();
        Set<String> householdIds = new HashSet<>();
        int totalDevices = 0;
        int sendingCount = 0;
        int receivingCount = 0;

        try (Scanner scanner = new Scanner(encostData)) {
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");

                if (row.length != 8) {
                    throw new RuntimeException("Invalid Row");
                }

                String householdId = row[0];
                String deviceType = row[1];
                String sending = row[6];
                String receiving = row[7];

                householdIds.add(householdId);
                deviceTypeCount.put(deviceType, deviceTypeCount.getOrDefault(deviceType, 0) + 1);
                totalDevices++;

                if (sending.equalsIgnoreCase("yes")) sendingCount++;
                if (receiving.equalsIgnoreCase("yes")) receivingCount++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading dataset: " + e.getMessage());
        }

        // Output formatting
        System.out.println("---------------------------------------------------");
        System.out.println("SUMMARY STATISTICS");
        System.out.println("---------------------------------------------------");
        System.out.println("Total Unique Households: " + householdIds.size());
        System.out.println("Total Devices: " + totalDevices);
        System.out.println("Devices by Type:");
        for (Map.Entry<String, Integer> entry : deviceTypeCount.entrySet()) {
            System.out.printf("  - %s: %d\n", entry.getKey(), entry.getValue());
        }
        System.out.println("Devices with Sending Capability: " + sendingCount);
        System.out.println("Devices with Receiving Capability: " + receivingCount);
        System.out.println("---------------------------------------------------");
    }

}
