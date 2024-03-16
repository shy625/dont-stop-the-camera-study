

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2411_아이템_먹기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+1];
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = -1;
		}
		// dynamic programming을 활용
		// dp[i][j][k]는 (i, j)까지 아이템을 k개 먹는 경우의 수를 의미
		int[][][] dp = new int[N+1][M+1][A+1];
		// 처음 지점에 장애물이 있는 경우
		if (map[1][1] == -1) {
			System.out.println(0);
			return;
		}
		dp[1][1][map[1][1]] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 장애물이 있는 경우
				if (map[i][j] == -1) continue;
				for (int k = 0; k <= A; k++) {
					if (k == A) dp[i][j][k] += dp[i-1][j][k] + dp[i][j-1][k];
					else dp[i][j][k+map[i][j]] += dp[i-1][j][k] + dp[i][j-1][k];
				}
			}
		}
		// 아이템을 모두 먹은 경우만 센다.
		System.out.println(dp[N][M][A]);
	}

}
