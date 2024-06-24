

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
	
	static int[][] map;
	static int max = -1;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 칸에 대해 dfs를 수행해서 만들 수 있는 테트로미노와
		// 凸 모양의 블록을 각각 조사해 최댓값을 찾는다.
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				dfs(i, j, 0, 0);
				check(i, j);
			}
		}
		System.out.println(max);
	}
	// dfs를 깊이가 4가 될 때까지 수행해 가능한 테트로미노를 모두 고려할 수 있도록 한다.
	public static void dfs(int x, int y, int n, int sum) {
		if (n == 4) {
			max = Math.max(max, sum);
			return;
		}
		visited[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (1 <= nx && nx < N+1 && 1 <= ny && ny < M+1 && !visited[nx][ny]) {
				dfs(nx, ny, n+1, sum+map[x][y]);
			}
		}
		visited[x][y] = false;
	}
	// 凸 모양의 블록은 dfs로 불가능하므로 따로 체크한다.
	public static void check(int x, int y) {
		int sum = map[x][y];
		for (int d = 0; d < 4; d++) {
			sum += map[x+dx[d]][y+dy[d]];
		}
		for (int d = 0; d < 4; d++) {
			max = Math.max(max, sum - map[x+dx[d]][y+dy[d]]);
		}
	}
}

