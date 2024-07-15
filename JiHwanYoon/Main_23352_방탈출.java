

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23352_방탈출 {
	static int N, M;
	static int[][] map;
	static int max;
	static int maxLen;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = maxLen = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) bfs(i, j);
			}
		}
		System.out.println(max);
	}
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		int l = 1;
		if (maxLen == 0) max = Math.max(max, map[r][c]);
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc)) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
						if (maxLen < l) {
							maxLen = l;
							max = map[r][c] + map[nr][nc];
						} else if (maxLen == l) {
							max = Math.max(max, map[r][c] + map[nr][nc]);
						}
					}
				}
				qLen--;
			}
			l++;
		}
	}
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M && !visited[r][c] && map[r][c] > 0;
	}

}
