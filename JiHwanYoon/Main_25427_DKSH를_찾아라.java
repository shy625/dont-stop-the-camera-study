

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_25427_DKSH를_찾아라 {
	static char[] cs = {' ', 'D', 'K', 'S', 'H'};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		// dynamic programming을 활용
		// dp[i][j]는 i번째 문자까지의 부분 문자열에서 DKSH의 j번째 문자까지 선택할 수 있는 경우의 수
		long[][] dp = new long[N+1][5];
		// 초기화
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; j <= 4; j++) {
			for (int i = 1; i <= N; i++) {
				dp[i][j] += dp[i-1][j];
				if (s.charAt(i-1) == cs[j]) {
					dp[i][j] += dp[i-1][j-1];
				}
			}
		}
		System.out.println(dp[N][4]);
	}

}
