package growar;

public class runs {
	public static void main(String[] args) {
		ConvexGrid cg = new ConvexGrid(16);
		cg.read("convexhullpoints.dat");
		cg.printAllListSize();
		cg.printMinMax();
		cg.printPerimeterClockWiseOrder();
	}

}
