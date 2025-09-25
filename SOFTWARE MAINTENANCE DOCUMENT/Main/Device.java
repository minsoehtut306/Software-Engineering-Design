public class Device {

	public String deviceID;
	private String dateConnected;
	private String deviceName;
	public DeviceCategory deviceType;
	private String householdID;
	public String routerConnection;
	public Boolean send;
	public Boolean recieve;

	public Device (String deviceID, String dateConnected, String deviceName, DeviceCategory deviceType, String householdID, String routerConnection, Boolean send, Boolean recieve) {
		this.deviceID = deviceID;
		this.dateConnected = dateConnected;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.householdID = householdID;
		this.routerConnection = routerConnection;
		this.send = send;
		this.recieve = recieve;
	}
}