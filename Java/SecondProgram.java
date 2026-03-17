package com.sunbeam;
import java.util.Scanner;

class Time{
	int hr;
	int min;
	
	void acceptTime() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the hrs -");
		hr = sc.nextInt();
		System.out.print("Enter the mins -");
		min = sc.nextInt();
	}
	
	void displayTime() {
		System.out.println("Time - "+hr +" : " +min);
		
	}
	
}

public class SecondProgram {

	public static void main(String []args) {
		Time t = new Time();
		t.acceptTime();
		t.displayTime();
	}

}
