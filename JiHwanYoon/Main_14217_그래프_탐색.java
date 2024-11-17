

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14217_그래프_탐색 {
	static int n, m;
	static boolean[][] graph;
	static int[] cnts;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new boolean[n+1][n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = true;
		}
		cnts = new int[n+1]; // 각 도시의, 수도를 방문하기 위해 거쳐야 하는 최소 도시의 개수
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = a == 1 ? true : false;
			// 너비 우선 탐색을 수행해 각 도시가 수도를 방문하기 위해 거쳐야 하는 최소 도시의 개수를 구한다.
			// 이 때, 각 도시에서 수도를 방문하는 최단 루트와 수도에서 각 도시를 방문하는 최단 루트는 동일하므로
			// 수도에서 각 도시를 방문하기 위해 거쳐야 하는 최소 도시의 개수를 구한다.
			bfs();
			for (int j = 1; j <= n; j++) {
				sb.append(cnts[j]).append(" ");
			}
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 너비 우선 탐색을 활용해 수도(1번 도시)에서 각 도시를 방문하기 위해 거쳐야 하는 최소 도시의 개수를 구하는 함수
	private static void bfs() {
		Arrays.fill(cnts, -1); // 방문 불가능한 경우를 고려
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		q.offer(1);
		visited[1] = true;
		cnts[1] = 0;
		int l = 1; // 방문한 도시의 개수
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				for (int v = 1; v <= n; v++) {
					if (visited[v] || !graph[u][v]) continue;
					visited[v] = true;
					if (cnts[v] == -1) cnts[v] = l;
					q.offer(v);
				}
				qLen--;
			}
			l++;
		}		
	}

}
