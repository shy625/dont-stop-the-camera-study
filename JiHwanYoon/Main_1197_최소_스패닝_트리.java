

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_1197_최소_스패닝_트리 {
	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		// u, v는 간선의 양 끝 정점, w는 해당 간선의 가중치를 의미한다.
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
	// union find를 하기 위한 배열
	static int[] parents;
	static int[] ranks;
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int V = Integer.parseInt(s[0]);
		int E = Integer.parseInt(s[1]);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			s = br.readLine().split(" ");
			int A = Integer.parseInt(s[0]);
			int B = Integer.parseInt(s[1]);
			int C = Integer.parseInt(s[2]);
			pq.offer(new Edge(A, B, C));
		}
		parents = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		ranks = new int[V+1];
		Arrays.fill(ranks, 1);
		// 최소 스패닝 트리의 가중치 합
		// 계산 도중에 int 범위를 넘어설 수 있어 long 타입으로 선언
		long MSTWeight = 0;
		// 지금까지 스패닝 트리에 추가한 간선의 개수
		int cnt = 0;
		// Kruskal's algorithm
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (union(e.u, e.v)) {
				MSTWeight += e.w;
				cnt++;
			}
			// 최소 스패닝 트리는 간선 개수가 V-1개이므로 이에 도달하면 남은 간선들은 고려할 필요가 없다.
			if (cnt == V-1) {
				break;
			}
		}
		System.out.println(MSTWeight);
	}
	
	// union find
	private static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) return false;
		if (ranks[u] > ranks[v]) parents[v] = u;
		else {
			parents[u] = v;
			if (ranks[u] == ranks[v]) {
				ranks[v]++;
			}
		}
		return true;
	}

	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		return parents[u] = find(parents[u]);
	}

}
