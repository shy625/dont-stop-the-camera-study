import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_5546_파스타 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 앞은 일수, 뒤의 0~2은 123을 한 번만 연속해서 쓴 것, 3~5는 123을 두 번 연속해서 쓴 것 
		int [][] dp = new int[N+1][6];
		int [][] days = new int[K][2];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			days[i][0] = Integer.parseInt(st.nextToken());
			days[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(days, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		int idx = 0;
		if(days[idx][0] == 1) {
			int B = days[idx][1];
			dp[1][B-1] = 1;
			idx++;
		} else {
			dp[1][0] = 1;
			dp[1][1] = 1;
			dp[1][2] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			if(days[idx][0] == i) {
				int B = days[idx][1]-1;
				if(idx != K-1) idx++;
				switch (B) {
				case 0:
					dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][4] + dp[i-1][5]) % 10000;
					dp[i][3] = dp[i-1][0];
					
					break;
				case 1:
					dp[i][1] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3] + dp[i-1][5]) % 10000;
					dp[i][4] = dp[i-1][1];
					
					break;
				case 2:
					dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3] + dp[i-1][4]) % 10000;
					dp[i][5] = dp[i-1][2];
					
					break;
				default:
					break;
				}
			} else {
				dp[i][0] = (dp[i-1][1] + dp[i-1][2] + dp[i-1][4] + dp[i-1][5]) % 10000;
				dp[i][1] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3] + dp[i-1][5]) % 10000;
				dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3] + dp[i-1][4]) % 10000;
				dp[i][3] = dp[i-1][0];
				dp[i][4] = dp[i-1][1];
				dp[i][5] = dp[i-1][2];
			}
		}
		
		int ans = 0;
		
		for (int i = 0; i < 6; i++) {
			ans = (ans + dp[N][i]) % 10000;
		}
		
		System.out.println(ans);
		
	}

}
