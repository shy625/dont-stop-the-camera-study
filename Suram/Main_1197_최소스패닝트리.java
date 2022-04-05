import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
	static int V;
	static int E;
	static Edge[] list; 
	static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	static int result;

	static class Edge implements Comparable<Edge> {
		int weight;
		int v1;
		int v2;

		public Edge(int v1, int v2, int w) {
			this.weight = w;
			this.v1 = v1;
			this.v2 = v2;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static int find(int x) {
		if (parent[x] == x)
			return parent[x];
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if(rootx > rooty) {
			parent[rootx] = rooty;
		}
		else {
			parent[rooty] = rootx;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V + 1];
		list = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(v1,v2,weight));
		}

		
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if (find(cur.v1) == find(cur.v2))continue;
			union(cur.v1, cur.v2);
			result += cur.weight;
		}
		System.out.println(result);

	}
}
