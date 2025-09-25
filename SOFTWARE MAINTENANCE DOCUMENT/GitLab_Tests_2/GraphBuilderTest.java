import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * The Graph Builder Test Suite validates the correct categorization of devices as facilitated by the Device Distribution class.
 * This suite performs black box testing assuming the correct functionality of the Device Distribution class, which should
 * additionally be verified by white box unit tests due to the lack of insight into its internal workings from this context.
 * It is important to note that device categorization is managed via the DeviceCategory enum as per software design specifications (SDS),
 * which is consistently utilized throughout these tests.
 */
public class GraphBuilderTest {

    private GraphBuilder graphBuilder;  // Component under test that handles graph construction from datasets
    private DeviceDistribution deviceDist;  // Class for managing device categorisation within the graph
    private static final Path RESOURCE_PATH = Paths.get("src", "test", "resources");  // Base path to test dataset files

    @BeforeEach
    public void setUp() {
        // Set up fresh instances before each test to avoid test interference
        graphBuilder = new GraphBuilder(); 
    }
    
    @Test
    @DisplayName("Ensure graph is correctly initialised from dataset")
    void readDatasetTestNull() {
        // Arrange: Prepare a file that is expected to initialize a graph without errors
        File oneCategoryFile = RESOURCE_PATH.resolve("oneCatRouterFile.csv").toFile(); 

        // Act: Load the dataset and retrieve the constructed graph
        graphBuilder.readDataset(oneCategoryFile); 
        Graph graph = graphBuilder.getGraph(); 

        // Assert: Check that the graph is properly initialised and not null
        assertNotNull(graph, "Graph must be initialized and not null after reading dataset.");
    }

    @Test
    @DisplayName("readDataset() should accurately categorise data into a graph with only routers, depends on the correct functionality of the DeviceDistribution class")
    void readDatasetTestOneCategory() {
        // Arrange: Setup with a dataset file containing only router devices
        File oneCategoryFile = RESOURCE_PATH.resolve("oneCatRouterFile.csv").toFile(); 
        DeviceCategory category = DeviceCategory.ROUTER; 
        int numRouters = 10; // Expected number of routers

        // Act: Load the dataset and perform categorization using DeviceDistribution
        graphBuilder.readDataset(oneCategoryFile); 
        Graph graph = graphBuilder.getGraph(); 
        deviceDist = new DeviceDistribution(graph);  

        // Assert: Validate that the number of routers categorized matches expected count
        int actualRouterCount = deviceDist.countByCategory(category);
        Assertions.assertEquals(numRouters, actualRouterCount, "10 routers should be counted by the graph builder");
    }

    @Test
    @DisplayName("readDataset() should not categorise non-existent whiteware in a router-only dataset")
    void readDatasetTestNotPresentCategory() {
        // Arrange: Prepare a dataset file containing only routers, testing for non-existent category
        File oneCategoryFile = RESOURCE_PATH.resolve("oneCatRouterFile.csv").toFile(); 
        DeviceCategory category = DeviceCategory.WHITEWARE; 
        int expectedCount = 0; // Expected number of whiteware

        // Act: Load the dataset and retrieve the graph
        graphBuilder.readDataset(oneCategoryFile); 
        Graph graph = graphBuilder.getGraph(); 
        deviceDist = new DeviceDistribution(graph);  

        // Assert: Verify that no whiteware devices are mistakenly categorized
        int actualWhitewareCount = deviceDist.countByCategory(category);
        Assertions.assertEquals(expectedCount, actualWhitewareCount, "0 whiteware should be counted by the graph builder");
    }

    @Test
    @DisplayName("readDataset() should accurately categorise data and verify counts for two categories")
    void readDatasetTestTwoCategories() {
        // Arrange: Setup with a dataset file that contains both controllers and appliances
        File twoCategoryFile = RESOURCE_PATH.resolve("twoCatFile.csv").toFile();
        Map<DeviceCategory, Integer> expectedCounts = new EnumMap<>(DeviceCategory.class);
        expectedCounts.put(DeviceCategory.CONTROLLER, 21);  // Expected count of controllers
        expectedCounts.put(DeviceCategory.APPLIANCES, 54);  // Expected count of appliances

        // Act: Load the dataset and perform categorization
        graphBuilder.readDataset(twoCategoryFile);
        Graph graph = graphBuilder.getGraph();
        deviceDist = new DeviceDistribution(graph);

        // Collect actual counts from the categorized graph
        Map<DeviceCategory, Integer> actualCounts = new EnumMap<>(DeviceCategory.class);
        actualCounts.put(DeviceCategory.CONTROLLER, deviceDist.countByCategory(DeviceCategory.CONTROLLER));
        actualCounts.put(DeviceCategory.APPLIANCES, deviceDist.countByCategory(DeviceCategory.APPLIANCES));

        // Assert: Compare expected and actual counts for correctness
        Assertions.assertEquals(expectedCounts, actualCounts, "Counts for two device categories should match expected values");
    }

    @Test
    @DisplayName("readDataset() should verify the correct number of connections between nodes in the dataset")
    void readDatasetTestConnections() {
        // Arrange: Prepare a dataset file intended to test graph connectivity
        File connectionsCategoryFile = RESOURCE_PATH.resolve("connectionsFile.csv").toFile();

        // Expected number of connections to be verified against the constructed graph
        int expectedConnections = 50; 

        // Act: Load the dataset and construct the graph
        graphBuilder.readDataset(connectionsCategoryFile);
        Graph graph = graphBuilder.getGraph();

        // Assert: Validate the total number of connections (edges) in the graph matches the expected count
        int actualConnections = graph.getEdgeCount();
        Assertions.assertEquals(expectedConnections, actualConnections,
            "The number of connections (edges) in the graph should exactly match the expected value. If they do not match, verify the input dataset and graph construction logic.");
    }
}
