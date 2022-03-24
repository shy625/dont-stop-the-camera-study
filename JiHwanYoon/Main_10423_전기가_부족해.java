

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10423_전기가_부족해 {
	static int[] parents;
	static boolean[] isGenerator; // 발전기가 설치된 도시를 나타내는 배열
	// 간선 클래스
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.w, e.w);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// Kruskal's algorithm에서 union find를 하기 위한 부모 노드 저장 배열
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		isGenerator = new boolean[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			int k = Integer.parseInt(st.nextToken());
			isGenerator[k] = true;
		}
		// 간선을 비용 순서대로 정렬하면서 넣는다.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(u, v, w));
		}
		int cost = 0;
		// Kruskal's algorithm
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			// 간선의 양끝 노드의 집합의 루트가 발전기가 설치된 도시라면 연결할 필요없다. 
			if (isGenerator[find(e.u)] && isGenerator[find(e.v)]) continue;
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cost += e.w;
			}
		}
		System.out.println(cost);
	}
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// union할 때 되도록 집합의 루트가 발전기가 설치된 도시가 되도록 한다.
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			if (isGenerator[u]) parents[v] = u;
			else parents[u] = v;
		}
	}

}
