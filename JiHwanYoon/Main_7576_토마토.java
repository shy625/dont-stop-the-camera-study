

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_7576_토마토 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int M = Integer.parseInt(s[0]);
		int N = Integer.parseInt(s[1]);
		int[][] map = new int[N][M];
		// BFS 사전 준비
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				// 익은 토마토의 위치를 미리 파악해 둔다.
				if (map[i][j] == 1) {
					q.offer(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		}
		// 지금까지 지난 날짜
		int day = 0;
		// BFS
		while (!q.isEmpty()) {
			int[] u = q.poll();
			// BFS 특성상 큐에는 날짜가 이른 순서대로 익은 토마토가 들어오게 된다. 
			// 그래서 만약 새 날짜의 익은 토마토가 큐에서 나왔다는 것은 이전 날짜의 익은 토마토는 이미 탐색을 마쳤다는 의미라서 날짜를 바꿔준다. 
			if (day != u[2]) day = u[2];
			// 익은 토마토 주변 순회
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					map[nr][nc] = 1;
					q.offer(new int[] {nr, nc, u[2]+1});
				}
			}
		}
		// 상자 내 토마토가 전부 익었는지 확인
		boolean allRipe = true;
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					allRipe = false;
					break outer;
				}
			}
		}
		// 상자 내 토마토가 전부 익었므면 day를, 하나라도 안 익었으면 -1 출력
		System.out.println(allRipe ? day : -1);
		

	}

}
