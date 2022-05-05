

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11052_카드_구매하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] prices = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}
		// 각 카드 개수마다 지불해야 하는 금액의 최댓값을 구하기 위해 dynamic programming을 활용
		// 초기값으로 각 카드 개수마다 그 카드 개수만큼 들어있는 카드팩을 사는 경우로 설정
		int[] dp = Arrays.copyOf(prices, N+1);
		// 각 카드 개수마다 지불해야 하는 금액의 최댓값을 구한다. 이를 위해 이전까지 구해놓은 최댓값을 이용한다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j]+prices[j]);
			}
		}
		System.out.println(dp[N]);
	}

}
