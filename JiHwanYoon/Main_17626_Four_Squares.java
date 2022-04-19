

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_17626_Four_Squares {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// dynamic programming을 활용해, 각 수마다 합이 그 수가 되는 제곱수들의 최소 개수를 저장한다.
		int[] dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		// 초기화
		dp[0] = 0;
		// 각 수 i에 대해 1부터 제곱근 i까지 i - j에 대한 dp 값을 고려해 그 중 제곱수의 개수가 
		// 최소가 되는 것을 선택한다.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= Math.sqrt(i); j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
			}
		}
		System.out.println(dp[n]);
	}

}

