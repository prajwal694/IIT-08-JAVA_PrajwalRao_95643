package com.tester;
import com.app.geometry.Point2D;
import java.util.Scanner;

public class TestPoint {
	public static void main(String []args) {
		double a,b;
		
		System.out.println("Calculate Point");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your first number: ");
		a = sc.nextDouble();
		System.out.println("Enter your second number: ");
		b = sc.nextDouble();
		
		Point2D p = new Point2D(a,b);
	
		System.out.println("The number is "+p.isEqual(a,b));
		p.getDetails();
		
		p.mathfunction(a, b);
		p.calculateDistance(a, b);
	}

}
