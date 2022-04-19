

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10282_해킹 {

	public static void main(String[] args) throws IOException {
		// 각종 변수들
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int n, d, c, a, b, s;
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		// 각 테스트케이스에 대해
		for (int t = 1; t <= T; t++) {
			// 입력
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph.clear(); // 이전 테스트케이스의 그래프를 제거
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				graph.get(b).add(new int[] {a, s});
			}
			// 다익스트라를 통해 감염이 가능한 모든 컴퓨터가 감염될 때까지 걸리는 최소 시간 측정
			int[] dijk = new int[n+1];
			Arrays.fill(dijk, Integer.MAX_VALUE);
			dijk[c] = 0;
			boolean[] visited = new boolean[n+1];
			PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
			pq.offer(new int[] {c, 0});
			while (!pq.isEmpty()) {
				int[] u = pq.poll();
				if (visited[u[0]]) continue;
				visited[u[0]] = true;
				for (int[] v : graph.get(u[0])) {
					if (!visited[v[0]] && dijk[v[0]] > dijk[u[0]] + v[1]) {
						dijk[v[0]] = dijk[u[0]] + v[1];
						pq.offer(new int[] {v[0], dijk[v[0]]});
					}
				}
			}
			// 다익스트라 수행 후 해킹된 컴퓨터와 연결된 컴퓨터의 수와
			// 해킹된 컴퓨터와 연결된 컴퓨터 중 가장 감염되는데 걸린 시간이 긴 컴퓨터를 찾는다.
			int cnt = 0;
			int max = 0;
			for (int i = 1; i <= n; i++) {
				if (dijk[i] != Integer.MAX_VALUE) {
					cnt++;
					max = Math.max(dijk[i], max);
				}
			}
			// 시간 단축을 위해 StringBuilder를 활용한다.
			sb.append(cnt).append(" ").append(max).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());

	}

}
