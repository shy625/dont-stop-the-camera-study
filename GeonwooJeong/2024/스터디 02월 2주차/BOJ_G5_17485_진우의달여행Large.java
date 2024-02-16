import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17485_진우의달여행Large {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int [][] fuel = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				fuel[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 왼아래, 아래, 오아래
		int [][][] dp = new int[N+1][M][3];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int nowFuel = fuel[i-1][j];
				if(j == 0) {
					dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + nowFuel;
					dp[i][j][1] = dp[i-1][j][0] + nowFuel;
					dp[i][j][2] = Integer.MAX_VALUE;
				} else if(j == M-1) {
					dp[i][j][0] = Integer.MAX_VALUE;
					dp[i][j][1] = dp[i-1][j][2] + nowFuel;
					dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + nowFuel;
				} else {
					dp[i][j][0] = Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + nowFuel;
					dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + nowFuel;
					dp[i][j][2] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + nowFuel;
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 3; j++) {
				ans = Math.min(ans, dp[N][i][j]);
			}
		}
		
		System.out.println(ans);

	}

}
