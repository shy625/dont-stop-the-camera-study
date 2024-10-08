import java.util.Scanner;

public class BOJ_S1_11057_오르막수 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		// dp[i][j]는 길이가 N인 수의 i번째 숫자가 j일 때의 경우의 수이다.
		int [][] dp = new int[N+1][10];
		
		// 길이가 0인 경우의 수는 1개밖에 없다.
		dp[0][0] = 1;
		
		// 길이가 1일때부터 N일 떄 까지 경우의 수를 확인해본다.
		for (int i = 1; i <= N; i++) {
			// 숫자 0부터 9까지 경우의 수를 각각 확인해본다.
			for (int j = 0; j <= 9; j++) {
				// i번째 숫자가 j일 때, i-1번째 숫자로 올 수 있는 숫자는 0~j번째이다.
				// 해당 경우의 수를 모두 더해준다.
				for (int l = 0; l <= j; l++) {
					dp[i][j] = (dp[i][j] + (dp[i-1][l])) % 10007;
				}
			}
		}
		
		int ans = 0;
		// 마지막 자리가 0일 때부터 9일 때까지 경우의 수를 모두 더한다.
		for (int i = 0; i <= 9; i++) {
			ans = (ans + dp[N][i]) % 10007;
		}
		
		System.out.println(ans);

	}

}
