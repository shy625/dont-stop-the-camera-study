

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_1504_특정한_최단_경로 {
	
	static ArrayList<ArrayList<int[]>> graph;
	static int N, E;
	// 다익스트라 적용 시 초기값으로 Integer.MAX_VALUE로 하면 아래에서 MAX 값끼리 더하는 경우가 생길 수 있어 integer overflow가 발생할 수 있다.
	static int MAX = 100_000_000; 
	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			int c = Integer.parseInt(s[2]);
			// 양방향 그래프
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
		s = br.readLine().split(" ");
		int v1 = Integer.parseInt(s[0]);
		int v2 = Integer.parseInt(s[1]);
		// 크게 두 가지 경우로 나눌 수 있다.
		// 1. 1 -> v1 -> v2 -> N
		// 2. 1 -> v2 -> v1 -> N
		// 각 경우에 대해 다익스트라를 세 번 적용해 각 출발지와 도착지 사이의 최단거리를 구한 뒤 더해준다.
		int result = Math.min(dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N), 
					dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N));
		// result가 MAX보다 크면 경유지를 통해 정점 N까지 가는 방법이 없다는 뜻이므로 -1을 출력한다.
		System.out.println(result >= MAX ? -1 : result);
	}
	// dijkstra's algorithm
	private static int dijkstra(int u, int v) {
		int[] dijk = new int[N+1];
		Arrays.fill(dijk, MAX);
		dijk[u] = 0;
		boolean[] visited = new boolean[N+1];
		// {정점 번호, dijk[정점 번호]}을 담는 우선순위 큐를 만든다.
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		pq.offer(new int[] {u, 0});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			// 우선순위 큐에서 v가 나왔다는 것은 목적지까지의 최단 거리를 다 구했다는 의미다.
			if (cur[0] == v) break;
			if (visited[cur[0]]) continue;
			visited[cur[0]] = true;
			for (int[] next : graph.get(cur[0])) {
				if (!visited[next[0]] && dijk[next[0]] > dijk[cur[0]] + next[1]) {
					dijk[next[0]] = dijk[cur[0]] + next[1];
					pq.offer(new int[] {next[0], dijk[next[0]]});
				}
			}
		}
		return dijk[v];
	}
}
