package sec.Main;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.List;

/**
 * GraphManager is responsible for constructing and displaying a device network graph
 * using the GraphStream library. Nodes represent devices and edges represent
 * router-based communication.
 */
public class GraphManager {

    /**
     * Builds and displays a directed graph of smart devices.
     * Each node is styled according to its category (e.g., NETWORK, LIGHTING).
     *
     * @param devices the list of Device objects to include in the graph
     */
    public void buildAndDisplayGraph(List<Device> devices) {
        Graph graph = new SingleGraph("Smart Home Graph");

        // Define custom styles for device categories
        graph.setAttribute("ui.stylesheet", 
            "node.network { fill-color: blue; }" +
            "node.lighting { fill-color: yellow; }" +
            "node.appliance { fill-color: green; }" +
            "node.controller { fill-color: orange; }" +
            "node.other { fill-color: gray; }"
        );

        graph.setAutoCreate(true); // Automatically create nodes if referenced in edges
        graph.setStrict(false);    // Allow duplicate or unordered operations

        for (Device device : devices) {
            String nodeId = device.deviceID;
            Node node = graph.addNode(nodeId);

            // Display the device name as a label
            node.setAttribute("ui.label", device.deviceName);

            // Apply visual styling class based on device category
            DeviceCategory cat = device.getCategory();
            if (cat != null) {
                switch (cat) {
                    case NETWORK -> node.setAttribute("ui.class", "network");
                    case LIGHTING -> node.setAttribute("ui.class", "lighting");
                    case APPLIANCE -> node.setAttribute("ui.class", "appliance");
                    case CONTROLLER -> node.setAttribute("ui.class", "controller");
                    default -> node.setAttribute("ui.class", "other");
                }
            } else {
                node.setAttribute("ui.class", "other");
            }

            // Add an edge from this device to its router, if applicable
            if (!device.routerConnection.equalsIgnoreCase("null")) {
                String edgeId = nodeId + "-" + device.routerConnection;
                graph.addEdge(edgeId, nodeId, device.routerConnection, true); // Directed edge
            }
        }

        // Show the graph in a viewer window
        graph.display();
    }
}
