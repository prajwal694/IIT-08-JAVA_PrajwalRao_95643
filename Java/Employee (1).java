package com.inheritance_implementation;
import java.util.Scanner;

public class Employee {
	int id;
	String name;
	String designation;
	
	
	public Employee() {
       this.id=id;
       this.name=name;
       this.designation = designation;
	}
	public Employee(int id,String name, String designation) {
		this.id = id;
		this.name = name;
		this.designation = designation;
	}
	
	
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	
	public void accept() {
		
	}
	
	public void setEmpData(int id, String name, String designation) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id: ");
		id = sc.nextInt();
		System.out.println("Enter the Name: ");
		name = sc.next();
		System.out.println("Enter the Designation");
		designation = sc.next();
		
		this.id = id;
		this.name = name;
		this.designation = designation;
	}
	
	public void display() {
		System.out.println("Enter the values for employee fields");
		System.out.println("Employee id: "+id);
		System.out.println("Employee name: "+name);
		System.out.print("Employee designation: "+designation);
	}
}
