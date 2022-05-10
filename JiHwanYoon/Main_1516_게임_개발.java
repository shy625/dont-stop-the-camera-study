

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1516_게임_개발 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N+1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		// 위상 정렬을 위해 (간선을 통해) 직접적으로 연관되어 있는, 이전에 지어야 하는 건물의 개수를 저장한다.
		int[] count = new int[N+1];
		StringTokenizer st;
		int temp;
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while ((temp = Integer.parseInt(st.nextToken())) != -1) {
				graph.get(temp).add(i);
				count[i]++;
			}
		}
		// 위상 정렬과 dynamic programming을 통해 각 건물을 짓기 위해 필요한 최소 시간을 계산한다.
		Queue<Integer> q = new LinkedList<>();
		int[] dp = new int[N+1];
		// 이전에 건설해야 할 건물이 없는 건물들을 우선적으로 탐색한다.
		for (int i = 1; i <= N; i++) {
			if (count[i] == 0) {
				q.offer(i);
				dp[i] = time[i];
			}
		}
		// 위상 정렬
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				// 이전에 건설해야 하는 건물 중 가장 오래 걸리는 건물이 완성되어야 해당 건물을 지을 수 있기 때문에
				// dp[u] + time[v]의 최댓값을 찾는다.
				dp[v] = Math.max(dp[v], dp[u] + time[v]);
				count[v]--;
				if (count[v] == 0) {
					q.offer(v);
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
