

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_ABCDE {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			// 각 사람이 ABCDE 친구 관계를 가지는지 확인한다.
			if (check(i, 0, visited)) {
				System.out.println(1);
				System.exit(0);
			}
		}
		System.out.println(0);
	}
	// i번째 사람이 ABCDE 친구 관계를 가지는지 DFS를 통해 확인하는 함수
	private static boolean check(int u, int cnt, boolean[] visited) {
		if (cnt == 4) {
			return true;
		}
		ArrayList<Integer> nexts = graph.get(u);
		if (nexts.size() == 0) return false;
		for (int v : nexts) {
			if (visited[v]) continue;
			visited[v] = true;
			if (check(v, cnt+1, visited)) return true;
			visited[v] = false;
		}
		return false;
	}

}
