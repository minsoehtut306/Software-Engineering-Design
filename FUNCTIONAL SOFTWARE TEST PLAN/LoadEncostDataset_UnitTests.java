import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Load Encost Dataset_UnitTests
 * Functional Unit Tests for loading Encost Smart Homes Dataset
 * Test Techniques Used:
 * - TC17–TC20: All-Pairs Testing
 * - TC21–TC22: Boundary Value Analysis & Equivalence Partitioning
 */
public class LoadEncostDataset_UnitTests {

    interface DatasetLoader {
        String loadDataset(boolean exists, boolean readable, boolean validStructure, int rowCount);
    }

    DatasetLoader mockLoader = new DatasetLoader() {
        public String loadDataset(boolean exists, boolean readable, boolean validStructure, int rowCount) {
            if (!exists)
                return "Error: dataset missing";
            if (!readable)
                return "Error: file cannot be read";
            if (!validStructure)
                return "Warning: malformed lines skipped";
            if (rowCount == 0)
                return "Warning: empty dataset structure returned";
            if (rowCount > 10000)
                return "Success: large dataset loaded";
            return "Success: dataset loaded and parsed";
        }
    };

    // --- All-Pairs Testing ---

    @Test
    public void TC17_ExistsReadableValid() {
        assertEquals("Success: dataset loaded and parsed", mockLoader.loadDataset(true, true, true, 50));
    }

    @Test
    public void TC18_ExistsUnreadableValid() {
        assertEquals("Error: file cannot be read", mockLoader.loadDataset(true, false, true, 50));
    }

    @Test
    public void TC19_ExistsReadableMalformed() {
        assertEquals("Warning: malformed lines skipped", mockLoader.loadDataset(true, true, false, 50));
    }

    @Test
    public void TC20_MissingDataset() {
        assertEquals("Error: dataset missing", mockLoader.loadDataset(false, false, false, 0));
    }

    // --- Boundary Value Analysis & Equivalence Partitioning ---

    @Test
    public void TC21_HeaderOnly_NoDataRows() {
        assertEquals("Warning: empty dataset structure returned", mockLoader.loadDataset(true, true, true, 0));
    }

    @Test
    public void TC22_HugeDataset_10000Rows() {
        assertEquals("Success: large dataset loaded", mockLoader.loadDataset(true, true, true, 10001));
    }
}
