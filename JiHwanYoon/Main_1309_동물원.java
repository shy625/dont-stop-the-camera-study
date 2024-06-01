

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1309_동물원 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 활용
		// dp[0][i]는 i번째 가로줄에 사자를 한 마리도 넣지 않는 경우의 수
		// dp[1][i], dp[2][i]는 i번째 가로줄에 사자를 1번 우리, 2번 우리에 넣는 경우의 수
		int[][] dp = new int[3][N];
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[2][0] = 1;
		int CONST = 9901;
		for (int i = 1; i < N; i++) {
			dp[0][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1])%CONST;
			dp[1][i] = (dp[0][i-1] + dp[2][i-1])%CONST;
			dp[2][i] = (dp[0][i-1] + dp[1][i-1])%CONST;
		}
		System.out.println((dp[0][N-1] + dp[1][N-1] + dp[2][N-1])%CONST);
	}

}
