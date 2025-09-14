package sec.Test;

import org.junit.jupiter.api.Test;
import sec.Main.Device;
import sec.Main.DeviceCategory;
import sec.Main.DeviceManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DeviceManager class.
 * These tests verify dataset loading, device field parsing, and category classification.
 */
public class DeviceManagerTest {

    /**
     * Verifies that a valid dataset file is loaded correctly and devices are populated.
     */
    @Test
    public void testValidDatasetLoadsCorrectly() {
        DeviceManager dm = new DeviceManager();
        boolean success = dm.loadDataset("resources/Encost Smart Homes Dataset (small).txt");

        assertTrue(success, "Dataset should load successfully");

        List<Device> devices = dm.getDevices();
        assertEquals(4, devices.size(), "Should load 4 devices");
    }

    /**
     * Verifies that fields of the first device are parsed correctly from the dataset.
     */
    @Test
    public void testDeviceFieldsAreParsedCorrectly() {
        DeviceManager dm = new DeviceManager();
        dm.loadDataset("resources/Encost Smart Homes Dataset (small).txt");

        List<Device> devices = dm.getDevices();
        Device first = devices.get(0);

        assertEquals("EWR-1234", first.deviceID);
        assertEquals("Encost Router 360", first.deviceName);
        assertEquals("Router", first.deviceType);
        assertEquals("WKO-1234", first.householdID);
    }

    /**
     * Verifies that the device category classification logic works (e.g., bulbs → LIGHTING).
     */
    @Test
    public void testDeviceCategoryClassification() {
        DeviceManager dm = new DeviceManager();
        dm.loadDataset("resources/Encost Smart Homes Dataset (small).txt");

        List<Device> devices = dm.getDevices();
        Device light = devices.stream()
            .filter(d -> d.deviceName.contains("Bulb"))
            .findFirst().orElse(null);

        assertNotNull(light, "Expected a bulb device in dataset");
        assertEquals(DeviceCategory.LIGHTING, light.getCategory());
    }
}
