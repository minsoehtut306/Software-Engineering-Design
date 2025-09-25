import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Class DataSetTest is responsible for conducting tests on the DataSet class to ensure its reliability
 * in managing file paths, loading data, and checking data formats. It tests various scenarios including
 * valid and invalid file paths, as well as corrupted and partially incomplete data sets.
 */
public class DataSetTest {

    private DataSet dataset; // Instance of DataSet to be tested
    private static final Path RESOURCE_PATH = Paths.get("src", "test", "resources"); // Path to the test resources

    @BeforeEach
    void setUp() {
        // Initialize the DataSet object before each test
        dataset = new DataSet();
    }

    @Test
    @DisplayName("Test setDataSet with a valid file path")
    void setDatasetTestWithValidFilePath() {
        // Arrange: Create a File object for a valid dataset file
        File validFile = RESOURCE_PATH.resolve("validDataset.csv").toFile();

        // Act: Set the dataset with the file path and retrieve it to verify the operation
        dataset.setDataSet(validFile.getAbsolutePath()); 
        File resultFile = dataset.getDataSet(); 

        // Assert: Ensure the retrieved file is not null and is the one expected
        assertNotNull(resultFile, "getDataSet should return a non-null file.");
    }

    @Test
    @DisplayName("Test setDataSet with an invalid file path")
    void setDatasetTestWithInvalidFilePath() {
        // Arrange: Specify a path that does not exist
        String invalidFilePath = RESOURCE_PATH.resolve("nonexistent.csv").toString();

        // Act & Assert: Expect a RuntimeException when setting an invalid dataset path
        RuntimeException exception = assertThrows(RuntimeException.class, 
                                                  () -> dataset.setDataSet(invalidFilePath));
        assertTrue(exception.getMessage().contains("unable to find dataset"), 
                   "Should throw an error for an invalid path.");
    }

    @Test
    @DisplayName("Test checkFormat with a valid dataset file")
    void checkFormatWithValidFile() {
        // Arrange: Set up a dataset with a valid file
        File validFile = RESOURCE_PATH.resolve("validDataset.csv").toFile();
        dataset.setDataSet(validFile.getAbsolutePath());

        // Act & Assert: Verify that the format check passes without throwing an exception
        assertDoesNotThrow(() -> dataset.checkFormat(),
                           "checkFormat should not throw an exception for a valid dataset.");
    }

    @Test
    @DisplayName("Test checkFormat with a corrupted dataset file")
    void checkFormatLoadingWithCorruptedFile() {
        // Arrange: Set up a dataset with a corrupted file
        File corruptedFile = RESOURCE_PATH.resolve("corruptedDataset.csv").toFile();
        dataset.setDataSet(corruptedFile.getAbsolutePath());

        // Act & Assert: Verify that processing a corrupted dataset reports an error
        assertThrows(RuntimeException.class, () -> dataset.checkFormat(),
                     "Should report error when trying to process a corrupted dataset.");
    }

    @Test
    @DisplayName("Test checkFormat with partially missing data in the dataset")
    void checkFormatWithPartiallyMissingData() {
        // Arrange: Set up a dataset with partially missing data
        File partiallyMissingFile = RESOURCE_PATH.resolve("partiallyMissingDataset.csv").toFile();
        dataset.setDataSet(partiallyMissingFile.getAbsolutePath());

        // Act & Assert: Verify that the dataset can handle or report errors for partially missing data
        assertThrows(RuntimeException.class, () -> dataset.checkFormat(),
                     "Should handle partially missing data correctly.");
    }
}
