import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2096_내려가기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 숫자들을 저장할 배열
		int [][] nums = new int[N+1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			nums[i][0] = Integer.parseInt(st.nextToken());
			nums[i][1] = Integer.parseInt(st.nextToken());
			nums[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// dp를 사용한다. 1번째 칸은 줄 수, 2번째 칸은 숫자의 위치, 3번째 칸은 최솟값과 최댓값을 각각 저장한다.
		int [][][] dp = new int[N+1][3][2];
		
		for (int i = 1; i <= N; i++) {
			// 최솟값을 먼저 갱신한다. i번째줄 0번 칸에는 i-1번째줄 0, 1번째 칸에서만 접근할 수 있다.
			// i번째줄 1번 칸에는 i-1번째줄 모든 칸에서 접근할 수 있다.
			// i번째줄 2번 칸에는 i-1번째줄 1, 2번째 칸에서만 접근할 수 있다.
			dp[i][0][0] = nums[i][0] + Math.min(dp[i-1][0][0], dp[i-1][1][0]);
			dp[i][1][0] = nums[i][1] + Math.min(dp[i-1][0][0], Math.min(dp[i-1][1][0], dp[i-1][2][0]));
			dp[i][2][0] = nums[i][2] + Math.min(dp[i-1][1][0], dp[i-1][2][0]);
			
			// 최댓값을 갱신한다.
			dp[i][0][1] = nums[i][0] + Math.max(dp[i-1][0][1], dp[i-1][1][1]);
			dp[i][1][1] = nums[i][1] + Math.max(dp[i-1][0][1], Math.max(dp[i-1][1][1], dp[i-1][2][1]));
			dp[i][2][1] = nums[i][2] + Math.max(dp[i-1][1][1], dp[i-1][2][1]);
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < 3; i++) {
			min = Math.min(min, dp[N][i][0]);
			max = Math.max(max, dp[N][i][1]);
		}
		
		System.out.println(max+" "+min);
		
	}

}
