

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1926_그림 {
	static int n, m, cnt, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0; // 그림의 개수
		max = 0; // 가장 넓은 그림의 넓이
		// 모든 지점에 대한 너비 우선 탐색을 수행해 그림의 개수와 가장 넓은 그림의 넓이를 찾는다.
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 새로운 그림을 찾은 경우
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}
	// 너비 우선 탐색을 활용해 현재 탐색 중인 그림의 넓이를 구하는 함수
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int area = 1;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					area++;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 그림의 넓이의 최댓값을 갱신
		max = Math.max(max, area);
	}
	private static boolean check(int r, int c) {
		return 0<=r && r<n && 0<=c && c<m;
	}
}
