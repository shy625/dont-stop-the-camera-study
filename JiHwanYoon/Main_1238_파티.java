

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_1238_파티 {
	
	static int N, M, X;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		X = Integer.parseInt(s[2]);
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		// 기존 그래프와 간선의 방향이 반대인 그래프도 만든다.
		ArrayList<ArrayList<int[]>> invGraph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			invGraph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			s = br.readLine().split(" ");
			int start = Integer.parseInt(s[0]);
			int end = Integer.parseInt(s[1]);
			int t = Integer.parseInt(s[2]);
			graph.get(start).add(new int[] {end, t});
			invGraph.get(end).add(new int[] {start, t});
		}
		// 임의의 v에 대해 v -> X로 가는 최소 비용을 구하기 위해 dijkstra's algorithm을 N-1번 반복하기 보다는,
		// 그래프의 간선 방향을 반대로 바꾸고 X -> v로 가는 최소 비용을 구하는 편이 훨씬 효율적이다.
		int[] dijk1 = dijkstra(X, invGraph);
		int[] dijk2 = dijkstra(X, graph);
		// X 마을까지 갔다 오는 데 가장 오래 걸리는 경우를 저장
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dijk1[i] + dijk2[i]);
		}
		System.out.println(max);
	}
	
	// dijkstra's algorithm
	private static int[] dijkstra(int start, ArrayList<ArrayList<int[]>> graph) {
		boolean[] visited = new boolean[N+1];
		int[] dijk = new int[N+1];
		Arrays.fill(dijk, Integer.MAX_VALUE);
		dijk[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		pq.offer(new int[] {start, 0});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[0]]) continue;
			visited[cur[0]] = true;
			for (int[] next : graph.get(cur[0])) {
				if (!visited[next[0]] && dijk[next[0]] > dijk[cur[0]] + next[1]) {
					dijk[next[0]] = dijk[cur[0]] + next[1];
					pq.offer(new int[] {next[0], dijk[next[0]]});
				}
			}
		}
		return dijk;
	}

}
