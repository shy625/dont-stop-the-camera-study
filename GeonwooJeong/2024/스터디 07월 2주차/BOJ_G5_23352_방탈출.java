import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_23352_방탈출 {
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static int [][] map;
	static int N, M, max, ans;

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
		
		// 0이 적힌 방이 아니라면 모두 탐색해본다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					bfs(i, j);
				}
			}
		}

		System.out.println(ans);
		
	}

	private static void bfs(int r, int c) {
		boolean [][] v = new boolean[N][M];
		v[r][c] = true;
		
		Queue<int []> q = new ArrayDeque<>();
		// 경로 중 가장 긴 경로를 찾아야 하므로 경로의 길이도 같이 저장한다.
		q.add(new int[] {r, c, 1});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			int len = cur[2];
			
			// 현재 경로가 가장 긴 경로일 경우, 숫자의 합을 갱신한다.
			if(len > max) {
				max = len;
				ans = map[r][c] + map[curR][curC];
			// 현재 경로와 가장 긴 경로의 길이가 같을 경우, 숫자의 합이 더 큰 값이 비밀번호가 된다.
			} else if(len == max) {
				ans = Math.max(ans, map[r][c] + map[curR][curC]);
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				if(!check(nr, nc)) continue;
				
				if(v[nr][nc] || map[nr][nc] == 0) continue;
				
				v[nr][nc] = true;
				q.add(new int[] {nr, nc, len+1});
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
