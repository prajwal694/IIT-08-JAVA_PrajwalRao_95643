package com.inheritance_implementation;
import java.util.Scanner;

class StudentDetails{
	String name;
	int rollno;
	
	public StudentDetails() {}
	
	public StudentDetails(String name,int rollno) {
		this.name=name;
		this.rollno=rollno;
	}

	public void AcceptStudentInfo() {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the names of students :- ");
		this.name= sc.next();
		
		System.out.print("Enter the rollno of students :- "); 	
		this.rollno=sc.nextInt();
		
	}
		
	public void Displayrecords() {	
		
		System.out.println("The name of students are :" +this.name);
		System.out.println("The rollno of students are :" +this.rollno);
		System.out.println();
		
		
	}	
	}
	
public class Student {
	public static void main(String[]args) {
		System.out.print("Inside student class");
	
		
		
		
		System.out.println();
		System.out.println("**********Details**********");
		
		
	}
}
