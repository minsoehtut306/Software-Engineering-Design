import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Categorise Devices_UnitTests
 * Functional Unit Tests for TC23–TC29
 * Techniques: Equivalence Partitioning, Error Guessing
 */
public class CategoriseDevices_UnitTests {

    interface DeviceClassifier {
        String categorise(String type);
    }

    DeviceClassifier mockClassifier = new DeviceClassifier() {
        public String categorise(String type) {
            if (type == null || type.isEmpty())
                return "Unknown";
            switch (type.toLowerCase()) {
                case "light":
                    return "Lighting";
                case "sensor":
                    return "Sensor";
                case "camera":
                    return "Camera";
                case "thermostat":
                    return "Climate";
                default:
                    return "Unknown";
            }
        }
    };

    // --- Equivalence Partitioning ---

    @Test
    public void TC23_DeviceTypeLight() {
        assertEquals("Lighting", mockClassifier.categorise("light"));
    }

    @Test
    public void TC24_DeviceTypeSensor() {
        assertEquals("Sensor", mockClassifier.categorise("sensor"));
    }

    @Test
    public void TC25_DeviceTypeCamera() {
        assertEquals("Camera", mockClassifier.categorise("camera"));
    }

    @Test
    public void TC26_DeviceTypeThermostat() {
        assertEquals("Climate", mockClassifier.categorise("thermostat"));
    }

    // --- Error Guessing ---

    @Test
    public void TC27_UnknownType() {
        assertEquals("Unknown", mockClassifier.categorise("xyz"));
    }

    @Test
    public void TC28_MissingTypeField() {
        assertEquals("Unknown", mockClassifier.categorise(null));
    }

    @Test
    public void TC29_EmptyTypeString() {
        assertEquals("Unknown", mockClassifier.categorise(""));
    }
}
