package consolebased;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ServiceRequest implements Serializable {
    private static int idCounter = 1;
    private int serviceId;
    private String vehicleNumber;
    private String date;
    private String description;
    private LinkedList<ServiceItem> items;
    
    public ServiceRequest(String vehicleNumber) {
        this.serviceId = idCounter++;
        this.vehicleNumber = vehicleNumber;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.description = "";
        this.items = new LinkedList<>();
    }
    
    public static int getIdCounter() { return idCounter; }
    public static void setIdCounter(int idCounter) { ServiceRequest.idCounter = idCounter; }
    public int getServiceId() { return serviceId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LinkedList<ServiceItem> getItems() { return items; }
    public void addItem(ServiceItem item) { items.add(item); }
    
    @Override
    public String toString() {
        return "Service ID: " + serviceId + ", Vehicle: " + vehicleNumber + ", Date: " + date;
    }
}
