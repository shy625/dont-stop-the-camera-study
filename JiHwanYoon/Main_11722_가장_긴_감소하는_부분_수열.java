

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11722_가장_긴_감소하는_부분_수열 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 활용
		// dp[i]는 i번째 수를 포함하는 가장 긴 감소하는 부분 수열의 길이
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int max = 0; // 가장 긴 감소하는 부분 수열의 길이
		for (int i = 0; i < N; i++) {
			int n = arr[i];
			for (int j = 0; j < i; j++) {
				if (arr[j] > n) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
