import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Build Graph_UnitTests
 * Functional Unit Tests for TC30–TC34 (Decision Table Testing)
 * Based on SRS 4.7.3 requirements for graph construction.
 */
public class BuildGraph_UnitTests {

    interface GraphBuilder {
        String buildGraph(boolean datasetLoaded, boolean validDevices, boolean connectionsExist, boolean newDataset);
    }

    GraphBuilder mockBuilder = new GraphBuilder() {
        public String buildGraph(boolean datasetLoaded, boolean validDevices, boolean connectionsExist,
                boolean newDataset) {
            if (!datasetLoaded)
                return "Error: Dataset not loaded";
            if (!validDevices)
                return "Error: Invalid device data";
            if (newDataset)
                return "Previous graph cleared; new graph created";
            if (connectionsExist)
                return "Graph created with nodes and edges";
            return "Graph created with nodes only";
        }
    };

    @Test
    public void TC30_FullValidGraph() {
        assertEquals("Graph created with nodes and edges",
                mockBuilder.buildGraph(true, true, true, false));
    }

    @Test
    public void TC31_NodesOnlyGraph() {
        assertEquals("Graph created with nodes only",
                mockBuilder.buildGraph(true, true, false, false));
    }

    @Test
    public void TC32_DatasetNotLoaded() {
        assertEquals("Error: Dataset not loaded",
                mockBuilder.buildGraph(false, false, false, false));
    }

    @Test
    public void TC33_InvalidDeviceData() {
        assertEquals("Error: Invalid device data",
                mockBuilder.buildGraph(true, false, false, false));
    }

    @Test
    public void TC34_NewDatasetLoaded() {
        assertEquals("Previous graph cleared; new graph created",
                mockBuilder.buildGraph(true, true, true, true));
    }
}
