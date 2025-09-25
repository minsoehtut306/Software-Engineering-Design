package sec.Main;

/**
 * Enumeration of predefined categories for devices in the ESGP system.
 * 
 * These categories help classify devices by their purpose or function
 * when processing and visualizing datasets.
 */
public enum DeviceCategory {

    /** Devices used for lighting (e.g., smart bulbs) */
    LIGHTING,

    /** Network-related devices (e.g., routers, modems) */
    NETWORK,

    /** Devices that act as controllers or hubs */
    CONTROLLER,

    /** Smart appliances (e.g., kettles, toasters) */
    APPLIANCE,

    /** Devices that do not match known categories */
    OTHER
}
