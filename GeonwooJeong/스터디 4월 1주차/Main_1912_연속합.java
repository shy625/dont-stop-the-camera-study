import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int [] num = new int[N];
		int [] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp와 max의 초기값 설정
		dp[0] = num[0];
		int max = num[0];
		
		// 2번째 숫자부터 dp, max 갱신
		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(num[i], dp[i-1]+num[i]);
			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
		
	}

}
