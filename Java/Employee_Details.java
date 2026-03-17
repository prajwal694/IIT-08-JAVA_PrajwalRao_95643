package com.sunbeam;

class Worker {
    String name;
    String last_name;
    double salary;

    // Default constructor
    public Worker() {
    }

    // Getters and Setters (as provided)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

public class Employee_Details {
    public static void main(String[] args) {
        System.out.println("--- Program Start ---");

        // --- Details for EMP BABU ---
        Worker babu = new Worker(); // Create a new object for Babu
        babu.setName("Babu Bhaiya");
        babu.setSalary(2000);

        System.out.println("Employee Name: " + babu.getName());
        System.out.println("Initial Salary: " + babu.getSalary());

        // Calculate and apply hike for Babu
        double babuHikeAmount = (babu.getSalary() * 10) / 100;
        double babuNewSalary = babu.getSalary() + babuHikeAmount;
        System.out.println("10% Hike Amount: " + babuHikeAmount);
        System.out.println("Salary After Hike: " + babuNewSalary);
        System.out.println(); // New line for formatting

        // --- Details for EMP RAJU ---
        Worker raju = new Worker(); // Create a separate new object for Raju
        raju.setName("Raju");
        raju.setSalary(1500);

        System.out.println("Employee Name: " + raju.getName());
        System.out.println("Initial Salary: " + raju.getSalary());

        // Calculate and apply hike for Raju
        double rajuHikeAmount = (raju.getSalary() * 10) / 100;
        double rajuNewSalary = raju.getSalary() + rajuHikeAmount;
        System.out.println("10% Hike Amount: " + rajuHikeAmount);
        System.out.println("Salary After Hike: " + rajuNewSalary);
        System.out.println(); // New line for formatting
        
        System.out.println("--- Program End ---");
    }
}
