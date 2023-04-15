import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
	int from;
	int to;
	int value;
	
	public Node(int from, int to, int value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}

	public int compareTo(Node o) {
		return Integer.compare(this.value, o.value);
	}
	
}

public class BOJ_G3_1414_불우이웃돕기 {
	
	static int N;
	static int [] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int [][] map = new int[N][N];
		int ans = 0;
		
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				if(c >= 'A' && c<= 'Z') {
					map[i][j] = Character.toLowerCase(c)-'a'+1+26;
				} else if (c >= 'a' && c <= 'z') {
					map[i][j] = c - 'a' + 1;
				} else {
					map[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] != 0) {
					ans += map[i][j];
					pq.add(new Node(i+1, j+1, map[i][j]));
				}
			}
		}
	
		int cycle = 1;
		int sum = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int rx = find(cur.from);
			int ry = find(cur.to);
			
			if(rx != ry) {
				cycle++;
				sum += cur.value;
				union(cur.from, cur.to);
			}
		}
		
		if(cycle != N) {
			System.out.println(-1);
		} else {
			System.out.println(ans - sum);
		}
		

	}

	private static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x >= y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
		
	}

}
