package com.interface_implementation;

interface name{
	int num=10;
	public void PrintNum();
}

interface address{
	String address = "Pune";
}

interface I1{
//marker interface 	
}

abstract class StudentName implements name{
	
	public void PrintNum(){
		System.out.println("Number: "+num);
	}
}

abstract class StudentAdd implements address{
	public void PrintAdd() {
		System.out.println("Address: " +address);
	}
}

abstract class StudentInfo implements name, address{
	
}

class studentNameInfo extends StudentName{
	
}

class studentAddInfo extends StudentAdd{

}

public class program {
	public static void main(String[]args) {
		studentNameInfo si = new studentNameInfo();
		si.PrintNum();
		
	}
}
