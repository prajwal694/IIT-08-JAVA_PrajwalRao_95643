package consolebased;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String vehicleNumber;
    private String manufacturer;
    private String model;
    
    public Vehicle(String vehicleNumber, String manufacturer, String model) {
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }
    
    public String getVehicleNumber() { return vehicleNumber; }
    public String getManufacturer() { return manufacturer; }
    public String getModel() { return model; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public void setModel(String model) { this.model = model; }
    
    @Override
    public String toString() {
        return "Vehicle Number: " + vehicleNumber + ", Manufacturer: " + manufacturer + ", Model: " + model;
    }
}
