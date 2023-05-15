import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3067_Coins {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int [] arr = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			
			int [] dp = new int[M+1];
			dp[0] = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = arr[i]; j <= M; j++) {
					dp[j] += dp[j - arr[i]];
				}
			}
			
			System.out.println(dp[M]);
			
		}
	}

}
