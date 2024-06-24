

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_28017_게임을_클리어하자 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] times = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dynamic programming을 활용
		// dp[i][j]는 i번째 플레이에서 j번째 무기를 사용할 때 드는 시간의 최솟값
		int[][] dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0] = Arrays.copyOf(times[0], M);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					if (j == k) continue; // 바로 이전과 같은 무기 사용 불가
					dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + times[i][j]);
				}
			}
		}
		int min = Integer.MAX_VALUE; // N번의 플레이에 드는 시간의 최솟값
		for (int j = 0; j < M; j++) {
			min = Math.min(min, dp[N-1][j]);
		}
		System.out.println(min);
	}

}
