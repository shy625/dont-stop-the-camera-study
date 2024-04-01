

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2806_DNA_발견 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		// dynamic programming을 이용
		// dp[i][0]은 i번째 문자까지 모두 A로 만들기 위해 필요한 연산의 횟수
		// dp[i][1]은 i번째 문자까지 모두 B로 만들기 위해 필요한 연산의 횟수
		int[][] dp = new int[N][2];
		for (int i = 1; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		// 초기화
		dp[0][0] = s.charAt(0) == 'A' ? 0 : 1;
		dp[0][1] = s.charAt(0) == 'A' ? 1 : 0;			
		for (int i = 1; i < N; i++) {
			// i번째 문자가 A인 경우
			if (s.charAt(i) == 'A') {
				// (i-1)번째 문자까지 모두 A면 연산을 수행할 필요가 없고, 모두 B면 (i-1)번째 문자까지 뒤집는 연산이 한 번 필요
				dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1] + 1);
				// (i-1)번째 문자까지 모두 A면 (i-1)번째 문자까지 뒤집는 연산이 한 번 필요하고,
				// (i-1)번째 문자까지 모두 B면 i번째 문자를 뒤집는 연산이 한 번 필요
				dp[i][1] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1);
			// i번째 문자가 B인 경우
			} else {
				// (i-1)번째 문자까지 모두 A면 i번째 문자를 뒤집는 연산이 한 번 필요하고,
				// (i-1)번째 문자까지 모두 B면 (i-1)번째 문자까지 뒤집는 연산이 한 번 필요
				dp[i][0] = Math.min(dp[i-1][0] + 1, dp[i-1][1] + 1);
				// (i-1)번째 문자까지 모두 A면 (i-1)번째 문자까지 뒤집는 연산이 한 번 필요하고, 모두 B면 연산을 수행할 필요가 없음
				dp[i][1] = Math.min(dp[i-1][0] + 1, dp[i-1][1]);
			}
		}
		System.out.println(dp[N-1][0]);
	}
}
