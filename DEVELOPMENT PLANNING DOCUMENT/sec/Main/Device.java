package sec.Main;

/**
 * Represents a device in the ESGP system with relevant properties like
 * ID, name, type, household, network connectivity, and category.
 */
public class Device {

    /** Unique identifier for the device */
    public String deviceID;

    /** Full name of the device */
    public String deviceName;

    /** Type of device (e.g., Router, Kettle, Light bulb) */
    public String deviceType;

    /** Household ID this device belongs to */
    public String householdID;

    /** The router this device is connected to */
    public String routerConnection;

    /** Whether the device can send data */
    public boolean sends;

    /** Whether the device can receive data */
    public boolean receives;

    /** Category of the device (e.g., NETWORK, APPLIANCE, LIGHTING, CONTROLLER) */
    public DeviceCategory category;

    /**
     * Constructs a new Device with its basic attributes.
     *
     * @param id         Device ID
     * @param name       Device name
     * @param type       Device type
     * @param household  Household ID
     * @param router     Connected router ID
     * @param sends      Whether the device sends data
     * @param receives   Whether the device receives data
     */
    public Device(String id, String name, String type, String household, String router, boolean sends, boolean receives) {
        this.deviceID = id;
        this.deviceName = name;
        this.deviceType = type;
        this.householdID = household;
        this.routerConnection = router;
        this.sends = sends;
        this.receives = receives;
    }

    /**
     * Returns the category assigned to this device.
     *
     * @return DeviceCategory enum value
     */
    public DeviceCategory getCategory() {
        return this.category;
    }
}
