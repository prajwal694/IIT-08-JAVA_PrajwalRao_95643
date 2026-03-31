package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Window1 extends JFrame {
    
   
    private Set<Customer> customers = new HashSet<>();
    private LinkedList<Bill> bills = new LinkedList<>();
    private LinkedList<Part> parts = new LinkedList<>();
    private LinkedList<ServiceRequest> serviceRequests = new LinkedList<>();
    private ServiceRequest currentService = null;
    
   
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    
   
    private Customer selectedCustomer = null;
    private Vehicle selectedVehicle = null;
    private Part selectedPart = null;
    private ServiceRequest selectedService = null;
     
    public Window1() {
        setTitle("Vehicle Service Station");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadData();
        
      
        setLayout(new BorderLayout());
        
        
        createSidebar();
        
      
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        
        
        contentPanel.add(createEmptyPanel(), "EMPTY");
        contentPanel.add(createCustomerPanel(), "CUSTOMER");
        contentPanel.add(createVehiclePanel(), "VEHICLE");
        contentPanel.add(createServicePanel(), "SERVICE");
        contentPanel.add(createPartsPanel(), "PARTS");
        contentPanel.add(createBusinessPanel(), "BUSINESS");
        
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveData();
            }
        });
        
        setLocationRelativeTo(null);
    }
    
    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(220, 0));
        sidebarPanel.setBackground(new Color(45, 62, 80));
        
       
        JLabel title = new JLabel("SERVICE STATION");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        sidebarPanel.add(title);
        
        
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(200, 2));
        sep.setForeground(new Color(70, 130, 180));
        sidebarPanel.add(sep);
        
        
        addMenuSection("CUSTOMER", new String[]{"Add Customer", "Display All", "Display Specific", "Edit", "Delete"}, e -> showCustomerPanel("ADD"));
        
        addMenuSection("VEHICLE", new String[]{"Add Vehicle", "Display All", "Display Customer's Vehicles", "Edit", "Delete"}, e -> showVehiclePanel("ADD"));
        
        addMenuSection("SERVICE REQUEST", new String[]{"Select Customer Vehicle", "Process Request", "Prepare & Display Bill", "Get Payment"}, e -> showServicePanel("SELECT"));
        
        addMenuSection("PARTS", new String[]{"Add Part", "Display All", "Edit Price", "Delete"}, e -> showPartsPanel("ADD"));
        
        addMenuSection("BUSINESS", new String[]{"Today's Business", "Given Date's Business"}, e -> showBusinessPanel("TODAY"));
        
        
        JButton exitBtn = createSidebarButton("EXIT");
        exitBtn.addActionListener(e -> {
            saveData();
            System.exit(0);
        });
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(exitBtn);
        sidebarPanel.add(Box.createVerticalStrut(20));
    }
    
    private void addMenuSection(String title, String[] items, ActionListener defaultAction) {
        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 12));
        sectionTitle.setForeground(new Color(100, 149, 237));
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        sidebarPanel.add(sectionTitle);
        
        for (String item : items) {
            JButton btn = createMenuButton(item);
            btn.addActionListener(defaultAction);
            sidebarPanel.add(btn);
        }
    }
    
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 10));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setForeground(new Color(200, 200, 200));
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setMaximumSize(new Dimension(220, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btn.setForeground(new Color(200, 200, 200));
            }
        });
        
        return btn;
    }
    
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setForeground(new Color(255, 99, 71));
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private JPanel createEmptyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Welcome to Vehicle Service Station", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(new Color(100, 149, 237));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    
    
    private JPanel customerPanel;
    private JTextField custMobileField, custNameField, custAddressField;
    private JTable customerTable;
    private DefaultTableModel customerTableModel;
    private String customerMode = "ADD";
    
    private JPanel createCustomerPanel() {
        customerPanel = new JPanel(new BorderLayout());
        
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 122, 183));
        JLabel headerLabel = new JLabel("CUSTOMER");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        customerPanel.add(headerPanel, BorderLayout.NORTH);
        
       
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        custMobileField = new JTextField();
        custNameField = new JTextField();
        custAddressField = new JTextField();
        
        formPanel.add(new JLabel("Mobile Number:"));
        formPanel.add(custMobileField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(custNameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(custAddressField);
        
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        
        JButton addBtn = createActionButton("Add Customer");
        JButton editBtn = createActionButton("Edit");
        JButton deleteBtn = createActionButton("Delete");
        JButton displayBtn = createActionButton("Display All");
        
        addBtn.addActionListener(e -> saveCustomer("ADD"));
        editBtn.addActionListener(e -> saveCustomer("EDIT"));
        deleteBtn.addActionListener(e -> saveCustomer("DELETE"));
        displayBtn.addActionListener(e -> {
            loadAllCustomers();
            customerMode = "DISPLAY";
            updateCustomerButtons();
        });
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);
        
      
        customerTableModel = new DefaultTableModel(new String[]{"Mobile", "Name", "Address"}, 0);
        customerTable = new JTable(customerTableModel);
        customerTable.getTableHeader().setBackground(new Color(51, 122, 183));
        customerTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(customerTable);
        
        customerTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = customerTable.getSelectedRow();
                if (row >= 0) {
                    custMobileField.setText(customerTableModel.getValueAt(row, 0).toString());
                    custNameField.setText(customerTableModel.getValueAt(row, 1).toString());
                    custAddressField.setText(customerTableModel.getValueAt(row, 2).toString());
                    customerMode = "EDIT";
                    updateCustomerButtons();
                }
            }
        });
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        customerPanel.add(topPanel, BorderLayout.NORTH);
        customerPanel.add(tableScroll, BorderLayout.CENTER);
        
        return customerPanel;
    }
    
    private void showCustomerPanel(String mode) {
        customerMode = mode;
        updateCustomerButtons();
        if (mode.equals("DISPLAY")) {
            loadAllCustomers();
        }
        contentLayout.show(contentPanel, "CUSTOMER");
    }
    
    private void updateCustomerButtons() {
        custMobileField.setEditable(customerMode.equals("ADD"));
    }
    
    private void loadAllCustomers() {
        customerTableModel.setRowCount(0);
        for (Customer c : customers) {
            customerTableModel.addRow(new Object[]{c.getMobile(), c.getName(), c.getAddress()});
        }
    }
    
    private void saveCustomer(String operation) {
        String mobile = custMobileField.getText();
        String name = custNameField.getText();
        String address = custAddressField.getText();
        
        if (mobile.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter mobile number and name!");
            return;
        }
        
        if (operation.equals("ADD")) {
            Customer c = new Customer(mobile, name, address);
            if (customers.add(c)) {
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
                clearCustomerFields();
                saveData();
            } else {
                JOptionPane.showMessageDialog(this, "Customer with this mobile already exists!");
            }
        } else if (operation.equals("EDIT")) {
            for (Customer c : customers) {
                if (c.getMobile().equals(mobile)) {
                    c.setName(name);
                    c.setAddress(address);
                    JOptionPane.showMessageDialog(this, "Customer updated!");
                    saveData();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Customer not found!");
        } else if (operation.equals("DELETE")) {
            Iterator<Customer> it = customers.iterator();
            while (it.hasNext()) {
                if (it.next().getMobile().equals(mobile)) {
                    it.remove();
                    JOptionPane.showMessageDialog(this, "Customer deleted!");
                    clearCustomerFields();
                    saveData();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Customer not found!");
        }
    }
    
    private void clearCustomerFields() {
        custMobileField.setText("");
        custNameField.setText("");
        custAddressField.setText("");
    }
    
    
    private JPanel vehiclePanel;
    private JTextField vehicleNumField, vehicleModelField, vehicleManufField;
    private JComboBox<String> vehicleCustCombo;
    private JTable vehicleTable;
    private DefaultTableModel vehicleTableModel;
    private String vehicleMode = "ADD";
    
    private JPanel createVehiclePanel() {
        vehiclePanel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 122, 183));
        JLabel headerLabel = new JLabel("VEHICLE");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        vehiclePanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        vehicleNumField = new JTextField();
        vehicleManufField = new JTextField();
        vehicleModelField = new JTextField();
        vehicleCustCombo = new JComboBox<>();
        
        formPanel.add(new JLabel("Vehicle Number:"));
        formPanel.add(vehicleNumField);
        formPanel.add(new JLabel("Manufacturer:"));
        formPanel.add(vehicleManufField);
        formPanel.add(new JLabel("Model:"));
        formPanel.add(vehicleModelField);
        formPanel.add(new JLabel("Customer Mobile:"));
        formPanel.add(vehicleCustCombo);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        
        JButton addBtn = createActionButton("Add Vehicle");
        JButton editBtn = createActionButton("Edit");
        JButton deleteBtn = createActionButton("Delete");
        JButton displayBtn = createActionButton("Display All");
        JButton displayCustBtn = createActionButton("Display Customer's Vehicles");
        
        addBtn.addActionListener(e -> saveVehicle("ADD"));
        editBtn.addActionListener(e -> saveVehicle("EDIT"));
        deleteBtn.addActionListener(e -> saveVehicle("DELETE"));
        displayBtn.addActionListener(e -> {
            loadAllVehicles();
            vehicleMode = "DISPLAY";
        });
        displayCustBtn.addActionListener(e -> displayCustomerVehicles());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(displayCustBtn);
        
        vehicleTableModel = new DefaultTableModel(new String[]{"Vehicle Number", "Manufacturer", "Model", "Customer Mobile"}, 0);
        vehicleTable = new JTable(vehicleTableModel);
        vehicleTable.getTableHeader().setBackground(new Color(51, 122, 183));
        vehicleTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(vehicleTable);
        
        vehicleTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = vehicleTable.getSelectedRow();
                if (row >= 0) {
                    vehicleNumField.setText(vehicleTableModel.getValueAt(row, 0).toString());
                    vehicleManufField.setText(vehicleTableModel.getValueAt(row, 1).toString());
                    vehicleModelField.setText(vehicleTableModel.getValueAt(row, 2).toString());
                    vehicleCustCombo.setSelectedItem(vehicleTableModel.getValueAt(row, 3).toString());
                    vehicleMode = "EDIT";
                }
            }
        });
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        vehiclePanel.add(topPanel, BorderLayout.NORTH);
        vehiclePanel.add(tableScroll, BorderLayout.CENTER);
        
        return vehiclePanel;
    }
    
    private void showVehiclePanel(String mode) {
        loadCustomerCombo();
        vehicleMode = mode;
        contentLayout.show(contentPanel, "VEHICLE");
    }
    
    private void loadCustomerCombo() {
        vehicleCustCombo.removeAllItems();
        for (Customer c : customers) {
            vehicleCustCombo.addItem(c.getMobile());
        }
    }
    
    private void loadAllVehicles() {
        vehicleTableModel.setRowCount(0);
        for (Customer c : customers) {
            for (Vehicle v : c.getVehicles().values()) {
                vehicleTableModel.addRow(new Object[]{v.getVehicleNumber(), v.getManufacturer(), v.getModel(), c.getMobile()});
            }
        }
    }
    
    private void displayCustomerVehicles() {
        String mobile = (String) vehicleCustCombo.getSelectedItem();
        if (mobile == null) {
            JOptionPane.showMessageDialog(this, "Please select a customer!");
            return;
        }
        vehicleTableModel.setRowCount(0);
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                for (Vehicle v : c.getVehicles().values()) {
                    vehicleTableModel.addRow(new Object[]{v.getVehicleNumber(), v.getManufacturer(), v.getModel(), c.getMobile()});
                }
            }
        }
    }
    
    private void saveVehicle(String operation) {
        String vehicleNum = vehicleNumField.getText().toUpperCase();
        String manuf = vehicleManufField.getText();
        String model = vehicleModelField.getText();
        String mobile = (String) vehicleCustCombo.getSelectedItem();
        
        if (vehicleNum.isEmpty() || manuf.isEmpty() || model.isEmpty() || mobile == null) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }
        
        if (operation.equals("ADD")) {
            for (Customer c : customers) {
                if (c.getMobile().equals(mobile)) {
                    Vehicle v = new Vehicle(vehicleNum, manuf, model);
                    c.addVehicle(v);
                    JOptionPane.showMessageDialog(this, "Vehicle added!");
                    clearVehicleFields();
                    saveData();
                    return;
                }
            }
        } else if (operation.equals("EDIT")) {
            for (Customer c : customers) {
                if (c.getVehicles().containsKey(vehicleNum)) {
                    Vehicle v = c.getVehicles().get(vehicleNum);
                    v.setManufacturer(manuf);
                    v.setModel(model);
                    JOptionPane.showMessageDialog(this, "Vehicle updated!");
                    saveData();
                    return;
                }
            }
        } else if (operation.equals("DELETE")) {
            for (Customer c : customers) {
                if (c.removeVehicle(vehicleNum)) {
                    JOptionPane.showMessageDialog(this, "Vehicle deleted!");
                    clearVehicleFields();
                    saveData();
                    return;
                }
            }
        }
    }
    
    private void clearVehicleFields() {
        vehicleNumField.setText("");
        vehicleManufField.setText("");
        vehicleModelField.setText("");
    }
    
    
    private JPanel servicePanel;
    private JComboBox<String> serviceCustCombo, serviceVehicleCombo;
    private JTextArea serviceDescArea;
    private JTable serviceItemsTable;
    private DefaultTableModel serviceItemsTableModel;
    
    private JPanel createServicePanel() {
        servicePanel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 122, 183));
        JLabel headerLabel = new JLabel("SERVICE REQUEST");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        servicePanel.add(headerPanel, BorderLayout.NORTH);
        
        
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        serviceCustCombo = new JComboBox<>();
        serviceVehicleCombo = new JComboBox<>();
        
        formPanel.add(new JLabel("Select Customer:"));
        formPanel.add(serviceCustCombo);
        formPanel.add(new JLabel("Select Vehicle:"));
        formPanel.add(serviceVehicleCombo);
        
        serviceCustCombo.addActionListener(e -> loadServiceVehicles());
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        
        JButton newServiceBtn = createActionButton("New Service");
        JButton existingServiceBtn = createActionButton("Existing Service");
        JButton addPartBtn = createActionButton("Add Parts");
        JButton addMaintBtn = createActionButton("Add Maintenance");
        JButton addOilBtn = createActionButton("Add Oil/Additive");
        JButton prepareBillBtn = createActionButton("Prepare Bill");
        JButton getPaymentBtn = createActionButton("Get Payment");
        
        newServiceBtn.addActionListener(e -> createNewService());
        existingServiceBtn.addActionListener(e -> selectExistingService());
        addPartBtn.addActionListener(e -> addPartsToService());
        addMaintBtn.addActionListener(e -> addMaintenance());
        addOilBtn.addActionListener(e -> addOilAdditive());
        prepareBillBtn.addActionListener(e -> prepareBill());
        getPaymentBtn.addActionListener(e -> getPayment());
        
        buttonPanel.add(newServiceBtn);
        buttonPanel.add(existingServiceBtn);
        buttonPanel.add(addPartBtn);
        buttonPanel.add(addMaintBtn);
        buttonPanel.add(addOilBtn);
        buttonPanel.add(prepareBillBtn);
        buttonPanel.add(getPaymentBtn);
        
        
        JPanel descPanel = new JPanel(new BorderLayout());
        descPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        descPanel.add(new JLabel("Service Description:"), BorderLayout.NORTH);
        serviceDescArea = new JTextArea(4, 30);
        descPanel.add(new JScrollPane(serviceDescArea), BorderLayout.CENTER);
        
     
        serviceItemsTableModel = new DefaultTableModel(new String[]{"Item", "Type", "Price", "Qty", "Total"}, 0);
        serviceItemsTable = new JTable(serviceItemsTableModel);
        serviceItemsTable.getTableHeader().setBackground(new Color(51, 122, 183));
        serviceItemsTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(serviceItemsTable);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(descPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        servicePanel.add(topPanel, BorderLayout.NORTH);
        servicePanel.add(tableScroll, BorderLayout.CENTER);
        
        return servicePanel;
    }
    
    private void showServicePanel(String mode) {
        loadServiceCustomerCombo();
        contentLayout.show(contentPanel, "SERVICE");
    }
    
    private void loadServiceCustomerCombo() {
        serviceCustCombo.removeAllItems();
        for (Customer c : customers) {
            serviceCustCombo.addItem(c.getMobile());
        }
    }
    
    private void loadServiceVehicles() {
        serviceVehicleCombo.removeAllItems();
        String mobile = (String) serviceCustCombo.getSelectedItem();
        if (mobile == null) return;
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                for (Vehicle v : c.getVehicles().values()) {
                    serviceVehicleCombo.addItem(v.getVehicleNumber());
                }
            }
        }
    }
    
    private void createNewService() {
        String mobile = (String) serviceCustCombo.getSelectedItem();
        String vehicleNum = (String) serviceVehicleCombo.getSelectedItem();
        
        if (mobile == null || vehicleNum == null) {
            JOptionPane.showMessageDialog(this, "Please select customer and vehicle!");
            return;
        }
        
        currentService = new ServiceRequest(vehicleNum);
        currentService.setDescription(serviceDescArea.getText());
        serviceRequests.add(currentService);
        
        JOptionPane.showMessageDialog(this, "New Service Created! ID: " + currentService.getServiceId());
        saveData();
    }
    
    private void selectExistingService() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Service ID:");
        if (idStr == null) return;
        
        try {
            int id = Integer.parseInt(idStr);
            for (ServiceRequest sr : serviceRequests) {
                if (sr.getServiceId() == id) {
                    currentService = sr;
                    serviceDescArea.setText(sr.getDescription());
                   
                    for (Customer c : customers) {
                        for (Vehicle v : c.getVehicles().values()) {
                            if (v.getVehicleNumber().equals(sr.getVehicleNumber())) {
                                serviceCustCombo.setSelectedItem(c.getMobile());
                                break;
                            }
                        }
                    }
                    loadServiceItems();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Service not found!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Service ID!");
        }
    }
    
    private void loadServiceItems() {
        serviceItemsTableModel.setRowCount(0);
        if (currentService == null) return;
        for (ServiceItem item : currentService.getItems()) {
            serviceItemsTableModel.addRow(new Object[]{
                item.getName(), item.getType(), item.getPrice(), 
                item.getQuantity(), item.getPrice() * item.getQuantity()
            });
        }
    }
    
    private void addPartsToService() {
        if (currentService == null) {
            JOptionPane.showMessageDialog(this, "Create or select a service first!");
            return;
        }
        
       
        JDialog dialog = new JDialog(this, "Select Parts", true);
        dialog.setSize(400, 300);
        
        JTable partsTable = new JTable(new DefaultTableModel(
            new String[]{"Select", "ID", "Name", "Price"}, 0) {
            @Override
            public Class<?> getColumnClass(int col) {
                return col == 0 ? Boolean.class : Object.class;
            }
        });
        
        for (Part p : parts) {
            ((DefaultTableModel)partsTable.getModel()).addRow(new Object[]{false, p.getPartId(), p.getPartName(), p.getPrice()});
        }
        
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton cancelBtn = new JButton("Cancel");
        
        addBtn.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) partsTable.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if ((Boolean) model.getValueAt(i, 0)) {
                    String qtyStr = JOptionPane.showInputDialog(this, "Quantity:");
                    if (qtyStr != null && !qtyStr.isEmpty()) {
                        int qty = Integer.parseInt(qtyStr);
                        currentService.addItem(new ServiceItem(
                            model.getValueAt(i, 2).toString(), "Part",
                            (Double) model.getValueAt(i, 3), qty));
                    }
                }
            }
            saveData();
            loadServiceItems();
            dialog.dispose();
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        btnPanel.add(addBtn);
        btnPanel.add(cancelBtn);
        
        dialog.add(new JScrollPane(partsTable), BorderLayout.CENTER);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void addMaintenance() {
        if (currentService == null) {
            JOptionPane.showMessageDialog(this, "Create or select a service first!");
            return;
        }
        
        String desc = JOptionPane.showInputDialog(this, "Maintenance Description:");
        String priceStr = JOptionPane.showInputDialog(this, "Labor Charge:");
        
        if (desc != null && priceStr != null) {
            try {
                double price = Double.parseDouble(priceStr);
                currentService.addItem(new ServiceItem(desc, "Maintenance", price, 1));
                saveData();
                loadServiceItems();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price!");
            }
        }
    }
    
    private void addOilAdditive() {
        if (currentService == null) {
            JOptionPane.showMessageDialog(this, "Create or select a service first!");
            return;
        }
        
        String name = JOptionPane.showInputDialog(this, "Oil/Additive Name:");
        String priceStr = JOptionPane.showInputDialog(this, "Price:");
        
        if (name != null && priceStr != null) {
            try {
                double price = Double.parseDouble(priceStr);
                currentService.addItem(new ServiceItem(name, "Oil/Additive", price, 1));
                saveData();
                loadServiceItems();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price!");
            }
        }
    }
    
    private void prepareBill() {
        if (currentService == null) {
            JOptionPane.showMessageDialog(this, "Select a service first!");
            return;
        }
        
        
        double partsTotal = 0, laborTotal = 0;
        for (ServiceItem item : currentService.getItems()) {
            if (item.getType().equals("Part") || item.getType().equals("Oil/Additive")) {
                partsTotal += item.getPrice() * item.getQuantity();
            } else {
                laborTotal += item.getPrice() * item.getQuantity();
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
        
        StringBuilder bill = new StringBuilder();
        bill.append("====================================\n");
        bill.append("     VEHICLE SERVICE STATION\n");
        bill.append("====================================\n\n");
        bill.append("Service ID: ").append(currentService.getServiceId()).append("\n");
        bill.append("Date: ").append(currentService.getDate()).append("\n\n");
        bill.append("Customer: ").append(cust.getName()).append("\n");
        bill.append("Mobile: ").append(cust.getMobile()).append("\n");
        bill.append("Vehicle: ").append(veh.getManufacturer()).append(" ").append(veh.getModel()).append("\n");
        bill.append("Number: ").append(veh.getVehicleNumber()).append("\n\n");
        bill.append("------------------------------------\n");
        bill.append("DETAILS:\n");
        
        for (ServiceItem item : currentService.getItems()) {
            bill.append(item.getName()).append(" (").append(item.getType()).append(")\n");
            bill.append("  ").append(item.getQuantity()).append(" x Rs.").append(item.getPrice());
            bill.append(" = Rs.").append(String.format("%.2f", item.getPrice() * item.getQuantity())).append("\n");
        }
        
        bill.append("\n------------------------------------\n");
        bill.append("Labor Charges: Rs.").append(String.format("%.2f", laborTotal)).append("\n");
        bill.append("Parts Total: Rs.").append(String.format("%.2f", partsTotal)).append("\n");
        bill.append("Subtotal: Rs.").append(String.format("%.2f", subtotal)).append("\n");
        bill.append("Tax (12.6%): Rs.").append(String.format("%.2f", tax)).append("\n");
        bill.append("====================================\n");
        bill.append("TOTAL: Rs.").append(String.format("%.2f", total)).append("\n");
        bill.append("====================================\n");
        
        JTextArea area = new JTextArea(bill.toString());
        area.setFont(new Font("Courier New", Font.PLAIN, 11));
        
        JDialog billDialog = new JDialog(this, "Bill", true);
        billDialog.setSize(400, 500);
        billDialog.add(new JScrollPane(area), BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> billDialog.dispose());
        btnPanel.add(closeBtn);
        billDialog.add(btnPanel, BorderLayout.SOUTH);
        
        billDialog.setLocationRelativeTo(this);
        billDialog.setVisible(true);
    }
    
    private void getPayment() {
        if (currentService == null) {
            JOptionPane.showMessageDialog(this, "Select a service first!");
            return;
        }
        
        String amountStr = JOptionPane.showInputDialog(this, "Enter Payment Amount:");
        if (amountStr != null) {
            try {
                double amount = Double.parseDouble(amountStr);
                
                
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
                
                
                double total = 0;
                for (ServiceItem item : currentService.getItems()) {
                    total += item.getPrice() * item.getQuantity();
                }
                total = total * 1.126;
                
                Bill bill = new Bill(currentService.getServiceId(), cust.getName(), 
                    cust.getMobile(), veh.getVehicleNumber(), total, amount, currentService.getDate());
                bills.add(bill);
                
                JOptionPane.showMessageDialog(this, "Payment recorded!");
                saveData();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        }
    }
    
    
    private JPanel partsPanel;
    private JTextField partIdField, partNameField, partPriceField;
    private JTable partsTable;
    private DefaultTableModel partsTableModel;
    
    private JPanel createPartsPanel() {
        partsPanel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 122, 183));
        JLabel headerLabel = new JLabel("PARTS");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        partsPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        partIdField = new JTextField();
        partIdField.setEditable(false);
        partNameField = new JTextField();
        partPriceField = new JTextField();
        
        formPanel.add(new JLabel("Part ID:"));
        formPanel.add(partIdField);
        formPanel.add(new JLabel("Part Name:"));
        formPanel.add(partNameField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(partPriceField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        
        JButton addBtn = createActionButton("Add Part");
        JButton editBtn = createActionButton("Edit Price");
        JButton deleteBtn = createActionButton("Delete");
        JButton displayBtn = createActionButton("Display All");
        
        addBtn.addActionListener(e -> savePart("ADD"));
        editBtn.addActionListener(e -> savePart("EDIT"));
        deleteBtn.addActionListener(e -> savePart("DELETE"));
        displayBtn.addActionListener(e -> loadAllParts());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(displayBtn);
        
        partsTableModel = new DefaultTableModel(new String[]{"Part ID", "Part Name", "Price"}, 0);
        partsTable = new JTable(partsTableModel);
        partsTable.getTableHeader().setBackground(new Color(51, 122, 183));
        partsTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane tableScroll = new JScrollPane(partsTable);
        
        partsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = partsTable.getSelectedRow();
                if (row >= 0) {
                    partIdField.setText(partsTableModel.getValueAt(row, 0).toString());
                    partNameField.setText(partsTableModel.getValueAt(row, 1).toString());
                    partPriceField.setText(partsTableModel.getValueAt(row, 2).toString());
                }
            }
        });
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        partsPanel.add(topPanel, BorderLayout.NORTH);
        partsPanel.add(tableScroll, BorderLayout.CENTER);
        
        return partsPanel;
    }
    
    private void showPartsPanel(String mode) {
        contentLayout.show(contentPanel, "PARTS");
    }
    
    private void loadAllParts() {
        partsTableModel.setRowCount(0);
        for (Part p : parts) {
            partsTableModel.addRow(new Object[]{p.getPartId(), p.getPartName(), p.getPrice()});
        }
    }
    
    private void savePart(String operation) {
        String name = partNameField.getText();
        String priceStr = partPriceField.getText();
        
        if (name.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter name and price!");
            return;
        }
        
        try {
            double price = Double.parseDouble(priceStr);
            
            if (operation.equals("ADD")) {
                int newId = parts.size() + 1;
                parts.add(new Part(newId, name, price));
                partIdField.setText(String.valueOf(newId));
                JOptionPane.showMessageDialog(this, "Part added!");
                saveData();
                loadAllParts();
                clearPartFields();
            } else if (operation.equals("EDIT")) {
                String idStr = partIdField.getText();
                if (idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a part!");
                    return;
                }
                int id = Integer.parseInt(idStr);
                for (Part p : parts) {
                    if (p.getPartId() == id) {
                        p.setPrice(price);
                        JOptionPane.showMessageDialog(this, "Price updated!");
                        saveData();
                        loadAllParts();
                        return;
                    }
                }
            } else if (operation.equals("DELETE")) {
                String idStr = partIdField.getText();
                if (idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a part!");
                    return;
                }
                int id = Integer.parseInt(idStr);
                Iterator<Part> it = parts.iterator();
                while (it.hasNext()) {
                    if (it.next().getPartId() == id) {
                        it.remove();
                        JOptionPane.showMessageDialog(this, "Part deleted!");
                        saveData();
                        loadAllParts();
                        clearPartFields();
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price!");
        }
    }
    
    private void clearPartFields() {
        partIdField.setText("");
        partNameField.setText("");
        partPriceField.setText("");
    }
    
    
    private JPanel businessPanel;
    private JTextField businessDateField;
    
    private JPanel createBusinessPanel() {
        businessPanel = new JPanel(new BorderLayout());
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 122, 183));
        JLabel headerLabel = new JLabel("BUSINESS");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        businessPanel.add(headerPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JButton todayBtn = createActionButton("Today's Business");
        JButton dateBtn = createActionButton("Given Date's Business");
        
        todayBtn.setPreferredSize(new Dimension(200, 50));
        dateBtn.setPreferredSize(new Dimension(200, 50));
        
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Date (YYYY-MM-DD):"));
        businessDateField = new JTextField(15);
        datePanel.add(businessDateField);
        
        todayBtn.addActionListener(e -> showTodayBusiness());
        dateBtn.addActionListener(e -> showDateBusiness());
        
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(todayBtn);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(dateBtn);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(datePanel);
        
        businessPanel.add(centerPanel, BorderLayout.CENTER);
        
        return businessPanel;
    }
    
    private void showBusinessPanel(String mode) {
        contentLayout.show(contentPanel, "BUSINESS");
    }
    
    private void showTodayBusiness() {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
        double total = 0;
        for (Bill b : bills) {
            if (b.getDate().equals(today)) {
                total += b.getPaidAmount();
            }
        }
        JOptionPane.showMessageDialog(this, "Today's Business: Rs. " + String.format("%.2f", total));
    }
    
    private void showDateBusiness() {
        String date = businessDateField.getText();
        if (date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a date!");
            return;
        }
        double total = 0;
        for (Bill b : bills) {
            if (b.getDate().equals(date)) {
                total += b.getPaidAmount();
            }
        }
        JOptionPane.showMessageDialog(this, "Business on " + date + ": Rs. " + String.format("%.2f", total));
    }
    
    private JButton createActionButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        return btn;
    }
    
   
    private void saveData() {
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
        } catch (Exception e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadData() {
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
        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Window1().setVisible(true);
        });
    }
}



class Customer implements Serializable {
    private String mobile, name, address;
    private HashMap<String, Vehicle> vehicles = new HashMap<>();
    
    public Customer(String mobile, String name, String address) {
        this.mobile = mobile;
        this.name = name;
        this.address = address;
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
        return o instanceof Customer && mobile.equals(((Customer) o).mobile);
    }
    
    @Override
    public int hashCode() { return mobile.hashCode(); }
}

class Vehicle implements Serializable {
    private String vehicleNumber, manufacturer, model;
    
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
}

class Part implements Serializable {
    private int partId, partName;
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
}

class ServiceRequest implements Serializable {
    private static int idCounter = 1;
    private int serviceId;
    private String vehicleNumber, date, description;
    private LinkedList<ServiceItem> items = new LinkedList<>();
    
    public ServiceRequest(String vehicleNumber) {
        this.serviceId = idCounter++;
        this.vehicleNumber = vehicleNumber;
        this.date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    
    public int getServiceId() { return serviceId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LinkedList<ServiceItem> getItems() { return items; }
    public void addItem(ServiceItem item) { items.add(item); }
}

class ServiceItem implements Serializable {
	private String name, type;
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
}

class Bill implements Serializable {
	private int serviceId;
	private String customerName, customerMobile, vehicleNumber, date;
	private double totalAmount, paidAmount;
	
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
	
	public String getDate() { return date; }
	public double getPaidAmount() { return paidAmount; }
}