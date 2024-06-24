

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14621_나만_안_되는_연애 {
	static int N, M;
	static int[] parents;
	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		int u, v, d;
		public Edge(int u, int v, int d) {
			this.u = u;
			this.v = v;
			this.d = d;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.d, e.d);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 각 대학의 성별 기록
		char[] genders = new char[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			genders[i] = st.nextToken().charAt(0);
		}
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			// 간선의 양끝 지점 대학이 다른 성별인 경우에만 유의미한 간선이다.
			if (genders[u] != genders[v]) {
				pq.offer(new Edge(u, v, d));
			}
		}
		// Kruskal's algorithm을 이용하기 위한 부모 노드 저장 배열
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		int cost = 0;
		// Kruskal's algorithm
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cost += e.d;
			}
		}
		// 모든 대학이 연결되었는지를 확인한다.
		int root = find(1);
		for (int i = 2; i <= N; i++) {
			if (root != find(i)) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		System.out.println(cost);
	}
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
