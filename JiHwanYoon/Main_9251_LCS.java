

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251_LCS {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int N = s1.length();
		int M = s2.length();
		// dynamic programming을 활용
		// dp[i][j]는 s1의 i번째 문자까지의 부분문자열와 s2의 j번째 문자까지의 부분문자열의 LCS의 길이
		int[][] dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// i번째 문자와 j번째 문자가 같은 경우
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				// 그렇지 않은 경우
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[N][M]);
	}

}
