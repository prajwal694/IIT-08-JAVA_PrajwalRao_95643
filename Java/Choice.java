package com.sunbeam;

import java.util.Scanner;

public class Choice {
	
		public static int menu() {
			System.out.println("0.EXIT");
			System.out.println("1.Accept");
			System.out.println("2.Display");
			System.out.println("3.Find");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			return choice;
		}
	
		public static void main(String []args) {
			int choice;
			
			while((choice = menu())!= 0) {
				switch (choice) {
				case 1:
					System.out.println("Accept the data");
				break;
				
				case 2:
					System.out.println("Display the data");
					break;
					
				case 3 :
					System.out.println("Find the data");
					break;
					
				default:
					System.out.println("Please select the valid options");
					break;
				}
				
			}
		}
}
