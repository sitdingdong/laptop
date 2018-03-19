package practiseabstract;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class practise {

	public static void main(String[] args) {
		char add = "+";
		Map<String,Animals> ani = new HashMap<>();
		try {
			File f = new File("Animals.txt");
			Scanner sc = new Scanner(f);
			String s;
			while(sc.hasNextLine()) {
				s = sc.nextLine();
				String[] animals = s.split(" ");
				String type = animals[0];
				String name = animals[1];
				double weight = Double.parseDouble(animals[2]);
				int age = Integer.parseInt(animals[3]);
				if(type.equals("Cat"))
					ani.put(name, new cat(name, weight, age));
				if(type.equals("Dog"))
					ani.put(name, new dog(name, weight, age));
				} 
			if(ani.containsKey("Alice")) {
				System.out.println(ani.get("Alice").print());
			}else{
				System.out.println("surprise motherfucker");
			}
			sc.close();
			//System.out.println("asda");
		}catch(Exception e) {
			System.out.println("Error");
		}
	

		// will read information from a file
		// format:
		// Cat Alice 15.9 5
		// Dog Steve 8.8 7
		// Cat CarMen 7.2 6
		// use some data structure to make sure we can find an animal by the name quickly

	}

}

abstract class Animals {
	private String name;
	private double weight;
	private int age;
	public  Animals(String name, double weight, int age){
		this.name = name;
		this.weight = weight;
		this.age = age;

	}
	public abstract String print();
	public String getname(){
		return name;
	}
	public int getage(){
		return age;
	}
}

class dog extends Animals{
	public dog(String name, double weight, int age) {
		super(name, weight, age);		
	}

	public String print() {
		return "I am a dog, my name is "+super.getname()+". my age is "+super.getage();
	}
}

class cat extends Animals {
	public cat(String name, double weight, int age) {
		super(name, weight, age);		
	}

	public String print() {
		return "I am a cat, my name is "+super.getname()+". my age is "+super.getage();
	}
	}
		// return I am a cat, my name is... my age is.
	
