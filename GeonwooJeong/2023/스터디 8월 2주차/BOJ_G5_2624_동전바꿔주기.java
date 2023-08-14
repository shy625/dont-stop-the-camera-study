import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2624_동전바꿔주기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		// T원을 만들 경우의 수를 저장할 배열
		int [] dp = new int[T+1];
		// 0원을 만들 수 있는 경우의 수는 1개이다.
		dp[0] = 1;
		
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			// T원부터 1원까지의 모든 경우의 수를 구한다.
			for (int j = T; j > 0 ; j--) {
				int sum = 0;
				
				// p원짜리 동전이 0~n개 있을 때를 모두 구해준다.
				for (int l = 0; l < n; l++) {
					sum += p;
					if(j - sum < 0) break;
					// 'j'원은 'j-sum' 원에서 'sum'을 더하면 구할 수 있으므로 'j-sum'일 떄의 경우의 수를 더해준다.
					dp[j] += dp[j - sum];
				}
			}
			
		}
		
		System.out.println(dp[T]);
		
	}

}
