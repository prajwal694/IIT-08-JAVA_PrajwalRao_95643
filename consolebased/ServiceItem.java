package consolebased;

import java.io.Serializable;

public class ServiceItem implements Serializable {
    private String name;
    private String type;
    private double price;
    private int quantity;
    
    public ServiceItem(String name, String type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return price * quantity; }
    
    @Override
    public String toString() {
        return name + " (" + type + ") - " + quantity + " x " + price + " = " + getTotal();
    }
}
