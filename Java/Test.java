package com.sunbeam;
import java.util.Scanner;
import java.lang.*;

public class Test{

	public static void main(String[] args) {
		System.out.println("Java Test");
		System.out.print("Enter the number :");
		Scanner sc = new Scanner (System.in);
		int num1 = sc.nextInt();
		System.out.println("Your number is :" + num1);
		System.out.println("The binary string of "+num1+ " is: " + Integer.toBinaryString(num1));
		System.out.println("THe octal string of " +num1+ " is: " + Integer.toOctalString(num1));
		System.out.println("THe hexadecimal string of " +num1+ " is: " +Integer.toHexString(num1));

	}

}
