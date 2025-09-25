import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the GraphVisualisation class.
 */
public class GraphVisualisationTest {

    private GraphVisualisation gv;

    @BeforeEach
    void setUp() {
        gv = new GraphVisualisation();
    }

    @Test
    @DisplayName("Graph visualisation runs without exceptions")
    void testDisplayGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        GraphVisualisation gv = new GraphVisualisation();
        assertDoesNotThrow(() -> gv.displayGraph(),
            "Graph visualisation should run without throwing exceptions.");
    }
}

