import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_11054_가장긴바이토닉부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[N][0]은 N번째 수까지 가장 긴 증가하는 부분 수열
		// dp[N][1]은 N번째 수 이후로 가장 긴 감소하는 부분 수열
		int [][] dp = new int[N+1][2];
		
		for (int i = 1; i < N; i++) {
			// j의 범위는 0~i로, i의 앞쪽만 탐색한다.
			for (int j = 0; j < i; j++) {
				// i번째 수보다 작은 j번째 수를 찾아
				// i번째 수의 dp 값과 j번째 수 dp 값 + 1을 비교하며 갱신해준다.
				if(arr[j] < arr[i]) {
					dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
				}
			}
		}
		
		for (int i = N-2; i >= 0; i--) {
			// j의 범위는 i~N으로, i의 뒤쪽만 탐색한다.
			for (int j = i; j < N; j++) {
				// i번째 수보다 작은 j번쨰 수를 찾아
				// i번째 수의 dp 값과 j번째 수 dp 값 + 1을 비교하며 갱신해준다.
				if(arr[j] < arr[i]) {
					dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
				}
			}
		}
		
		int ans = 0;
		
		// i번째 수에 대해, 가장 긴 증가하는 부분수열과 가장 긴 감소하는 부분수열의 합이 가장 큰 값이 답이 된다.
		// 자기 자신도 포함해야 하므로 +1을 해준다.
		for (int i = 0; i <= N; i++) {
			ans = Math.max(ans, dp[i][0]+dp[i][1]+1);
		}
		
		System.out.println(ans);
		
	}

}
