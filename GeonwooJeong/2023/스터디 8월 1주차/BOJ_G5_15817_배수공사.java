import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_15817_배수공사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		// 길이 x를 만들 수 있는 총 경우의 수를 저장할 배열
		int [] dp = new int[x+1];
		// 0을 만들 수 있는 경우의 수는 1이다.
		dp[0] = 1;
		
		for (int i = 0; i < N; i++) {
			// 현재의 파이프를 사용했을 때의 경우의 수를 저장할 임시 배열 tmp
			int [] tmp = Arrays.copyOf(dp, x+1);
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			// 1부터 x까지의 모든 경우의 수를 구한다.
			for (int j = 1; j <= x; j++) {
				int sum = 0;
				for (int k = 0; k < C; k++) {
					// 파이프가 총 C개 있을 수 있으므로, 각각의 경우의 수를 구한다.
					sum += L;
					if(j - sum < 0) break;
					// 길이 'j'는 길이 'j-sum'에서 'sum'을 더하면 구할 수 있으므로, dp[j-sum]의 경우의 수를 더한다.
					tmp[j] += dp[j - sum];
				}
			}
			
			// 임시 배열을 dp 배열로 갱신해준다.
			dp = Arrays.copyOf(tmp, x+1);
			
		}
		
		System.out.println(dp[x]);

	}

}
