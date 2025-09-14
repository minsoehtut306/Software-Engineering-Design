import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * GraphVisualisation_UnitTests
 * Functional Unit Tests for SRS 4.8 – Graph Visualisation
 * Covers Test Cases TC35–TC39
 * Techniques Used:
 * - Boundary Value Analysis
 * - Error Guessing
 */
public class GraphVisualisation_UnitTests {

    interface GraphVisualizer {
        /**
         * Simulates a visualisation attempt and returns a summary result.
         *
         * @param nodeCount      Number of nodes in the graph
         * @param edgeCount      Number of edges in the graph
         * @param graphAvailable Whether a graph was built/loaded
         * @return Message describing result of visualisation
         */
        String visualise(int nodeCount, int edgeCount, boolean graphAvailable);
    }

    GraphVisualizer mockVisualizer = new GraphVisualizer() {
        public String visualise(int nodeCount, int edgeCount, boolean graphAvailable) {
            if (!graphAvailable)
                return "Error: Graph not available";
            if (nodeCount == 0)
                return "Error: Graph invalid or incomplete";
            if (nodeCount == 1 && edgeCount == 0)
                return "Single node visualised; no connections drawn";
            if (edgeCount == 0)
                return "Only nodes displayed; no connections";
            if (nodeCount > 1000)
                return "Large graph visualised successfully";
            return "Graph visualised with nodes and connections";
        }
    };

    // --- Boundary Value Analysis ---

    @Test
    public void TC35_SingleNodeNoEdges() {
        assertEquals("Single node visualised; no connections drawn",
                mockVisualizer.visualise(1, 0, true));
    }

    @Test
    public void TC36_ThousandNodesWithEdges() {
        assertEquals("Large graph visualised successfully",
                mockVisualizer.visualise(1001, 1500, true));
    }

    // --- Error Guessing ---

    @Test
    public void TC37_NoGraphAvailable() {
        assertEquals("Error: Graph not available",
                mockVisualizer.visualise(0, 0, false));
    }

    @Test
    public void TC38_GraphBuildFailed() {
        assertEquals("Error: Graph invalid or incomplete",
                mockVisualizer.visualise(0, 0, true));
    }

    // --- Boundary + Error Combination ---

    @Test
    public void TC39_DisconnectedNodesOnly() {
        assertEquals("Only nodes displayed; no connections",
                mockVisualizer.visualise(10, 0, true));
    }
}
