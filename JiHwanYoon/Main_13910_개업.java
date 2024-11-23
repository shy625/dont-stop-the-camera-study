

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13910_개업 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int MAX = 10000; // 웍 크기의 최댓값
		int[] woks = new int[MAX+1]; // woks[i]는 i그릇의 짜장면을 만들 수 있는 웍의 개수를 의미한다.
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			woks[Integer.parseInt(st.nextToken())]++;
		}
		// dynamic programming을 활용
		// dp[i]는 i그릇의 짜장면을 만들기 위해 필요한 최소 요리 횟수를 의미한다.
		int[] dp = new int[N+1];
		// 초기화
		Arrays.fill(dp, MAX);
		dp[0] = 0;
		woks[0] = 1;
		// dp[i]는 순차적으로 구한다.
		for (int i = 1; i <= N; i++) {
			// 이전에 구한 dp[j]와 dp[i-j]를 활용해 계산
			for (int j = 0; j <= i/2; j++) {
				// i/2그릇의 짜장면을 만들 수 있는 웍 2개를 이용하는 경우
				if (2*j == i && woks[j] >= 2) { 
					dp[i] = 1;
				// j그릇의 짜장면을 만들 수 있는 웍과, i-j그릇의 짜장면을 만들 수 있는 웍을 이용하는 경우(단, j와 i-j는 다름)
				} else if (2*j != i && woks[j] > 0 && woks[i-j] > 0) { 
					dp[i] = 1;
				// 위 두 경우에 해당하지 않으면 이전에 구했던 해를 활용해 dp[i]를 계산
				} else if (dp[j] != MAX && dp[i-j] != MAX) {
					dp[i] = Math.min(dp[i], dp[j] + dp[i-j]);
				}
			}
		}
		System.out.println(dp[N] >= MAX ? -1 : dp[N]);
	}

}
