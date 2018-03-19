package sdap;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry; 
public class sdap2qyf { 
	// store the date in the list
	private TreeMap<String, TreeMap<String, int[]>> data;
	private HashMap<String, int[]> prod_amount;
	private HashMap<String, int[]> cust_amount;
	private int total;
	
	public sdap2qyf() {
	// constructor initialize the storage structure 
		data = new TreeMap<String, TreeMap<String, int[]>>();
		cust_amount = new HashMap<>();
		prod_amount = new HashMap<>();
		total = 0;
	}
	//this method is for load the data in the database
	//deploy only once!!!!!
	public void load() {
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
	public void report(){
		System.out.println("CUSTOMER  PRODUCT  THE_AVG  OTHER_PROD_AVG  OTHER_CUST_AVG");
		System.out.println("--------  -------  -------  --------------  --------------");
		for(Entry<String, TreeMap<String, int[]>> e : data.entrySet()) {
			String cust = e.getKey();
			for(Entry<String, int[]> e1 : e.getValue().entrySet()) {
				String prod = e1.getKey();
				int[] amo = e1.getValue();
				int[] camo = cust_amount.get(cust);
				int[] pamo = prod_amount.get(prod);
				System.out.print(cust);
				fillSpace(10, cust.length());
				System.out.print(prod);
				fillSpace(9, prod.length());
				System.out.print(amo[0] / amo[1] + "     ");
				System.out.print((camo[0] - amo[0]) / (camo[1] - amo[1]) + "         ");
				System.out.print((pamo[0] - amo[0]) / (pamo[1] - amo[1]) + "    ");
				System.out.println();
			}
		} 
	}
	// this method is for fill the space to make the text on left or right
	private void fillSpace(int n, int r) {
		for(int i = 0; i < n - r; i++)
			System.out.print(" ");
	}
	public static void main(String[] args) { 
		sdap2qyf sales = new sdap2qyf();
		sales.load();
		sales.report();
		
	} 
}