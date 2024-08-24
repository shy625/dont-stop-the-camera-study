

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1595_북쪽나라의_도로 {
	// 각 도시의 번호와, 해당 도시까지 이동한 거리를 나타내는 클래스
	static class Node {
		int u;
		long distance;
		public Node(int u, long distance) {
			this.u = u;
			this.distance = distance;
		}		
	}
	static ArrayList<ArrayList<Node>> graph;
	static boolean[] visited;
	static int maxIdx;
	static long max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new ArrayList<>();
		for (int i = 0; i <= 10000; i++) {
			graph.add(new ArrayList<>());
		}
		while (true) {
			String s = br.readLine();
			if (s == null || s.length() == 0) break;
			StringTokenizer st = new StringTokenizer(s);
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}
		// DFS를 1번째 노드에 대해 수행해, 가장 먼 거리에 있는 노드를 찾는다.
		// 그리고, 그 노드에 대해 DFS를 수행해 이동거리가 가장 긴 경우를 찾는다.
		visited = new boolean[10001]; // 방문 배열
		max = 0;
		dfs(1, 0);
		dfs(maxIdx, 0);
		System.out.println(max);
	}
	private static void dfs(int u, long distance) {
		visited[u] = true;
		if (max < distance) {
			maxIdx = u;
			max = distance;
		}
		for (Node next : graph.get(u)) {
			if (visited[next.u]) continue;
			dfs(next.u, distance + next.distance);
		}
		visited[u] = false;
	}

}
