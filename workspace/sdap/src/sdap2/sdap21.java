package sdap2;
import java.sql.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class sdap21 {

    private static int checkQuarter(int month) {
        if (month < 4) {
            return 1;
        } else if (month < 7) {
            return 2;
        } else if (month < 10) {
            return 3;
        } else if (month < 13) {
            return 4;
        }
        return 0;
    }

    public static void main(String[] args) {
        String usr = "postgres";
        String pwd = "1qaz2wsx";
        String url = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Successfully loaded the driver!");
        }

        catch (Exception e) {
            System.out.println("Failed to load the driver!");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Success connecting server!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

            List<StatisticsSample3> list = new CopyOnWriteArrayList< >();
            while (rs.next()) {
                int i;
                if (list.isEmpty()) {
                    for (i = 1; i < 5; i++) {
                        list.add(new StatisticsSample3(rs.getString("cust"), rs.getString("prod"), i));
                    }
                }
                int j = 1;
                for (StatisticsSample3 s : list) {
                    if (s.getName().equals(rs.getString("cust")) && s.getProduct().equals(rs.getString("prod"))) {
                        break;
                    } else if (j == list.size()) {
                        for (i = 1; i < 5; i++) {
                            list.add(new StatisticsSample3(rs.getString("cust"), rs.getString("prod"), i));
                        }
                    } else {
                        j++;
                    }
                }

                int q = checkQuarter(rs.getInt("month"));
                for (StatisticsSample3 s : list) {
                    if (s.getName().equals(rs.getString("cust")) && s.getProduct().equals(rs.getString("prod")) && s.getQuarter() == q + 1) {
                        s.updateBefore(rs.getInt("quant"));
                    }
                    if (s.getName().equals(rs.getString("cust")) && s.getProduct().equals(rs.getString("prod")) && s.getQuarter() == q - 1) {
                        s.updateAfter(rs.getInt("quant"));
                    }
                }
            }

            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Sales");
            while(rs2.next()){
                int q = checkQuarter(rs2.getInt("month"));
                for (StatisticsSample3 s : list) {
                    if (s.getName().equals(rs2.getString("cust")) && s.getProduct().equals(rs2.getString("prod")) && s.getQuarter() + 1 == q && s.getAfter() <= rs2.getInt("quant")) {
                        s.countAfter();
                    }
                    if (s.getName().equals(rs2.getString("cust")) && s.getProduct().equals(rs2.getString("prod")) && s.getQuarter() - 1 == q && s.getBefore() <= rs2.getInt("quant")) {
                        s.countBefore();
                    }
                }

            }

            System.out.println(
                    "CUSTOMER" + "  " + "PRODUCT" + "  " + "QUARTER" + "  " + "BEFORE_TOT" + "  " + "AFTER_TOT");
            System.out.println(
                    "========" + "  " + "=======" + "  " + "=======" + "  " + "==========" + "  " + "=========");

            int i = 1;
            for (StatisticsSample3 s : list) {
                System.out.println(s.outPut());
                //System.out.println(i);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Connection URL or username or password errors!");
            e.printStackTrace();
        }

    }
}


class StatisticsSample3 extends StatisticsSample {
	private String cust, prod;
	private int avg, otherProdAvg, otherCustAvg, avgCount, otherProdCount, otherCustCount;

	public SalesQuery1(String cust, String prod) {
		this.cust = cust;
		this.prod = prod;
		avg = 0;
		otherProdAvg = 0;
		otherCustAvg = 0;
		avgCount = 0;
		otherProdCount = 0;
		otherCustCount = 0;
	}
	
	public String getName(){
		return cust;
	}
	
	public String getProduct(){
		return prod;
	}

	public void update(String cust, String prod, int quan) {
		if (this.cust.equals(cust) && this.prod.equals(prod)) {
			avg = (avg * avgCount + quan) / (avgCount + 1);
			avgCount = avgCount + 1;
		} else if (this.cust.equals(cust)) {
			otherProdAvg = (otherProdAvg * otherProdCount + quan) / (otherProdCount + 1);
			otherProdCount = otherProdCount + 1;
		} else if (this.prod.equals(prod)) {
			otherCustAvg = (otherCustAvg * otherCustCount + quan) / (otherCustCount + 1);
			otherCustCount = otherCustCount + 1;
		}
	}

	public String outPut() {
		return format(cust, 10) + format(prod, 12 + formatspace(avg))
				+ format(getString(avg), 16 - formatspace(avg) + formatspace(otherProdAvg))
				+ format(getString(otherProdAvg), 16 - formatspace(otherProdAvg) + formatspace(otherCustAvg))
				+ getString(otherCustAvg);
	}
}


class StatisticsSample {

    public String getString(int num) {
        if (num == 0 || num == 99999) {
            return "null";
        }
        return Integer.toString(num);
    }


    public static String format(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static int formatspace(int n) {
        if (n < 10 && n > 0) {
            return 3;
        }
        if (n < 100 && n > 0) {
            return 2;
        }
        if (n < 1000 && n > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
