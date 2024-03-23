

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17953_디저트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] satis = new int[N+1][M];
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				satis[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dynamic programming을 활용
		// dp[i][j]는 i번째 날 j번째 디저트를 먹었을 때 만족도의 합의 최댓값
		int[][] dp = new int[N+1][M];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					// 첫 번째 날의 경우 이전에 먹은 디저트가 없으므로 만족도 감소 여부를 고려하지 않는다.
					if (i != 1 && j == k) dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + satis[i][j]/2);
					else dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + satis[i][j]);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < M; i++) {
			max = Math.max(max, dp[N][i]);
		}
		System.out.println(max);
	}
}
