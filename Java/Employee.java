package com.AssociationandInheritance;

public class Employee {
	
	public static void main(String[]args) {
		
		Person person = new Person();
		person.setDetails("Jack", 101);
		person.setDetails("James", 102);
		person.getDetails();
	}
}
