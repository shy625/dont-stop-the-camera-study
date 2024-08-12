

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_24230_트리_색칠하기 {
	static int N;
	static int[] C;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		C = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			C[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// DFS를 활용해, 루트 노드부터 시작하면서 자식 노드로 가면
		// 루트 노드를 칠한 색을 자식 노드까지 적용할 수 있다.
		// 만약 자식 노드의 색이 달라지는 경우 그 자식 노드를 루트 노드로 하는 부분 트리를 
		// 동일한 방법으로 탐색한다.
		visited = new boolean[N+1];
		cnt = C[1] == 0 ? 0 : 1; // 색을 칠하는 최소 횟수
		dfs(1, C[1]);
		System.out.println(cnt);
	}
	// DFS를 적용하는 함수
	// 현재 u 노드를 탐색하고 있고, u 노드의 색은 color
	private static void dfs(int u, int color) {
		visited[u] = true;
		for (int v : graph.get(u)) {
			if (visited[v]) continue;
			if (C[v] != color) cnt++;
			dfs(v, C[v]);
		}
	}

}
