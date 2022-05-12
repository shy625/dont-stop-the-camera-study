

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_13418_학교_탐방하기 {
	static int N, M;
	static int[] parents;
	static int[] ranks;
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.w, e.w);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// Kruskal's Algorithm을 우선순위에 따라 2번 적용해야 하기 때문에, 
		// 간선을 우선순위 큐에 넣지 않고 ArrayList에 넣었다.
		ArrayList<Edge> q = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 오르막길을 1, 내리막길을 0으로 바꿔줬다.
			int w = Integer.parseInt(st.nextToken()) ^ 1;
			q.add(new Edge(u, v, w));
		}
		// ArrayList 오름차순 정렬 -> 내리막길이 앞에 있다.
		Collections.sort(q);
		int min = kruskal(q, false);
		int max = kruskal(q, true);
		System.out.println(max*max - min*min);
	}
	// Kruskal's Algorithm을 적용하나, reverse가 true면 오르막길이 우선순위가 높고, 
	// reverse가 false면 내리막길이 우선순위가 높다.(기본적인 Kruskal's Algorithm)
	private static int kruskal(ArrayList<Edge> q, boolean reverse) {
		int cnt = 0;
		parents = new int[N+1];
		ranks = new int[N+1];
		Arrays.fill(ranks, 1);
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		int edge = 0;
		Edge e;
		// 우선순위 큐에서 Edge를 하나씩 빼내는 방식이 아닌 ArrayList를 왼쪽(오른쪽)부터 순차적으로 탐색하는 방식 사용
		for (int i = 0; i <= M; i++) {
			if (reverse) e = q.get(M-i);
			else e = q.get(i);
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cnt += e.w;
				edge++;
			}
			if (edge == N) break;
		}
		return cnt;
	}
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			if (ranks[u] > ranks[v]) {
				parents[v] = u;
			} else {
				parents[u] = v;
				if (ranks[u] == ranks[v]) ranks[v]++;
			}
			
		}
	}
}
