import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_11722_가장긴감소하는부분수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 숫자들을 저장할 배열
		int [] arr = new int[N];
		// i번째 숫자까지 가장 긴 감소하는 부분 수열의 길이를 저장할 배열
		int [] dp = new int[N];
		int ans = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 자기 자신은 1번 연속이므로 dp 배열을 1로 초기화해준다.
		Arrays.fill(dp, 1);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// i번째 수가 j번째 수보다 작다면, j번째까지 최장 부분 수열의 길이+1로 dp[i]를 갱신해본다.
				if(arr[j] > arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			// 자신의 앞에있는 수를 모두 확인했다면, ans를 갱신한다.
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		
	}

}
