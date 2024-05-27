

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14550_마리오_파티 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0) break; // 입력 끝
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			// 0번째는 시작 칸, N+1번째는 별이 있는 칸
			int[] coins = new int[N+2];
			int idx = 1;
			while (idx <= N) {
				st = new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					coins[idx++] = Integer.parseInt(st.nextToken());
				}
			}
			// dynamic programming을 활용
			// dp[t][n]은 t번째 턴에서 n번째 칸에 도달했을 때 최대 수익
			int[][] dp = new int[T+1][N+2];
			// 초기화 시, 코인의 값이 음수가 될 수 있어 음수 중 최대한 작은 값으로 초기화
			for (int i = 0; i <= T; i++) {				
				Arrays.fill(dp[i], Integer.MIN_VALUE/10);
			}
			dp[0][0] = 0;
			for (int t = 1; t <= T; t++) {
				for (int i = 0; i <= N; i++) {
					for (int s = 1; s <= S; s++) {
						dp[t][Math.min(i+s, N+1)] = Math.max(dp[t][Math.min(i+s, N+1)], dp[t-1][i] + coins[Math.min(i+s, N+1)]);
					}
				}
			}
			// 수익의 최댓값 계산
			int max = Integer.MIN_VALUE;
			for (int t = 1; t <= T; t++) {
				max = Math.max(max, dp[t][N+1]);
			}
			sb.append(max).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
