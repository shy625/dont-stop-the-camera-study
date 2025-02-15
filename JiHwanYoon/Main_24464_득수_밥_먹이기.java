

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_24464_득수_밥_먹이기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int CONST = 1_000_000_007;
		// dynamic programming 활용
		// dp[i][0]은 i번째날 굶었을 때 가능한 경우의 수
		// dp[i][j]는 i번째날 j번 식당에 가는 경우의 수(1<=j<=4)
		long[][] dp = new long[N+1][5];
		for (int i = 0; i <= 4; i++) {			
			dp[1][i] = 1;
		}
		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4])%CONST;
			dp[i][1] = (dp[i-1][0] + dp[i-1][3] + dp[i-1][4])%CONST;
			dp[i][2] = (dp[i-1][0] + dp[i-1][4])%CONST;
			dp[i][3] = (dp[i-1][0] + dp[i-1][1])%CONST;
			dp[i][4] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%CONST;
		}
		long res = 0;
		for (int i = 0; i <= 4; i++) {
			res = (res + dp[N][i])%CONST;
		}
		System.out.println(res);
	}

}
