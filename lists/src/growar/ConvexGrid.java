package growar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ConvexGrid {
	private GrowArray[] data;
	private int size;
	private double minx, miny, maxx, maxy;

	public ConvexGrid(int s) {
		data = new GrowArray[s * s];
		size = s;
		for (int i = 0; i < size * size; i++) {
			data[i] = new GrowArray();
		}
	}

	public void read(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		GrowArray temp = new GrowArray();
		minx = miny = Double.MAX_VALUE;
		maxx = maxy = Double.MIN_VALUE;

		while (sc.hasNext()) {
			double i = sc.nextDouble();
			if (minx > i) {
				minx = i;
			}
			if (maxx < i) {
				maxx = i;
			}
			double j = sc.nextDouble();
			if (miny > j) {
				miny = j;
			}
			if (maxy < j) {
				maxy = j;
			}
			temp.addEnd(new Point(i, j));
		}
		sc.close();
		double Axis_x = (maxx - minx) / size;
		double Axis_y = (maxy - miny) / size;
		for (int i = 0; i < temp.length(); i++) {
			Point p = temp.get(i);
			int xp = (int) ((p.x - minx) / Axis_x);
			int yp = (int) ((p.y - miny) / Axis_y);
			xp = xp == size ? xp - 1 : 1;
			yp = yp == size ? yp - 1 : 1;
			data[xp * size + yp].addEnd(p);
		}
	}

	public void printAllListSize() {
		for (int i = 0; i < data.length; i++) {
			System.out.println("for p" + (i + 1) + "have" + data[i].length());
		}
	}

	public void printMinMax() {
		System.out.println("minx" + minx + '\n' + "miny" + miny + '\n' + "maxx" + maxx + '\n' + "maxy" + maxy);
	}

	public void printPerimeterClockWiseOrder() {
		for (int i = 0; i <= size - 1; i++) {
			if(data[i].length() == 0) {
				System.out.println("p" + (i + 1) + ":" + "...");
			} else {
				for (int j = 0; j < data[i].length(); j++) {
					System.out.println("p" + (i + 1) + ":" + data[i].get(j));
				}
			}
		}
		for (int i = (2 * size) - 1; i <= (size * size) - 1; i += size) {
			if(data[i].length() == 0) {
				System.out.println("p" + (i + 1) + ":" + "...");
			} else {
				for (int j = 0; j < data[i].length(); j++) {
					System.out.println("p" + (i + 1) + ":" + data[i].get(j));
				}
			}
		}
		for (int i = (size * size) - 2; i >= size * (size - 1) - 1; i--) {
			if(data[i].length() == 0) {
				System.out.println("p" + (i + 1) + ":" + "...");
			} else {
				for (int j = 0; j < data[i].length(); j++) {
					System.out.println("p" + (i + 1) + ":" + data[i].get(j));
				}
			}
		}
		for (int i = size * (size - 2); i > 0; i -= size) {
			if(data[i].length() == 0) {
				System.out.println("p" + (i + 1) + ":" + "...");
			} else {
				for (int j = 0; j < data[i].length(); j++) {
					System.out.println("p" + (i + 1) + ":" + data[i].get(j));
				}
			}
		}

	}

}

class Point {
	public double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
}

class GrowArray {
	private Point[] data;
	private int used;

	private void Grow() {
		if (used < data.length)
			return;
		Point[] temp = data;
		data = new Point[data.length * 2];
		for (int i = 0; i < used; i++) {
			data[i] = temp[i];
		}
	}

	public GrowArray() {
		data = new Point[1];
		used = 0;
	}

	public GrowArray(int size) {
		size = size == 0 ? 1 : size;
		data = new Point[size];
		used = 0;
	}

	public void addEnd(Point i) {
		Grow();
		data[used++] = i;
	}

	public Point get(int index) {
		return data[index];
	}

	public int length() {
		return used;
	}

}
