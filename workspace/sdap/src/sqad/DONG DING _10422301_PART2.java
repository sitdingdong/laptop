/*
Name DONG DING
CWID 10422301
HOW TO EXECUTE THE PROGRAM :
1 Create a new Java project.
2 Create a Java file in the project with the name same to the class name in the code
3 Change the password(pwd) and username(usr) to match the database in the executing computer
4 Run it 
Data member : 	List<List<String>> data 
List data structure helps to save the data and just need to readin once
The method signin() just call only once!
*/
package assi2;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry; 
public class sqad22 { 
	// store the date in the list
	private TreeMap<String, TreeMap<String, int[]>> data;

	
	public sqad22() {
	// constructor initialize the storage structure 
		data = new TreeMap<String, TreeMap<String, int[]>>();
	}
	//read the data in the database
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales where quiz"); 
	  
			while (rs.next()) { 
				//store the data into class
				String cust = rs.getString("cust");
				String prod = rs.getString("prod");
				int month  = Integer.parseInt(rs.getString("month"));
				int quant = Integer.parseInt(rs.getString("quant"));
				
				if(!data.containsKey(cust)) {
					data.put(cust, new TreeMap<>()); 
				}
				TreeMap<String, int[]> temp = data.get(cust);
				if(!temp.containsKey(prod)) {
					temp.put(prod, new int[8]);
				}
				int[] temp1 = temp.get(prod);
				if(month <= 3) {
					temp1[0] += quant;
					temp1[1] ++;
				} else if(month <= 6) {
					temp1[2] += quant;
					temp1[3] ++;
				} else if(month <= 9) {
					temp1[4] += quant;
					temp1[5] ++;
				} else {
					temp1[6] += quant;
					temp1[7] ++;
				}
				temp.put(prod, temp1);
				data.put(cust, temp);
			}
		} catch (SQLException e) {    
				System.out.println("Connection URL or username or password errors!");    
				e.printStackTrace();   
			}
	}
	public void printout(){
		System.out.println("CUSTOMER" + "  " + "PRODUCT" + "  " + "QUARTER" + "  " + "BEFORE_AVG" + "  " + "AFTER_AVG");
		System.out.println("========" + "  " + "=======" + "  " + "=======" + "  " + "==========" + "  " + "=========");
		for(Entry<String, TreeMap<String, int[]>> e : data.entrySet()) {
			String cust = e.getKey();
			for(Entry<String, int[]> e1 : e.getValue().entrySet()) {
				String prod = e1.getKey();
				int[] val = e1.getValue();
				for(int i = 0; i < 4; i++) {
					
					if(i == 0 && val[3] == 0) continue;
					if(i == 3 && val[5] == 0) continue;
					if((i == 1 || i == 2) && val[i * 2 - 1] == 0 && val[i * 2 + 3] == 0) continue;
					System.out.print(cust);
					fillSpace(10, cust.length());
					System.out.print(prod);
					fillSpace(9, prod.length());
					System.out.print("Q" + (i + 1));
					fillSpace(9, 2);
					if(i == 0 || val[i * 2 - 1] == 0) {
						fillSpace(10, 6);
						System.out.print("<null>");
					} else {
						fillSpace(10, numberSpace(val[i * 2 - 2] / val[i * 2 - 1]));
						System.out.print(val[i * 2 - 2] / val[i * 2 - 1]);
					}
					
					if(i == 3 || val[i * 2 + 3] == 0) {
						fillSpace(11, 6);
						System.out.print("<null>");
					} else {
						fillSpace(11, numberSpace(val[i * 2 + 2] / val[i * 2 + 3]));
						System.out.print(val[i * 2 + 2] / val[i * 2 + 3]);
					}
					
					System.out.println();
					
				}
				
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
		
	public static void main(String[] args) { 
		sqad22 sales = new sqad22();
		sales.signin();
		sales.printout();
		
	} 
}