

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3184_양 {
	static int R, C, cntSheep, cntWolf;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		// 각 영역을 BFS로 탐색한다.
		visited = new boolean[R][C];
		cntSheep = cntWolf = 0; // 남아있는 양과 늑대의 수
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && map[i][j] != '#') bfs(i, j);
			}
		}
		System.out.println(cntSheep + " " + cntWolf);
	}
	// BFS를 통해 (r, c)가 속한 영역에서 양 혹은 늑대의 수를 구하는 함수
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		int tempCntS = 0;
		int tempCntW = 0;		
		while (!q.isEmpty()) {
			int[] u = q.poll();
			if (map[u[0]][u[1]] == 'o') tempCntS++;
			else if (map[u[0]][u[1]] == 'v') tempCntW++;
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc)) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 양과 늑대 수를 비교해 양이 남아있는지, 늑대가 남아있는지를 확인
		if (tempCntS > tempCntW) cntSheep += tempCntS;
		else cntWolf += tempCntW;
	}
	// (r, c)가 탐색 가능한 영역인지 확인
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C && !visited[r][c] && map[r][c] != '#';
	}

}
