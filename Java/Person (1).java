package com.inheritance_implementation;

public class Person {
	public static void main(String []args) {
		System.out.println("Implementation of Inheritance");
		
		Employee e = new Employee();
//		e.getEmpData();
		Employee e1=new Employee();
		
		e.accept();
		
		e.setEmpData(1, "Dev","Jack");
		e.display();
		
		System.out.println();
		StudentDetails s = new StudentDetails();
//		s.Displayrecords();
	
		
		StudentDetails[] arr=new StudentDetails[3];

		for(int i=0;i<arr.length;i++) {
			arr[i]=new StudentDetails();
			arr[i].AcceptStudentInfo();
			System.out.println();
			
		}
		
		System.out.println("Details of all students is as follows : ");
		
		for(StudentDetails st : arr) {
			
			st.Displayrecords();
		}
	}	
}
