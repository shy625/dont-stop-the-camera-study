import java.util.Scanner;

public class BOJ_G5_2225_합분해 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		int K = scann.nextInt();
		
		int [][] dp = new int[N+1][K+1];
		
		// 숫자 1개로 만들 수 있는 경우의 수는 1개다.
		for (int i = 0; i <= N; i++) {
			dp[i][1] = 1;
		}
		
		// N이 1일 때 경우의 수는 K개다.
		for (int i = 0; i <= K; i++) {
			dp[1][i] = i;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				// dp[N][K]는 dp[N-1][K]와 dp[N][K-1]을 더한 값이다.
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
			}
		}
		
		System.out.println(dp[N][K]);

	}

}
