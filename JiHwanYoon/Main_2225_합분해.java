

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2225_합분해 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// dynamic programming을 활용
		// dp[i][j]는 j를 i개의 숫자의 합으로 표현할 수 있는 경우의 수
		int[][] dp = new int[N+1][K+1];
		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1;
		}
		for (int i = 2; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = 0; k <= j; k++) {
					dp[j][i] = (dp[j][i]+dp[j-k][i-1])%1_000_000_000;
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}