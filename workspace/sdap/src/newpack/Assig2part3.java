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
package newpack;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry; 
public class Assig2part3 { 
	// store the date in the list
	private TreeMap<String, TreeMap<String, int[]>> data3;

	
	public Assig2part3() {
	// constructor initialize the storage structure 
		data3 = new TreeMap<String, TreeMap<String, int[]>>();
	}
	//read the data in the database
	public void signin() {
		// the basic info of the database
		TreeMap<String, TreeMap<String, int[]>> data = new TreeMap<String, TreeMap<String, int[]>>();
		TreeMap<String, TreeMap<String, List<Integer>>> data1 = new TreeMap<>();
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
				int month = Integer.parseInt(rs.getString("month"));
				int quant = Integer.parseInt(rs.getString("quant"));
				
				if(!data.containsKey(cust)) {
					data.put(cust, new TreeMap<>()); 
					data1.put(cust, new TreeMap<>());
				}
				TreeMap<String, int[]> temp = data.get(cust);
				if(!temp.containsKey(prod)) {
					temp.put(prod, new int[12]);
					data1.get(cust).put(prod, new ArrayList<>());
				}
				//temp1 is to store the avg and min
				int[] temp1 = temp.get(prod);
				//temp2 is to store the base data of each row
				TreeMap<String, List<Integer>> temp2 = data1.get(cust);
				List<Integer> ls = temp2.get(prod);
				if(month <= 3) {
					temp1[0] += quant;
					temp1[1] ++;
					temp1[2] = temp1[2] == 0 ? quant : Math.min(temp1[2], quant);
					ls.add(1);
					ls.add(quant);
				} else if(month <= 6) {
					temp1[3] += quant;
					temp1[4] ++;
					temp1[5] = temp1[5] == 0 ? quant : Math.min(temp1[5], quant);
					ls.add(2);
					ls.add(quant);
				} else if(month <= 9) {
					temp1[6] += quant;
					temp1[7] ++;
					temp1[8] = temp1[8] == 0 ? quant : Math.min(temp1[8], quant);
					ls.add(3);
					ls.add(quant);
				} else {
					temp1[9] += quant;
					temp1[10] ++;
					temp1[11] = temp1[11] == 0 ? quant : Math.min(temp1[11], quant);
					ls.add(4);
					ls.add(quant);
				}
				temp.put(prod, temp1);
				data.put(cust, temp);
				temp2.put(prod,ls);
				data1.put(cust, temp2);
			}
		} catch (SQLException e) {    
				System.out.println("Connection URL or username or password errors!");    
				e.printStackTrace();   
		}
		for(Entry<String, TreeMap<String, List<Integer>>> e : data1.entrySet()) {
			String cust = e.getKey();
			for(Entry<String, List<Integer>> e1 : e.getValue().entrySet()) {
				String prod = e1.getKey();
				if(!data3.containsKey(cust)) {
					data3.put(cust, new TreeMap<>());
				}
				TreeMap<String, int[]> map = data3.get(cust);
				if(!map.containsKey(prod)) {
					map.put(prod, new int[8]);
				}
				int[] res = map.get(prod);
				List<Integer> ls = e1.getValue();
				int[] amo = data.get(cust).get(prod);
				for(int i = 0; i < ls.size(); i += 2) {
					int quarter = ls.get(i);
					int quant = ls.get(i + 1);
					if(quarter == 1) {
						if(quant < (amo[3] == 0 ? 0 : amo[3] *1.0 / amo[4]) && quant > amo[5]) {
							res[2] ++;
						}
					} else if(quarter == 4) {
						if(quant < (amo[6] == 0 ? 0 : amo[6] *1.0/ amo[7]) && quant > amo[8]) {
							res[5] ++; // 3 after
						}
					} else {
						if(quant < (amo[quarter*3] == 0 ? 0 : amo[quarter*3]*1.0/ amo[quarter*3 +1 ]) && quant > amo[quarter*3 + 2]) {
							res[quarter*2] ++; //before
						}
						if(quant < (amo[quarter*3 - 6] == 0 ? 0 : amo[quarter*3 - 6] *1.0/ amo[quarter*3 - 5]) && quant > amo[quarter*3 - 4]) {
							res[quarter*2 - 3] ++; // 3 after
						}
					}
				}
			}
		}
	}
	public void printout(){
		System.out.println("CUSTOMER" + "  " + "PRODUCT" + "  " + "QUARTER" + "  " + "BEFORE_TOT" + "  " + "AFTER_TOT");
		System.out.println("========" + "  " + "=======" + "  " + "=======" + "  " + "==========" + "  " + "=========");
		for(Entry<String, TreeMap<String, int[]>> e : data3.entrySet()) {
			String cust = e.getKey();
			for(Entry<String, int[]> e1 : e.getValue().entrySet()) {
				String prod = e1.getKey();
				int[] val = e1.getValue();
				for(int i = 0; i < 4; i++) {
					if(val[i * 2] == 0 && val[i * 2 + 1] == 0) continue;
					System.out.print(cust);
					fillSpace(10, cust.length());
					System.out.print(prod);
					fillSpace(9, prod.length());
					System.out.print("Q" + (i + 1));
					fillSpace(9, 2);
					if(val[i * 2] == 0) {
						fillSpace(10, 6);
						System.out.print("<null>");
					} else {
						fillSpace(10, numberSpace(val[i * 2]));
						System.out.print(val[i * 2]);
					}
					
					if(val[i * 2 + 1] == 0) {
						fillSpace(11, 6);
						System.out.print("<null>");
					} else {
						fillSpace(11, numberSpace(val[i * 2 + 1]));
						System.out.print(val[i * 2 + 1]);
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
		Assig2part3 sales = new Assig2part3();
		sales.signin();
		sales.printout();
		
	} 
}
