

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17142_연구소_3 {
	static int N, M;
	static int[][] map;
	static int min;
	static ArrayList<int[]> virus;
	static int num;
	static int[] v;
	static int[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		v = new int[M];
		min = Integer.MAX_VALUE;
		virus = new ArrayList<>(); // 바이러스의 위치를 저장하는 ArrayList
		visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virus.add(new int[] {i, j});
			}
		}
		num = virus.size(); // 바이러스 개수
		// 바이러스를 퍼뜨리는 최단 시간을 구한다.
		combi(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// 조합을 활용해 바이러스를 퍼뜨리는 최단 시간을 구하는 함수
	private static void combi(int cnt, int start) {
		// M개의 활성화할 바이러스를 선택한 경우
		if (cnt == M) {
			// BFS를 통해 바이러스가 퍼지는 시간을 구한다.
			int res = bfs();
			min = Math.min(res, min); // 최단 시간 갱신
			return;
		}
		for (int i = start; i < num; i++) {
			v[cnt] = i;
			combi(cnt+1, i+1);
		}
	}
	// BFS를 활용해 바이러스가 퍼지는 최단 시간을 구하는 함수
	private static int bfs() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		Queue<int[]> q = new LinkedList<>();
		// 활성화할 바이러스를 큐에 넣는다.
		for (int i : v) {
			int[] vi = virus.get(i);
			q.add(vi);
			visited[vi[0]][vi[1]] = 0;
		}
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<N && visited[nr][nc] == -1 && map[nr][nc] != 1) {
						visited[nr][nc] = visited[u[0]][u[1]] + 1;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
		}
		// 바이러스가 퍼지는데 걸린 시간을 구한다.
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == -1 && map[i][j] == 0) return Integer.MAX_VALUE; // 바이러스가 퍼지지 않은 지점이 있는 경우
				if (map[i][j] != 2) max = Math.max(max, visited[i][j]);
			}
		}
		return max;
	}

}
