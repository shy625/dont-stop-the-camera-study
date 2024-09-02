

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_30702_국기_색칠하기 {
	static int N, M;
	static char[][] flag1;
	static char[][] flag2;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		flag1 = new char[N][M];
		flag2 = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				flag1[i][j] = s.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				flag2[i][j] = s.charAt(j);
			}
		}
		// 1번째 국기의 각 지점에 대해 BFS 수행
		// BFS 수행하면서 2번째 국기와 영역이 동일한지 확인
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) bfs(i, j);
			}
		}
		// 1번째 국기와 2번째 국기의 모든 영역이 동일한 경우
		System.out.println("YES");
	}
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		char color1 = flag1[r][c]; // (r, c)에서 1번째 국기의 색
		char color2 = flag2[r][c]; // (r, c)에서 2번째 국기의 색
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc, color1)) {
					// 1번째 국기와 2번째 국기의 영역이 다른 경우
					if (flag2[nr][nc] != color2) {
						System.out.println("NO");
						System.exit(0);
					}
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	// 1번째 국기 내 같은 영역 탐색을 위한 지점 판별 함수
	private static boolean check(int r, int c, int color) {
		return 0<=r && r<N && 0<=c && c<M && !visited[r][c] && flag1[r][c] == color;
	}
}
