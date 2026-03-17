package com.bankaccount;
import java.util.Scanner;

class BankAccount{
	int Account_Num;
	String Account_Holder;
	double Account_Balance;
	
	public void setDetails(Scanner sc) {
		
		int Account_Num[] = new int[10];
		String Account_Holder[] = new String[10];
		double[] Account_Balance = new double[10];
		
		
		int arr=5;
		System.out.println("Input Details");
		for(int i=0; i<arr; i++) {
			System.out.println("Set the Account Number");
			this.Account_Num = sc.nextInt();
			System.out.println("Set the Name");
			this.Account_Holder = sc.next();
			System.out.println("Input the Balance");
			this.Account_Balance = sc.nextDouble();
				
		}
		System.out.print("Thank");
		
	}
		
	public void getDetails() {
		System.out.println(Account_Num);
		System.out.println(Account_Holder);
		System.out.println(Account_Balance);
	}
				
}


public class Bank {
	public static void main(String[]args) {
		System.out.println("Welcome to Bank !");
		System.out.println("Choose the options below to start\n");
		
		System.out.println("1.Deposit");
		System.out.println("2.Withdraw");
		System.out.println("3.Check the Balance");
		System.out.println("0.Exit");
		
		Scanner sc = new Scanner(System.in);
		
		int choice = sc.nextInt();
		
		double Deposit_Amount = 0;
		double withdrawn_amt = 0;
		double total_bal = 0;
		
		switch(choice) {
		
		case 1:  
			System.out.println("Enter the amount :");
			Deposit_Amount = sc.nextDouble();
			
			if(Deposit_Amount > 0) {
				total_bal += Deposit_Amount;
				System.out.println("You have deposited the " +Deposit_Amount);
			}
			else {
				System.out.println("The deposit amount must be greater than 0");
			}
			break;
		case 2:
			if (withdrawn_amt > 0 && withdrawn_amt <= total_bal)
			{
                total_bal -= withdrawn_amt;
                System.out.println("You have wihdrawn the " +withdrawn_amt);
			}
			else if(withdrawn_amt > total_bal)
			{
				System.out.print("Insufficient Funds");
			}
			else {
				//In case of negative number
				System.out.println("Negative values are invalid");
			}
			 break;
		case 3:
			System.out.println("Your Account Balance is :" +total_bal);
		}
		
		
		try {
		BankAccount ba = new BankAccount(); 
//		ba.setDetails(sc);
//		ba.getDetails();
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
		}
	
	}
}
