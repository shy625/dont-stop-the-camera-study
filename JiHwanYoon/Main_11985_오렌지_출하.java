

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11985_오렌지_출하 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] sizes = new long[N+1];
		for (int i = 1; i <= N; i++) {
			sizes[i] = Long.parseLong(br.readLine());
		}
		// dynamic programming을 활용
		// dp[i]는 i번째 오렌지까지만 포장할 때 포장 비용의 최솟값
		long[] dp = new long[N+1];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			long min = Long.MAX_VALUE;
			long max = -1;
			// 각 오렌지에 대해, 해당 오렌지부터 (i-j+1)번째 오렌지까지 하나의 박스로 포장했을 때 포장 비용을 모두 계산하고,
			// 그 중 최솟값을 구한다.
			for (int j = 1; j <= Math.min(i, M); j++) {
				min = Math.min(min, sizes[i-j+1]);
				max = Math.max(max, sizes[i-j+1]);
				dp[i] = Math.min(dp[i], K + j*(max - min) + dp[i-j]);
			}
		}
		System.out.println(dp[N]);
	}

}
