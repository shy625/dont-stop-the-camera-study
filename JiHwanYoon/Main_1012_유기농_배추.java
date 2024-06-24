

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농_배추 {
	static int M, N, cnt;
	static boolean[][] map, visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new boolean[N][M]; // 배추가 심어진 위치를 나타내는 배열
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = true;
			}
			// 배추가 심어진 모든 지점에 대해 BFS를 수행해, 배추가 심어진 영역의 개수를 구한다.
			visited = new boolean[N][M]; // 방문 배열
			cnt = 0; // 배추가 심어진 영역의 개수
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && map[r][c]) {
						bfs(r, c);
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// BFS를 활용해 배추가 심어진 영역을 구하는 함수 
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc)) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	// 경계 확인 및 배추가 심어진 영역 내 지점 방문 여부 확인 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M && !visited[r][c] && map[r][c];
	}
}
