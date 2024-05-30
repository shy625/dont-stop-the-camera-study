import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1390_동물원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// [i번째 칸][0: 둘다 비어둔 것, 1: 왼쪽만 넣은 것, 2: 오른쪽만 넣은 것]
		int [][] dp = new int[N+1][3];
		
		// 첫 번째 칸에는 둘다 비어있는 경우, 왼쪽만 넣은 경우, 오른쪽만 넣은 경우 각각 1개씩 가능하다.
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for (int i = 2; i <= N; i++) {
			// 현재 칸에 비우는 경우는, 이전 칸에 어떻게 넣었든 가능하다.
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			// 왼쪽 칸에 넣는 경우는, 이전 칸을 비우거나, 오른쪽 칸에 넣었을 때만 가능하다.
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
			// 오른쪽 칸에 넣는 경우는, 이전 칸을 비우거나, 왼쪽 칸에 넣었을 때만 가능하다.
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
		}
		
		int ans = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
		
		System.out.println(ans);
		
	}

}
