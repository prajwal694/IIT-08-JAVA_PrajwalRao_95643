package consolebased;

import java.io.Serializable;

public class Part implements Serializable {
    private int partId;
    private String name;
    private double price;
    
    public Part(int partId, String name, double price) {
        this.partId = partId;
        this.name = name;
        this.price = price;
    }
    
    public int getPartId() { return partId; }
    public String getPartName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public void setName(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return "Part ID: " + partId + ", Name: " + name + ", Price: " + price;
    }
}
