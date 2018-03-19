package practise;

import java.io.Serializable;
import java.util.*;

public class Bank {
	public void main (String[] args) {
		
	}

}
interface Cust extends Serializable, Cloneable {
	//private String name;
	//private double amount;
	Cust clone();
	//boolean equals();
	public void print();
}
class Depositor implements Cust{
	private String name;
	private double amount;
	private String account;
	public Depositor(String name, double amount, String account) {
		this.name = name;
		this.amount = amount;
		this.account = account;
	}
	public String getName() {
		return name;
	}
	
	public Cust clone() {
		return new Depositor(name, amount, account);
	}
	public boolean equals(Depositor a) {
		if(name.equals(a.name) && amount == a.amount && account.equals(a.account))
			return true;
		else return false;
	}
	
	public void print() {
		System.out.println("");;
		
	}
	
}
class Borrower implements Cust{
	public class Date {
		int year;
		int month;
		int day;
		public Date(int year, int month, int day){
			this.year = year;
			this.month = month;
			this.day = day;
		}
		public boolean equals(Date a) {
			if (year == a.year && month == a.month && day == a.day)
				return true;
			else return false;
		}
		public String toString() {
			return day + "/" + month + "/" + year;
		}
	}
	private int y;
	private int m;
	private int d;
	private Date date;
	private String name;
	private double amount;
	public Borrower(String name, double amount, Date date) {
		this.name = name;
		this.amount = amount;
		this.date = new Date(y,m,d);
	}
	public Cust clone() {
		return new Borrower(name, amount, date);
	}
	public boolean equals(Borrower a) {
		if(name.equals(a.name) && amount == a.amount && date.equals(a.date))
			return true;
		else return false;
	}
	
	public void print() {
		System.out.println("");
		
	}
}
