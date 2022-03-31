

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1647_도시_분할_계획 {
	static int[] parents;
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
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(u, v, w));
		}
		// Kruskal's algorithm 이용해 MST를 찾되, 두 개의 마을로 분할하기 위해서는  MST에서 하나의 간선을 빼야 한다.
		// 따라서 비용을 최소로 하기 위해 비용이 가장 큰 간선을 빼거나, 아님 MST를 만들 때 N-1개가 아닌 N-2개의 간선만 찾으면 된다.
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		int cost = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cost += e.w;
				cnt++;
			}
			if (cnt == N-2) break;
		}
		System.out.println(cost);
	}
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
