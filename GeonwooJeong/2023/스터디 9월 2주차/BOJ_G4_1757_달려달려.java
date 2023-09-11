import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1757_달려달려 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// dp 배열의 앞은 시간, 뒤는 지침 지수
		int [][] dp = new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			int d = Integer.parseInt(br.readLine());
			
			// i번째에서 쉬는 경우 -> i-1번째의 거리를 그대로 가져온다.
			dp[i][0] = dp[i-1][0];
			
			for (int j = 1; j <= M; j++) {
				// i번째에서 달리는 경우 -> i-1번째 j-1의 지침 지수를 가지고 d의 거리를 추가로 달린다.
				dp[i][j] = dp[i-1][j-1] + d;
			}
			
			// 한 번 쉬면 지침지수가 0이 될 때까지 쉬어야 하므로
			// 해당 조건을 가지고 dp 배열을 업데이트해준다.
			// j는 최대 M까지 가질 수 있지만, 시간이 충분히 지나야 하므로 j<i라는 조건이 추가로 필요하다.
			for (int j = 1; j <= M && j < i; j++) {
				dp[i][0] = Math.max(dp[i][0], dp[i-j][j]);
			}
		}
		
		System.out.println(dp[N][0]);
		
	}

}
