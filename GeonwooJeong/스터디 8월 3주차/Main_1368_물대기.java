import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368_물대기 {
	static int N;
	static int [] W, parent, rank;
	static int [][] map;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int ans;
	
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int cost;
		
		public Edge(int s, int e, int cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		W = new int[N+1];
		parent = new int[N+1];
		rank = new int[N+1];
		map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			W[i] = Integer.parseInt(br.readLine());
			parent[i] = i;
		}
		
		// 직접 우물을 파는 경우를 pq에 넣어준다.
		for (int i = 1; i <= N; i++) {
			pq.add(new Edge(0, i, W[i]));
		}
		
		// pq에 나머지 비용들을 넣는다.
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(i != j) {
					pq.add(new Edge(i, j, map[i][j]));
				} 
			}
			
		}
		
		mst();
		
		System.out.println(ans);

	}

	private static void mst() {
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if(find(cur.s) == find(cur.e)) continue;
			union(cur.s, cur.e);
			ans += cur.cost;
		}
		
	}

	private static void union(int u, int v) {
		int a = find(u);
		int b = find(v);
		if(a != b) {
			if(rank[a] < rank[b]) {
				parent[a] = b;
			} else {
				parent[b] = a;
				if(rank[a] == rank[b]) {
					rank[a]++;
				}
			}
		}
		
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}

}
