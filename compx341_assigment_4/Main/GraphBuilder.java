import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.io.File;
import java.util.Scanner;

/**
 * Build the graph from the current dataset
 */
public class GraphBuilder {

    private Graph graphDataset;

    /**
     * read the dataset, create corresponding devices, and add them to the Graph
     * 
     * @param dataset to read from
     */
    public void readDataset(File dataset) {

        graphDataset = new SingleGraph("Graph w Stylesheet for Nodes");

        String cssFilePath = new Scanner(Main.class.getResourceAsStream("stylesheet.css"), "UTF-8")
                .useDelimiter("\\A")
                .next();
        graphDataset.setAttribute("ui.stylesheet", cssFilePath);

        try (Scanner scanner = new Scanner(dataset)) {

            String headers = scanner.nextLine();

            while (scanner.hasNextLine()) {

                String[] row = scanner.nextLine().split(",");
                
                Device newDevice = new Device(
                        row[0],
                        row[1],
                        row[2],
                        getDeviceCategory(row[3]),
                        row[4],
                        row[5],
                        row[6].equals("Yes"),
                        row[7].equals("Yes"));

                addToGraph(newDevice);

            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid Row");
        }
    }

    private void addToGraph(Device newDevice) {

        Node node = graphDataset.addNode(newDevice.deviceID);
        node.setAttribute("Device", newDevice);
        node.setAttribute("ui.class", newDevice.deviceType.toString());
        node.setAttribute("ui.label", newDevice.deviceID);

        if (graphDataset.getNode(newDevice.routerConnection) != null) {

            if (newDevice.send) {
                graphDataset.addEdge(newDevice.deviceID + ":" + newDevice.routerConnection, newDevice.deviceID,
                        newDevice.routerConnection, true);
            }

            if (newDevice.recieve) {
                graphDataset.addEdge(newDevice.routerConnection + ":" + newDevice.deviceID, newDevice.routerConnection,
                        newDevice.deviceID, true);
            }
        }
    }

    /**
     * Categorise the device from its device type string
     * 
     * @param deviceString to parse
     * @return the DeviceCategory
     */
    private DeviceCategory getDeviceCategory(String deviceString) {
        if (deviceString.contains("Light")) {
            return DeviceCategory.Lighting;
        } else if (deviceString.equals("Kettle") ||
                deviceString.equals("Toaster") ||
                deviceString.equals("Coffee Maker")) {
            return DeviceCategory.Appliances;
        } else if (deviceString.equals("Router") ||
                deviceString.equals("Extender")) {
            return DeviceCategory.Router;
        } else if (deviceString.equals("Hub/ Controller")) {
            return DeviceCategory.Controller;
        } else if (deviceString.equals("Washing Machine/Dryer") ||
                deviceString.equals("Refrigerator/Freezer") ||
                deviceString.equals("Dishwasher")) {
            return DeviceCategory.Whiteware;
        } else {
            return DeviceCategory.Appliances;// todo, not this
        }
    }

    /**
     * @return the built GraphStream data structure
     */
    public Graph getGraph() {
        return graphDataset;
    }
}
