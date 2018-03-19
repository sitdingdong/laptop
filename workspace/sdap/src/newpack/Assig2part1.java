/***********************
name DONG DING
CWID 10422301
HOW TO EXECUTE THE PROGRAM :
1 Create a new Java project.
2 Create a Java file in the project with the name same to the class name in the code
3 Change the password(pwd) and username(usr) to match the database in the executing computer
4 Run it 
Data member : 	List<List<String>> data 
List data structure helps to save the data and just need to readin once
The method signin() just call only once!
}
 */
package newpack;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry; 
public class Assig2part1 { 
	// store the date in the list
	private TreeMap<String, TreeMap<String, int[]>> data;
	private HashMap<String, int[]> prod_amount;
	private HashMap<String, int[]> cust_amount;
	private int total;
	
	public Assig2part1() {
	// constructor initialize the storage structure 
		data = new TreeMap<String, TreeMap<String, int[]>>();
		cust_amount = new HashMap<>();
		prod_amount = new HashMap<>();
		total = 0;
	}
	//the format of printout
	public void printout(){
		System.out.println("CUSTOMER" + "  " + "PRODUCT" + "  " + "THE_AVG" + "  " + "OTHER_PROD_AVG" + "  " + "OTHER_CUST_AVG");
		System.out.println("========" + "  " + "=======" + "  " + "=======" + "  " + "==============" + "  "
				+ "==============");
		for(Entry<String, TreeMap<String, int[]>> e : data.entrySet()) {
			String cust = e.getKey();
			for(Entry<String, int[]> e1 : e.getValue().entrySet()) {
				String prod = e1.getKey();
				int[] val = e1.getValue();
				int[] custval = cust_amount.get(cust);
				int[] prodval = prod_amount.get(prod);
				System.out.print(cust);
				fillSpace(10, cust.length());
				System.out.print(prod);
				fillSpace(9, prod.length());
				fillSpace(7, numberSpace(val[0] / val[1]));
				System.out.print(val[0] / val[1] );
				//fillSpace(9, numberSpace(val[0] / val[1]));
				fillSpace(16, numberSpace((custval[0] - val[0]) / (custval[1] - val[1])));
				System.out.print((custval[0] - val[0]) / (custval[1] - val[1]) );
				fillSpace(16, numberSpace((prodval[0] - val[0]) / (custval[1] - val[1])));
				System.out.print((prodval[0] - val[0]) / (custval[1] - val[1]));
				System.out.println();
			}
		} 
	}
	// this method is for fill the space to make the text on left or right
	private void fillSpace(int n, int r) {
		for(int i = 0; i < n - r; i++)
			System.out.print(" ");
	}
	// this method is to help fillSpace print out the right space when number come in
    public static int numberSpace(int n) {
        if (n < 10 && n > 0) {
            return 1;
        }
        if (n < 100 && n > 0) {
            return 2;
        }
        if (n < 1000 && n > 0) {
            return 3;
        } else {
            return 4;
        }
    }
	//this method is for load the data in the database
	// try to deploy only once!
	public void signin() {
		// the basic info of the database
		String usr = "postgres";
		String pwd = "1qaz2wsx";
		String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			while (rs.next()) {
				//store the data into class
				String cust = rs.getString("cust");
				String prod = rs.getString("prod");
				int quant = Integer.parseInt(rs.getString("quant"));
				total++;

				if(!data.containsKey(cust)) {
					data.put(cust, new TreeMap<>());
				}
				TreeMap<String, int[]> temp = data.get(cust);
				if(!temp.containsKey(prod)) {
					temp.put(prod, new int[2]);
				}
				int[] temp1 = temp.get(prod);
				temp1[0] += quant;
				temp1[1] ++;
				temp.put(prod, temp1);
				data.put(cust, temp);
				if(!prod_amount.containsKey(prod)) {
					prod_amount.put(prod, new int[2]);
				}
				if(!cust_amount.containsKey(cust)) {
					cust_amount.put(cust, new int[2]);
				}
				int[] prod_array = prod_amount.get(prod);
				int[] cust_array = cust_amount.get(cust);
				prod_array[0] += quant;
				prod_array[1] ++;
				cust_array[0] += quant;
				cust_array[1] ++;
				cust_amount.put(cust, cust_array);
				prod_amount.put(prod, prod_array);
			}
		} catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) { 
		Assig2part1 sales = new Assig2part1();
		sales.signin();
		sales.printout();
		
	} 
}