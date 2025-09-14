package sec.Test;

import org.junit.jupiter.api.Test;
import sec.Main.Device;
import sec.Main.DeviceCategory;
import sec.Main.DeviceManager;
import sec.Main.SummaryReporter;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SummaryReporterTest {

    @Test
    public void testCategoryCounts() {
        DeviceManager dm = new DeviceManager();
        dm.loadDataset("resources/Encost Smart Homes Dataset (small).txt");

        SummaryReporter sr = new SummaryReporter();
        Map<DeviceCategory, Long> counts = sr.getCategoryCounts(dm.getDevices());

        assertEquals(1, counts.get(DeviceCategory.LIGHTING));
        assertEquals(1, counts.get(DeviceCategory.APPLIANCE));
        assertEquals(1, counts.get(DeviceCategory.NETWORK));
        assertEquals(1, counts.get(DeviceCategory.CONTROLLER));
    }

    @Test
    public void testHouseholdCounts() {
        DeviceManager dm = new DeviceManager();
        dm.loadDataset("resources/Encost Smart Homes Dataset (small).txt");

        SummaryReporter sr = new SummaryReporter();
        Map<String, Long> householdCounts = sr.getHouseholdCounts(dm.getDevices());

        assertEquals(1, householdCounts.size());
        assertTrue(householdCounts.containsKey("WKO-1234"));
    }
}
