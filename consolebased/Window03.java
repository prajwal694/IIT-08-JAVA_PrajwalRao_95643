package consolebased;

import java.io.*;
import java.util.*;

public class Window03 {
    
    private static Set<Customer> customers = new HashSet<>();
    private static LinkedList<Bill> bills = new LinkedList<>();
    private static LinkedList<Part> parts = new LinkedList<>();
    private static LinkedList<ServiceRequest> serviceRequests = new LinkedList<>();
    private static ServiceRequest currentService = null;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        loadData();
        mainMenu();
        saveData();
    }
    
    private static void mainMenu() {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("   VEHICLE SERVICE STATION");
            System.out.println("====================================");
            System.out.println("1. CUSTOMER Management");
            System.out.println("2. VEHICLE Management");
            System.out.println("3. SERVICE REQUEST");
            System.out.println("4. PARTS Management");
            System.out.println("5. BUSINESS Reports");
            System.out.println("6. EXIT");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    vehicleMenu();
                    break;
                case 3:
                    serviceMenu();
                    break;
                case 4:
                    partsMenu();
                    break;
                case 5:
                    businessMenu();
                    break;
                case 6:
                    System.out.println("Thank you for using Vehicle Service Station!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    // ==================== CUSTOMER MENU ====================
    private static void customerMenu() {
        while (true) {
            System.out.println("\n------- CUSTOMER MENU -------");
            System.out.println("1. Add Customer");
            System.out.println("2. Display All Customers");
            System.out.println("3. Display Specific Customer");
            System.out.println("4. Edit Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    displaySpecificCustomer();
                    break;
                case 4:
                    editCustomer();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void addCustomer() {
        System.out.println("\n--- Add Customer ---");
        System.out.print("Mobile Number: ");
        String mobile = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        if (mobile.isEmpty() || name.isEmpty()) {
            System.out.println("Error: Mobile number and name are required!");
            return;
        }
        
        Customer c = new Customer(mobile, name, address);
        if (customers.add(c)) {
            System.out.println("Customer added successfully!");
            saveData();
        } else {
            System.out.println("Error: Customer with this mobile number already exists!");
        }
    }
    
    private static void displayAllCustomers() {
        System.out.println("\n--- All Customers ---");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        
        System.out.println("Mobile\t\tName\t\tAddress");
        System.out.println("------------------------------------------------");
        for (Customer c : customers) {
            System.out.println(c.getMobile() + "\t\t" + c.getName() + "\t\t" + c.getAddress());
        }
    }
    
    private static void displaySpecificCustomer() {
        System.out.print("Enter Mobile Number: ");
        String mobile = scanner.nextLine();
        
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                System.out.println("\n--- Customer Details ---");
                System.out.println("Mobile: " + c.getMobile());
                System.out.println("Name: " + c.getName());
                System.out.println("Address: " + c.getAddress());
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    private static void editCustomer() {
        System.out.print("Enter Mobile Number: ");
        String mobile = scanner.nextLine();
        
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                System.out.println("Current Name: " + c.getName());
                System.out.print("New Name: ");
                String name = scanner.nextLine();
                System.out.print("New Address: ");
                String address = scanner.nextLine();
                
                c.setName(name);
                c.setAddress(address);
                System.out.println("Customer updated successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    private static void deleteCustomer() {
        System.out.print("Enter Mobile Number: ");
        String mobile = scanner.nextLine();
        
        Iterator<Customer> it = customers.iterator();
        while (it.hasNext()) {
            if (it.next().getMobile().equals(mobile)) {
                it.remove();
                System.out.println("Customer deleted successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    // ==================== VEHICLE MENU ====================
    private static void vehicleMenu() {
        while (true) {
            System.out.println("\n------- VEHICLE MENU -------");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Display All Vehicles");
            System.out.println("3. Display Customer's Vehicles");
            System.out.println("4. Edit Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    displayAllVehicles();
                    break;
                case 3:
                    displayCustomerVehicles();
                    break;
                case 4:
                    editVehicle();
                    break;
                case 5:
                    deleteVehicle();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void addVehicle() {
        System.out.println("\n--- Add Vehicle ---");
        System.out.print("Vehicle Number: ");
        String vehicleNum = scanner.nextLine().toUpperCase();
        System.out.print("Manufacturer: ");
        String manuf = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        
        System.out.println("\nAvailable Customers:");
        for (Customer c : customers) {
            System.out.println(c.getMobile() + " - " + c.getName());
        }
        System.out.print("Select Customer Mobile: ");
        String mobile = scanner.nextLine();
        
        if (vehicleNum.isEmpty() || manuf.isEmpty() || model.isEmpty() || mobile.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }
        
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                Vehicle v = new Vehicle(vehicleNum, manuf, model);
                c.addVehicle(v);
                System.out.println("Vehicle added successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    private static void displayAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        boolean found = false;
        
        System.out.println("Vehicle Number\tManufacturer\tModel\t\tCustomer Mobile");
        System.out.println("--------------------------------------------------------------------");
        for (Customer c : customers) {
            for (Vehicle v : c.getVehicles().values()) {
                System.out.println(v.getVehicleNumber() + "\t\t" + v.getManufacturer() + 
                                   "\t\t" + v.getModel() + "\t\t" + c.getMobile());
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No vehicles found.");
        }
    }
    
    private static void displayCustomerVehicles() {
        System.out.print("Enter Customer Mobile: ");
        String mobile = scanner.nextLine();
        
        boolean found = false;
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                System.out.println("\n--- Vehicles for " + c.getName() + " ---");
                for (Vehicle v : c.getVehicles().values()) {
                    System.out.println(v.getVehicleNumber() + " - " + v.getManufacturer() + " " + v.getModel());
                    found = true;
                }
                break;
            }
        }
        
        if (!found) {
            System.out.println("No vehicles found for this customer!");
        }
    }
    
    private static void editVehicle() {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNum = scanner.nextLine().toUpperCase();
        
        for (Customer c : customers) {
            if (c.getVehicles().containsKey(vehicleNum)) {
                Vehicle v = c.getVehicles().get(vehicleNum);
                System.out.println("Current Manufacturer: " + v.getManufacturer());
                System.out.print("New Manufacturer: ");
                String manuf = scanner.nextLine();
                System.out.print("New Model: ");
                String model = scanner.nextLine();
                
                v.setManufacturer(manuf);
                v.setModel(model);
                System.out.println("Vehicle updated successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }
    
    private static void deleteVehicle() {
        System.out.print("Enter Vehicle Number: ");
        String vehicleNum = scanner.nextLine().toUpperCase();
        
        for (Customer c : customers) {
            if (c.removeVehicle(vehicleNum)) {
                System.out.println("Vehicle deleted successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }
    
    // ==================== SERVICE MENU ====================
    private static void serviceMenu() {
        while (true) {
            System.out.println("\n------- SERVICE REQUEST MENU -------");
            System.out.println("1. Create New Service");
            System.out.println("2. Select Existing Service");
            System.out.println("3. Add Parts to Service");
            System.out.println("4. Add Maintenance");
            System.out.println("5. Add Oil/Additive");
            System.out.println("6. Prepare Bill");
            System.out.println("7. Get Payment");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createNewService();
                    break;
                case 2:
                    selectExistingService();
                    break;
                case 3:
                    addPartsToService();
                    break;
                case 4:
                    addMaintenance();
                    break;
                case 5:
                    addOilAdditive();
                    break;
                case 6:
                    prepareBill();
                    break;
                case 7:
                    getPayment();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void createNewService() {
        System.out.println("\n--- Create New Service ---");
        
        System.out.println("\nAvailable Customers:");
        for (Customer c : customers) {
            System.out.println(c.getMobile() + " - " + c.getName());
        }
        System.out.print("Select Customer Mobile: ");
        String mobile = scanner.nextLine();
        
        Customer selectedCustomer = null;
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                selectedCustomer = c;
                break;
            }
        }
        
        if (selectedCustomer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        System.out.println("\nVehicles for " + selectedCustomer.getName() + ":");
        for (Vehicle v : selectedCustomer.getVehicles().values()) {
            System.out.println(v.getVehicleNumber() + " - " + v.getManufacturer() + " " + v.getModel());
        }
        
        if (selectedCustomer.getVehicles().isEmpty()) {
            System.out.println("No vehicles found for this customer!");
            return;
        }
        
        System.out.print("Select Vehicle Number: ");
        String vehicleNum = scanner.nextLine().toUpperCase();
        
        if (!selectedCustomer.getVehicles().containsKey(vehicleNum)) {
            System.out.println("Vehicle not found!");
            return;
        }
        
        System.out.print("Service Description: ");
        String description = scanner.nextLine();
        
        currentService = new ServiceRequest(vehicleNum);
        currentService.setDescription(description);
        serviceRequests.add(currentService);
        
        System.out.println("New Service Created! Service ID: " + currentService.getServiceId());
        saveData();
    }
    
    private static void selectExistingService() {
        System.out.print("Enter Service ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (ServiceRequest sr : serviceRequests) {
            if (sr.getServiceId() == id) {
                currentService = sr;
                System.out.println("Service loaded: " + sr.getServiceId());
                System.out.println("Description: " + sr.getDescription());
                System.out.println("\nService Items:");
                for (ServiceItem item : sr.getItems()) {
                    System.out.println(item);
                }
                return;
            }
        }
        System.out.println("Service not found!");
    }
    
    private static void addPartsToService() {
        if (currentService == null) {
            System.out.println("Error: Create or select a service first!");
            return;
        }
        
        System.out.println("\n--- Available Parts ---");
        if (parts.isEmpty()) {
            System.out.println("No parts available. Please add parts first.");
            return;
        }
        
        for (Part p : parts) {
            System.out.println("ID: " + p.getPartId() + " - " + p.getPartName() + " - Rs." + p.getPrice());
        }
        
        System.out.print("Enter Part ID: ");
        int partId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        
        Part selectedPart = null;
        for (Part p : parts) {
            if (p.getPartId() == partId) {
                selectedPart = p;
                break;
            }
        }
        
        if (selectedPart == null) {
            System.out.println("Part not found!");
            return;
        }
        
        ServiceItem item = new ServiceItem(selectedPart.getPartName(), "Part", selectedPart.getPrice(), qty);
        currentService.addItem(item);
        System.out.println("Part added to service!");
        saveData();
    }
    
    private static void addMaintenance() {
        if (currentService == null) {
            System.out.println("Error: Create or select a service first!");
            return;
        }
        
        System.out.print("Maintenance Description: ");
        String desc = scanner.nextLine();
        System.out.print("Labor Charge: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        ServiceItem item = new ServiceItem(desc, "Maintenance", price, 1);
        currentService.addItem(item);
        System.out.println("Maintenance added!");
        saveData();
    }
    
    private static void addOilAdditive() {
        if (currentService == null) {
            System.out.println("Error: Create or select a service first!");
            return;
        }
        
        System.out.print("Oil/Additive Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        ServiceItem item = new ServiceItem(name, "Oil/Additive", price, 1);
        currentService.addItem(item);
        System.out.println("Oil/Additive added!");
        saveData();
    }
    
    private static void prepareBill() {
        if (currentService == null) {
            System.out.println("Error: Select a service first!");
            return;
        }
        
        double partsTotal = 0;
        double laborTotal = 0;
        
        for (ServiceItem item : currentService.getItems()) {
            if (item.getType().equals("Part") || item.getType().equals("Oil/Additive")) {
                partsTotal += item.getTotal();
            } else {
                laborTotal += item.getTotal();
            }
        }
        
        double subtotal = partsTotal + laborTotal;
        double tax = subtotal * 0.126;
        double total = subtotal + tax;
        
        Customer cust = null;
        Vehicle veh = null;
        for (Customer c : customers) {
            for (Vehicle v : c.getVehicles().values()) {
                if (v.getVehicleNumber().equals(currentService.getVehicleNumber())) {
                    cust = c;
                    veh = v;
                    break;
                }
            }
        }
        
        System.out.println("\n====================================");
        System.out.println("     VEHICLE SERVICE STATION");
        System.out.println("====================================");
        System.out.println("Service ID: " + currentService.getServiceId());
        System.out.println("Date: " + currentService.getDate());
        System.out.println("\nCustomer: " + cust.getName());
        System.out.println("Mobile: " + cust.getMobile());
        System.out.println("Vehicle: " + veh.getManufacturer() + " " + veh.getModel());
        System.out.println("Number: " + veh.getVehicleNumber());
        System.out.println("\n------------------------------------");
        System.out.println("DETAILS:");
        
        for (ServiceItem item : currentService.getItems()) {
            System.out.println(item.getName() + " (" + item.getType() + ")");
            System.out.println("  " + item.getQuantity() + " x Rs." + item.getPrice() + " = Rs." + item.getTotal());
        }
        
        System.out.println("\n------------------------------------");
        System.out.println("Labor Charges: Rs." + String.format("%.2f", laborTotal));
        System.out.println("Parts Total: Rs." + String.format("%.2f", partsTotal));
        System.out.println("Subtotal: Rs." + String.format("%.2f", subtotal));
        System.out.println("Tax (12.6%): Rs." + String.format("%.2f", tax));
        System.out.println("====================================");
        System.out.println("TOTAL: Rs." + String.format("%.2f", total));
        System.out.println("====================================");
    }
    
    private static void getPayment() {
        if (currentService == null) {
            System.out.println("Error: Select a service first!");
            return;
        }
        
        double total = 0;
        for (ServiceItem item : currentService.getItems()) {
            total += item.getTotal();
        }
        total = total * 1.126;
        
        System.out.print("Enter Payment Amount (Total: Rs." + String.format("%.2f", total) + "): ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        Customer cust = null;
        Vehicle veh = null;
        for (Customer c : customers) {
            for (Vehicle v : c.getVehicles().values()) {
                if (v.getVehicleNumber().equals(currentService.getVehicleNumber())) {
                    cust = c;
                    veh = v;
                    break;
                }
            }
        }
        
        Bill bill = new Bill(currentService.getServiceId(), cust.getName(), 
            cust.getMobile(), veh.getVehicleNumber(), total, amount, currentService.getDate());
        bills.add(bill);
        
        System.out.println("Payment recorded successfully!");
        saveData();
    }
    
    // ==================== PARTS MENU ====================
    private static void partsMenu() {
        while (true) {
            System.out.println("\n------- PARTS MENU -------");
            System.out.println("1. Add Part");
            System.out.println("2. Display All Parts");
            System.out.println("3. Edit Price");
            System.out.println("4. Delete Part");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addPart();
                    break;
                case 2:
                    displayAllParts();
                    break;
                case 3:
                    editPartPrice();
                    break;
                case 4:
                    deletePart();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void addPart() {
        System.out.println("\n--- Add Part ---");
        System.out.print("Part Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        int newId = parts.isEmpty() ? 1 : parts.get(parts.size() - 1).getPartId() + 1;
        parts.add(new Part(newId, name, price));
        
        System.out.println("Part added with ID: " + newId);
        saveData();
    }
    
    private static void displayAllParts() {
        System.out.println("\n--- All Parts ---");
        if (parts.isEmpty()) {
            System.out.println("No parts found.");
            return;
        }
        
        System.out.println("Part ID\tName\t\tPrice");
        System.out.println("--------------------------------");
        for (Part p : parts) {
            System.out.println(p.getPartId() + "\t" + p.getPartName() + "\t\tRs." + p.getPrice());
        }
    }
    
    private static void editPartPrice() {
        System.out.print("Enter Part ID: ");
        int partId = scanner.nextInt();
        scanner.nextLine();
        
        for (Part p : parts) {
            if (p.getPartId() == partId) {
                System.out.println("Current Price: " + p.getPrice());
                System.out.print("New Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                
                p.setPrice(price);
                System.out.println("Price updated successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Part not found!");
    }
    
    private static void deletePart() {
        System.out.print("Enter Part ID: ");
        int partId = scanner.nextInt();
        scanner.nextLine();
        
        Iterator<Part> it = parts.iterator();
        while (it.hasNext()) {
            if (it.next().getPartId() == partId) {
                it.remove();
                System.out.println("Part deleted successfully!");
                saveData();
                return;
            }
        }
        System.out.println("Part not found!");
    }
    
    // ==================== BUSINESS MENU ====================
    private static void businessMenu() {
        while (true) {
            System.out.println("\n------- BUSINESS REPORTS MENU -------");
            System.out.println("1. Today's Business");
            System.out.println("2. Given Date's Business");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    showTodayBusiness();
                    break;
                case 2:
                    showDateBusiness();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void showTodayBusiness() {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        double total = 0;
        
        for (Bill b : bills) {
            if (b.getDate().equals(today)) {
                total += b.getPaidAmount();
            }
        }
        
        System.out.println("\n--- Today's Business ---");
        System.out.println("Date: " + today);
        System.out.println("Total Collection: Rs." + String.format("%.2f", total));
    }
    
    private static void showDateBusiness() {
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        double total = 0;
        for (Bill b : bills) {
            if (b.getDate().equals(date)) {
                total += b.getPaidAmount();
            }
        }
        
        System.out.println("\n--- Business on " + date + " ---");
        System.out.println("Total Collection: Rs." + String.format("%.2f", total));
    }
    
    // ==================== DATA PERSISTENCE ====================
    private static void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.dat"));
            oos.writeObject(customers);
            oos.close();
            
            oos = new ObjectOutputStream(new FileOutputStream("parts.dat"));
            oos.writeObject(parts);
            oos.close();
            
            oos = new ObjectOutputStream(new FileOutputStream("services.dat"));
            oos.writeObject(serviceRequests);
            oos.close();
            
            oos = new ObjectOutputStream(new FileOutputStream("bills.dat"));
            oos.writeObject(bills);
            oos.close();
            
            // Save id counter
            int maxId = 1;
            for (ServiceRequest sr : serviceRequests) {
                if (sr.getServiceId() >= maxId) {
                    maxId = sr.getServiceId() + 1;
                }
            }
            ServiceRequest.setIdCounter(maxId);
            
            System.out.println("Data saved successfully!");
        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.dat"));
            customers = (Set<Customer>) ois.readObject();
            ois.close();
            
            ois = new ObjectInputStream(new FileInputStream("parts.dat"));
            parts = (LinkedList<Part>) ois.readObject();
            ois.close();
            
            ois = new ObjectInputStream(new FileInputStream("services.dat"));
            serviceRequests = (LinkedList<ServiceRequest>) ois.readObject();
            ois.close();
            
            ois = new ObjectInputStream(new FileInputStream("bills.dat"));
            bills = (LinkedList<Bill>) ois.readObject();
            ois.close();
            
            // Find max ID to continue counter
            int maxId = 1;
            for (ServiceRequest sr : serviceRequests) {
                if (sr.getServiceId() >= maxId) {
                    maxId = sr.getServiceId() + 1;
                }
            }
            ServiceRequest.setIdCounter(maxId);
            
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }
}
