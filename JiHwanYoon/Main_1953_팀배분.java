

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1953_팀배분 {
	static int N, cnt;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>(); // 싫어하는 사람 간 관계를 간선으로 하는 그래프 
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int k = Integer.parseInt(st.nextToken());
				graph.get(i).add(k);
				graph.get(k).add(i);
			}
		}
		visited = new int[N+1]; // 각 인원에 대해 어느 팀에 속하는지를 나타내는 배열(청팀은 1, 백팀은 2로 표시)
		cnt = 0; // 청팀에 속하는 인원 수
		// 모든 인원에 대해 팀을 배정하기 위해, 각 인원에 대해 아직 팀에 속하지 않았다면 bfs 함수 수행 
		for (int i = 1; i <= N; i++) {
			if (visited[i] == 0) bfs(i);
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int i = 1; i <= N; i++) {
			if (visited[i] == 1) sb.append(i).append(" ");
		}
		sb.setLength(sb.length()-1);
		sb.append("\n");
		sb.append(N-cnt).append("\n");
		for (int i = 1; i <= N; i++) {
			if (visited[i] == 2) sb.append(i).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 너비 우선 탐색을 이용해, 이분 그래프를 만드는 함수
	// 그래프에서 인접한 정점을 만날 때마다 반대 팀에 넣는 방식으로 진행
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(i);
		visited[i] = 1; // 첫 인원은 청팀에 넣는다.
		cnt++;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (visited[v] == 0) {
					visited[v] = visited[u] == 1 ? 2 : 1;
					q.offer(v);
					if (visited[v] == 1) cnt++;
				}
			}
		}
	}

}
