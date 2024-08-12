

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_29704_벼락치기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] problems = new int[N+1][2];
		int sum = 0; // 모든 벌금의 총합
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			problems[i][0] = Integer.parseInt(st.nextToken());
			problems[i][1] = Integer.parseInt(st.nextToken());
			sum += problems[i][1];
		}
		// 베낭 문제와 동일한 알고리즘으로 해결한다.
		// dp[i][j]는 i번째 과제까지만 고려했을 때 T일의 기한이 주어질 경우 내야 하는 벌금의 최솟값
		int[][] dp = new int[N+1][T+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], sum);
		}
		for (int i = 1; i <= N; i++) {
			for (int t = 1; t <= T; t++) {
				dp[i][t] = Math.min(dp[i-1][t], dp[i][t-1]);
				if (t >= problems[i][0]) dp[i][t] = Math.min(dp[i][t], dp[i-1][t-problems[i][0]] - problems[i][1]); 
			}
		}
		System.out.println(dp[N][T]);
	}

}
