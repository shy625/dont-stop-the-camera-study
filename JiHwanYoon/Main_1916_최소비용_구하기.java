

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1916_최소비용_구하기 {
	// 현재 위치와 해당 위치까지 도달하기 위한 비용을 저장하는 클래스
	static class Loc implements Comparable<Loc> {
		int u;
		long cost;
		public Loc(int u, long cost) {
			this.u = u;
			this.cost = cost;
		}
		public int compareTo(Loc l) {
			return Long.compare(this.cost, l.cost);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		// dijkstra's algorithm을 활용
		long[] dp = new long[N];
		Arrays.fill(dp, Long.MAX_VALUE/10);
		dp[start] = 0;
		PriorityQueue<Loc> pq = new PriorityQueue<>((x, y) -> Long.compare(x.cost, y.cost));
		boolean[] visited = new boolean[N];
		pq.offer(new Loc(start, 0));
		while (!pq.isEmpty()) {
			Loc u = pq.poll();
			// 목적지에 도달한 경우
			if (u.u == end) {
				System.out.println(dp[end]);
				return;
			}
			visited[u.u] = true;
			for (int[] v : graph.get(u.u)) {
				if (!visited[v[0]] && dp[v[0]] > dp[u.u] + v[1]) {
					dp[v[0]] = dp[u.u] + v[1]; 
					pq.offer(new Loc(v[0], dp[v[0]]));
				}
			}
		}
	}

}
