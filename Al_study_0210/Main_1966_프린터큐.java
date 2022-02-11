import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1966_프린터큐 {
	static int T;
	static int N, M;
	static Queue<Integer> dd; 
	static Queue<Integer> index; 
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt(); 
		for (int t = 1 ; t <= T ; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			dd = new LinkedList<Integer>();
			index = new LinkedList<Integer>();
			count = 0;
			for (int i = 0 ; i < N ; i++) {
				dd.offer(sc.nextInt());
				if(i!=M) index.offer(0);
				else index.offer(1);
			}
			while(!dd.isEmpty()) {
				check();
			}
		}
	}
	
	public static void check() {
		int element = dd.poll(); 
		int ind = index.poll();
		boolean addq = false;
	
		for (int i = 0 ; i < dd.size(); i++) {
			int compare = dd.poll();
			int x = index.poll();			
			if (compare > element) {
				addq = true;
			}
			dd.offer(compare);
			index.offer(x);
			
		}
		if(addq) {
			dd.offer(element);
			index.offer(ind);			
		} else {
			count++;
			if(ind == 1) System.out.println(count);
			
		}
	}
}
