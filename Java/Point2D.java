package com.app.geometry;
import java.math.*;


public class Point2D {
	private double x;
	private double y;
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public String getDetails() {
		return(" " + x + " " + y);
	}
	
	public Double isEqual(double x, double y) {
		double compareResult = Double.compare(x, y);
		System.out.println();
		return compareResult;
	}
	
	public void mathfunction(double num1, double num2) {
//	 	double result;
		System.out.println(Math.sqrt(num1));
		System.out.println(Math.pow(num1, num2));
		
	}
	
	public double calculateDistance(double curpoint, double specpoint) {
		double calDis = Math.abs(curpoint - specpoint);
		System.out.println("The distance between x and y: " + calDis);
		return calDis;	
	}
	
	
	public static void main(String []args) {
		Point2D p1 = new Point2D(10, 20);
		
		System.out.println("Point p1 details"+p1.getDetails());
		
		Point2D p2 = new Point2D(20.5, 35.5);
		System.out.println("Point p2 details" + p2.getDetails());
		
		System.out.println(p1.isEqual(40, 40));
		
		System.out.println(p1.calculateDistance(40, 40));
			
	}
	 
	
}
