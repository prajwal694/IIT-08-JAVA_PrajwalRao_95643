package consolebased;

import java.io.Serializable;

public class Bill implements Serializable {
    private int serviceId;
    private String customerName;
    private String customerMobile;
    private String vehicleNumber;
    private String date;
    private double totalAmount;
    private double paidAmount;
    
    public Bill(int serviceId, String customerName, String customerMobile,
            String vehicleNumber, double totalAmount, double paidAmount, String date) {
        this.serviceId = serviceId;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.vehicleNumber = vehicleNumber;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.date = date;
    }
    
    public int getServiceId() { return serviceId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerMobile() { return customerMobile; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getDate() { return date; }
    public double getTotalAmount() { return totalAmount; }
    public double getPaidAmount() { return paidAmount; }
    
    @Override
    public String toString() {
        return "Service ID: " + serviceId + ", Customer: " + customerName + 
               ", Vehicle: " + vehicleNumber + ", Total: " + totalAmount + 
               ", Paid: " + paidAmount + ", Date: " + date;
    }
}
