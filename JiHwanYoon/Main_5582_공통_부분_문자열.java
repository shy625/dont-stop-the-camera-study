

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5582_공통_부분_문자열 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		int N = S.length();
		int M = T.length();
		// dynamic programming 활용
		// dp[i][j]는 S의 i번째 문자, T의 j번쨰 문자를 포함하는 가장 긴 공통 부분 문자열의 길이
		int[][] dp = new int[N][M];
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (S.charAt(i) == T.charAt(j)) {
					if (i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]) + 1;
					}
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		System.out.println(max);
	}

}
