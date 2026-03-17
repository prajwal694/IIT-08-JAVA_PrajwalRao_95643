package com.sunbeam;
import java.util.Scanner;

public class Take_User_Input {
	public static void main(String []args) {
		System.out.println("Hello ");
		
		System.out.print("Enter the first value :");
		Scanner sc = new Scanner (System.in);
		double a = sc.nextDouble();
		
		System.out.print("Enter the second value :");
		double b = sc.nextDouble();
		
		double average = a+b/2;
		System.out.println("The average of both inputs are :" +average);
	}
}
