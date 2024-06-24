

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1915_가장_큰_정사각형 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		// dynamic programming 활용
		// dp[i][j]는 (i-1, j-1)을 오른쪽 아래 꼭짓점으로 하는 가장 큰 정사각형의 길이
		int[][] dp = new int[n+1][m+1];
		int max = 0; // 가장 큰 정사각형의 길이
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i-1][j-1] == 1) dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max*max);
	}

}
