package growar;

import growar.LinkedList2;

public class Stack {
	private LinkedList2 imp;
	
	public Stack() {
		imp = new LinkedList2();
	}
	public void push(int v) {
		imp.addStart(v);
	}
	public int pop() {
		return imp.removeStart();
	}
	public boolean isEmpty() {
		return imp.isEmpty();
	}

}
