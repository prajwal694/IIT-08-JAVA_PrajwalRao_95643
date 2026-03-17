package com.AssociationandInheritance;

class Student_info extends Person{
	@Override
	void greet() {
		super.greet();
		System.out.println("Hello from student class");
	}
	
	
}

public class Student{
	public static void main(String[]args) {
		Person P = new Person();
		
		
		
	}
}
