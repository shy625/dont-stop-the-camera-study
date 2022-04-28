

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1939_중량제한 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 양방향 그래프
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		// dijkstra's algorithm을 활용해 특정 지점까지의 최단 거리가 아닌, 특정 지점까지 가는데 버틸 수 있는 중량의 최댓값 저장
		int[] dijk = new int[N+1];
		// 초기값으로 시작 지점은 어떠한 다리도 지나지 않으므로 버틸 수 있는 중량은 무한대라 할 수 있다.
		dijk[start] = Integer.MAX_VALUE;
		// pq에 들어오는 지점들에 대해 가장 버틸 수 있는 중량이 큰 지점부터 탐색한다.
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));
		pq.offer(new int[] {start, Integer.MAX_VALUE});
		boolean[] visited = new boolean[N+1];
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == end) break;
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				// dijkstra's algorithm의 relaxing하는 방식을 약간 비틀어서,
				// 현재 지점까지 버틸 수 있는 중량의 최댓값과 다리가 버틸 수 있는 중량의 최솟값을 통해
				// u[0] 지점을 경유해서 갈 경우 버틸 수 있는 중량의 최댓값과 기존의 v[0] 지점에서의 버틸 수 있는 중량의 최댓값을 비교해
				// 더 큰 값을 저장한다.
				if (!visited[v[0]] && dijk[v[0]] < Math.min(dijk[u[0]], v[1])) {
					dijk[v[0]] = Math.min(dijk[u[0]], v[1]);
					pq.offer(new int[] {v[0], dijk[v[0]]});
				}
			}
		}
		System.out.println(dijk[end]);
	}
}
