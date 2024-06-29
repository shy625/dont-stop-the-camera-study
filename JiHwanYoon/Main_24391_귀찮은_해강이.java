

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_24391_귀찮은_해강이 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] group;
	static Queue<Integer> q;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
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
		// BFS 수행을 통해 밖으로 이동하지 않아도 되는 건물들끼리 묶는다.
		q = new LinkedList<>();
		group = new int[N+1];
		visited = new boolean[N+1];
		int idx = 1;
		for (int i = 1; i <= N; i++) {
			if (group[i] == 0) bfs(i, idx++);
		}
		int[] timetable = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			timetable[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			if (group[timetable[i]] != group[timetable[i-1]]) cnt++;
		}
		System.out.println(cnt);
	}
	// BFS를 통해 u와 같은 그룹에 속하는 건물들을 찾는 함수
	private static void bfs(int u, int idx) {
		q.clear();
		q.offer(u);
		visited[u] = true;
		group[u] = idx;
		while (!q.isEmpty()) {
			u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					group[v] = idx;
					q.offer(v);
				}
			}
		}		
	}

}
