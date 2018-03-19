package growar;

public class Queue {
	private LinkedList2 q;
	
	public Queue() {
		q = new LinkedList2();
	}
	public void enQueue(int v) {
		q.addEnd(v);
	}
	public int deQueue() {
		return q.removeStart();
	}
	public boolean isEmpty() {
		return q.isEmpty();
	}

}
