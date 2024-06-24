

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1303_전쟁_전투 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			String S = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = S.charAt(j) == 'B' ? 1 : 0;
			}
		}
		// 각 지점에 대해 BFS를 수행하여 뭉쳐있는 병사의 수를 구하고, 이를 바탕으로 위력을 계산한다.
		visited = new boolean[M][N]; // 방문 배열
		int[] sum = new int[2]; // sum[0]은 아군의 위력의 합, sum[1]은 적군의 위력의 합
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) sum[map[i][j]] += bfs(i, j, map[i][j]);
			}
		}
		System.out.println(sum[0] + " " + sum[1]);
	}
	// BFS를 활용해 (r, c)를 포함하는 k번째 군의 뭉쳐있는 집단의 위력을 계산하는 함수
	// k = 0인 경우 아군, k = 1인 경우 적군을 의미한다.
	private static int bfs(int r, int c, int k) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == k) {
					visited[nr][nc] = true;
					cnt++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		return cnt*cnt;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<M && 0<=c && c<N;
	}
}
