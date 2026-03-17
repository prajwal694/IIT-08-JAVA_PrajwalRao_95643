package com.sunbeam;
import java.util.Scanner;

public class FirstProgram {
   public static void main (String []args) {
//	   System.out.print("Hello World");
	   
	   Scanner sc = new Scanner(System.in);
	   System.out.println();
	   System.out.println("Enter name: ");
	   String name = sc.nextLine();
	   System.out.println("Enter age");
	   int age = sc.nextInt();
	   
	   System.out.println("Name: " + name + " Age: " +age);
	   
   }
}
