

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2600_구슬게임 {
	static int[][] dp;
	static int[] K;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = new int[3];
		for (int i = 0; i < 3; i++) {
			K[i] = Integer.parseInt(st.nextToken());
		}
		StringBuilder sb = new StringBuilder();
		// dynamic programming을 활용
		// dp[i][j]는 (i, j)개의 구슬이 있을 때 A가 이기면 1, B가 이기면 0의 값을 가진다.
		dp = new int[501][501];
		for (int i = 0; i <= 500; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			int k1 = Integer.parseInt(st.nextToken());
			int k2 = Integer.parseInt(st.nextToken());
			sb.append(dfs(k1, k2) == 1 ? "A" : "B").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 탑다운 방식을 통해 게임 결과를 예측하는 함수
	private static int dfs(int k1, int k2) {
		// 이미 구한 경우
		if (dp[k1][k2] >= 0) return dp[k1][k2];
		// k1개의 구슬에서 구슬을 뺀 결과, B가 지는 경우가 있다면 A는 이긴다.
		for (int i = 0; i < 3; i++) {
			if (k1 >= K[i] && dfs(k1 - K[i], k2) == 0) {
				dp[k1][k2] = 1;
				return dp[k1][k2];
			}
		}
		// k2개의 구슬에서 구슬을 뺀 결과, B가 지는 경우가 있다면 A는 이긴다.
		for (int i = 0; i < 3; i++) {
			if (k2 >= K[i] && dfs(k1, k2 - K[i]) == 0) {
				dp[k1][k2] = 1;
				return dp[k1][k2];
			}
		}
		// 구슬을 아무리 빼도 B가 이기면, A는 진다.
		dp[k1][k2] = 0;
		return dp[k1][k2];
	}

}
