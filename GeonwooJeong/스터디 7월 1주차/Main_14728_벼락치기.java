import java.util.Scanner;

public class Main_14728_벼락치기 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		int T = scann.nextInt();
		
		int [][] chapter = new int[N+1][2]; // 시간, 점수
		int [][] dp = new int[N+1][T+1];
		
		for (int i = 1; i <= N; i++) {
			int K = scann.nextInt();
			int S = scann.nextInt();
			chapter[i][0] = K;
			chapter[i][1] = S;
		}
		
		for (int i = 1; i <= N; i++) { // 단원
			for (int j = 0; j <= T; j++) { // 시간
				if(chapter[i][0] <= j) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-chapter[i][0]] + chapter[i][1]);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N][T]);

	}

}
