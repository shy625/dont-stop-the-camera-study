

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13265_색칠하기 {
	static Queue<Integer> q;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;
	static boolean pos;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
			visited = new int[N+1]; // 방문 배열이나, 1은 하나의 색, 2는 1과 다른 색을 의미
			pos = true; // 2가지 색으로 색칠이 가능한지 나타내는 변수
			for (int i = 1; i <= N; i++) {
				if (!pos) break;
				if (visited[i] == 0) bfs(i);
			}
			sb.append(pos ? "possible" : "impossible").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// BFS를 활용해 그래프를 2가지 색으로 색칠할 수 있는지 확인하는 함수
	private static void bfs(int u) {
		q = new LinkedList<>();
		q.offer(u);
		visited[u] = 1;
		while (!q.isEmpty()) {
			u = q.poll();
			for (int v : graph.get(u)) {
				// 인접한 두 정점이 같은 색인 경우
				if (visited[v] == visited[u]) {
					pos = false;
					return;
				}
				if (visited[v] > 0) continue;
				// 인접한 두 정점을 다른 색으로 색칠한다.
				visited[v] = visited[u] == 1 ? 2 : 1;
				q.offer(v);
			}
		}
	}

}
