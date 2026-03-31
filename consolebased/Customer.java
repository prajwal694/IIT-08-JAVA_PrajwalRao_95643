package consolebased;

import java.io.Serializable;
import java.util.HashMap;

public class Customer implements Serializable {
    private String mobile;
    private String name;
    private String address;
    private HashMap<String, Vehicle> vehicles;
    
    public Customer(String mobile, String name, String address) {
        this.mobile = mobile;
        this.name = name;
        this.address = address;
        this.vehicles = new HashMap<>();
    }
    
    public String getMobile() { return mobile; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public HashMap<String, Vehicle> getVehicles() { return vehicles; }
    public void addVehicle(Vehicle v) { vehicles.put(v.getVehicleNumber(), v); }
    public boolean removeVehicle(String num) { return vehicles.remove(num) != null; }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            return mobile.equals(((Customer) o).mobile);
        }
        return false;
    }
    
    @Override
    public int hashCode() { return mobile.hashCode(); }
    
    @Override
    public String toString() {
        return "Mobile: " + mobile + ", Name: " + name + ", Address: " + address;
    }
}
