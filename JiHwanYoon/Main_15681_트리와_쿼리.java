

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15681_트리와_쿼리 {
	static int N, R, Q;
	static ArrayList<ArrayList<Integer>> graph; // 간선들을 저장하는 그래프
	static int[] nums; // 각 정점에 대하여 그 정점을 루트로 하는 서브트리에 속한 정점의 개수를 저장하는 배열
	static boolean[] visited; // 각 정점을 방문했는지를 나타내는 배열
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 그래프의 방향성이 없으므로 양방향으로 간주하고 저장한다.
		for (int e = 0; e < N-1; e++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			graph.get(U).add(V);
			graph.get(V).add(U);
		}
		nums = new int[N+1];
		visited = new boolean[N+1];
		// DFS를 통해 각 정점에 대해 서브트리의 정점의 개수를 구한다.
		tree(R);
		// 각 쿼리에 대해 답한다.
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			sb.append(nums[q]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// DFS를 이용해 각 정점을 루트로 하는 서브트리의 정점의 개수를 구한다.
	private static void tree(int u) {
		// 우선 해당 정점을 방문 처리
		visited[u] = true;
		// 개수 셀 때 정점 자신 포함
		nums[u] = 1;
		// 자식 정점을 루트로 하는 서브트리의 정점 개수를 센 뒤 
		// 현재 정점을 루트로 하는 서브트리의 정점의 개수에 더해준다.
		for (int v : graph.get(u)) {
			if (!visited[v]) {
				tree(v);
				nums[u] += nums[v];
			}
		}		
	}
}
