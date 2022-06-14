

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10026_적록색약 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 적록색약이 아닌 경우
		visited = new boolean[N][N];
		int cnt1 = 0; // 적록색약이 아닌 사람이 보는 구역의 개수
		int cnt2 = 0; // 적록색약인 사람이 보는 구역의 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 새로운 구역을 발견한 경우
				if (!visited[i][j]) {
					bfs(i, j, false);
					cnt1++;
				}
				
			}
		}
		// 적록색약인 경우
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, true);
					cnt2++;
				}
			}
		}
		System.out.println(cnt1+" "+cnt2);
	}
	// BFS를 통해 각 구역을 탐색한다.
	private static void bfs(int i, int j, boolean blind) {
		char c = map[i][j]; // 현재 발견한 구역의 색
		visited[i][j] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc]) {
					// 적록색약이 아니면 다른 색을 발견한 경우 다른 구역으로 간주한다.
					if (!blind && map[nr][nc] == c) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					// 적록색약인 경우 G와 R을 같은 색으로 보기에 다른 구역으로 간주하는 기준을 완화한다.
					} else if (blind && ((c == 'B' && map[nr][nc] == c) || ((c == 'G' || c == 'R') && (map[nr][nc] == 'G' || map[nr][nc] == 'R')))) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		
		
	}

}
