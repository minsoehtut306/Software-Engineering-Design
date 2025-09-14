import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ESGP Feature Options
 * Functional Unit Tests for TC12–TC16
 * Test Technique: Decision Table Testing
 * Description: Validates user access to feature options based on user type.
 */
public class ESGPFeatureOptions_UnitTests {

    // Contract interface
    interface FeatureAccessControl {
        boolean isAccessAllowed(String userType, String feature);
    }

    // Mock implementation
    FeatureAccessControl mockAccess = new FeatureAccessControl() {
        public boolean isAccessAllowed(String userType, String feature) {
            if ("encost".equals(userType)) {
                return feature.equals("upload") || feature.equals("visualise") || feature.equals("stats");
            } else if ("community".equals(userType)) {
                return feature.equals("visualise");
            }
            return false;
        }
    };

    @Test
    public void TC12_EncostUploadAllowed() {
        assertTrue(mockAccess.isAccessAllowed("encost", "upload"));
    }

    @Test
    public void TC13_EncostVisualiseAllowed() {
        assertTrue(mockAccess.isAccessAllowed("encost", "visualise"));
    }

    @Test
    public void TC14_EncostStatsAllowed() {
        assertTrue(mockAccess.isAccessAllowed("encost", "stats"));
    }

    @Test
    public void TC15_CommunityVisualiseAllowed() {
        assertTrue(mockAccess.isAccessAllowed("community", "visualise"));
    }

    @Test
    public void TC16_CommunityUploadDenied() {
        assertFalse(mockAccess.isAccessAllowed("community", "upload"));
    }
}
