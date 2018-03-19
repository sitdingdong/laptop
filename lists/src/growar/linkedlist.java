package growar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class linkedlist {
	private static class Node {
			int val;
			Node next;
			Node(int v, Node next) { val = v; this.next = next; }
		}

		private	Node head;

		private Node tail() { 
			Node p = head;
			if (p == null)
				return null;
			for ( p = head; p.next != null; p = p.next)
				;
			return p;
		}

		public linkedlist() {
			head = null;
		}
		public void addStart(int v) { 
			head =  new Node(v, head);
		}

		public void addEnd(int  v) {
			if (head == null) {
				head = new Node(v, null);
				return;
			}
			tail().next = new Node(v, null);
		}
		public void removeStart() {
			if (head == null) {
				return;
			}
			head = head.next;
		}
		public void removeEnd() {
			if (head == null)
				return ;
			if (head.next == null) {
				head = null;
			}
			Node p = head;
			Node q;
			for (q = p.next; q.next != null;) {
				p = q;
				q = q.next;
			}
			p.next = null;
			return;
		}
		public static void load(String filename) {
			linkedlist ls = new linkedlist();
			Scanner sc = null;
			try {
				sc = new Scanner(new FileReader(filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (sc.hasNext()) {
				while (sc.hasNext("ADD_FRONT")) {
					int start = sc.nextInt();
					int step = sc.nextInt();
					int end = sc.nextInt();
					for(int i = start; i < end; i += step) {
						ls.addStart(i);
					}
				}
				while (sc.hasNext("ADD_END")) {
					int start = sc.nextInt();
					int step = sc.nextInt();
					int end = sc.nextInt();
					for(int i = start; i < end; i += step) {
						ls.addEnd(i);
						}
					}
				while (sc.hasNext("REMOVE_FRONT")) {
					int num = sc.nextInt();
					for(int i = 0; i < num; i++) {
						ls.removeStart();
						}
					}
				while (sc.hasNext("REMOVE_BACK")) {
					int num = sc.nextInt();
					for(int i = 0; i < num; i++) {
						ls.removeEnd();
						}
					}
				while (sc.hasNext("OUTPUT")) {
					while(ls.head !=null) {
						System.out.print(ls.head.val);
						ls.head = ls.head.next;
					}
					System.out.println();
				}
				
			}
				
		}
		public static void main(String[] args) {
			//load("HW4B.txt");
			linkedlist ls = new linkedlist();
			ls.addEnd(4);
			ls.addEnd(6);
			System.out.println(ls.head.val);
		}

}
