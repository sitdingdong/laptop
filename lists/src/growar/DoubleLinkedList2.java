package growar;

public class DoubleLinkedList2 implements Cloneable {
	private static class Node{
		int val;
		Node next, prev;
		Node(int v, Node next, Node prev) {
			v = val;
			this.next = next;
			this.prev = prev;
		}
	}
	private Node head;
	private Node tail;
	public DoubleLinkedList2 clone() {
		DoubleLinkedList2 copy = new DoubleLinkedList2();
		if(this.head == null) {
			copy.head = null;
			copy.tail = null;
			return copy;
		}
		copy.head = new Node(this.head.val, null, null);
		if(head.next == null) {
			return copy;
		}
		Node q = copy.head;
		for(Node p = this.head; p.next != null; p = p.next) {
			Node temp = new Node(p.val, null, q);
			q.next = temp;
			q = q.next;
			
		}
		return copy;
	}
	public DoubleLinkedList2() {
		head = null;
		tail = null;
	}
	public void addStart(int v) {
		Node temp = new Node(v, head, null);
		if(head == null) {
			tail = temp;
		}else {
			head.prev = temp;
		}
		head = temp;
	}
	public void addEnd(int v) {
		Node temp = new Node(v, null, tail);
		if(tail == null) {
			head = temp;
		}else {
			tail.next = temp;
		}
		tail = temp;
	}
	public void removeStart() {
		if(head == null) return;
		if(head.next == null) {
			head = tail = null;
			return;
		}
		head = head.next;
		head.prev = null;
	}
	public void removeEnd() {
		if(tail == null) return;
		if(tail.prev == null) {
			head = tail = null;
			return;
		}
		tail = tail.prev;
		tail.next = null;
	}
	public int size() {
		int count = 0;
		for(Node p = head; p.next != null; p = p.next)
			count++;
		return count;
	}
	public static void main(String[] args) {
		DoubleLinkedList2 a = new DoubleLinkedList2();
		a.addEnd(5);
		//a.addEnd(3);
		//a.addStart(2);
		//a.removeEnd();
		//a.removeStart();
		System.out.println(a.tail.val);
	}
}
