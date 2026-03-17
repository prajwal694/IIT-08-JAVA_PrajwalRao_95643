package com.AssociationandInheritance;
import java.util.Scanner;

public class Person {
	
	String p_name;
	int p_num;
	
	void greet() {
		System.out.println("Hello from person class");
	}
	
//	Scanner sc = new Scanner(System.in);
//	int first_num = sc.nextInt();
//	int second_num = sc.nextInt();
	
	
	
	void setDetails(String name, int num){
		this.p_name = name;
		this.p_num = num;
	}
	
	void getDetails() {
		System.out.println(p_name);
		System.out.println(p_num);
	}
}
