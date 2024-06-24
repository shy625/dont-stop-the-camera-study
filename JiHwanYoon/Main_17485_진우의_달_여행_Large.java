

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17485_진우의_달_여행_Large {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dynamic programming을 활용
		// dp[i][j][k]는 마지막에 k번째 방향으로 (i, j)까지 왔을 때 드는 최소 연료의 양
		// 방향은 0번째가 왼쪽 아래, 1번째가 아래, 2번째가 오른쪽 아래 방향이다.
		int[][][] dp = new int[N+1][M+2][3];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= M+1; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE/10);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i+1][j-1][0] = Math.min(dp[i][j][1], dp[i][j][2]) + map[i+1][j-1]; // 왼쪽 아래
				dp[i+1][j][1] = Math.min(dp[i][j][0], dp[i][j][2]) + map[i+1][j]; // 아래
				dp[i+1][j+1][2] = Math.min(dp[i][j][0], dp[i][j][1]) + map[i+1][j+1]; // 오른쪽 아래
			}
		}
		int min = Integer.MAX_VALUE; // 달까지 가기 위해 드는 최소 연료의 양
		for (int j = 1; j <= M; j++) {
			for (int k = 0; k < 3; k++) {
				min = Math.min(min, dp[N][j][k]);
			}
		}
		System.out.println(min);
	}

}
