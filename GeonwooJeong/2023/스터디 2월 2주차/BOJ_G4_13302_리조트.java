import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_13302_리조트 {
	static int N, M;
	static boolean [] rest;
	static int [][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rest = new boolean[N+1];
		dp = new int[N+1][N+1];
		
		if(M > 0) {
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < M; i++) {
				int n = Integer.parseInt(st.nextToken());
				rest[n] = true;
			}
		}
		
		for (int i = 0; i < N+1; i++) {
			Arrays.fill(dp[i], -1);
		}	
		
		System.out.println(go(1, 0));

	}

	private static int go(int day, int coupon) {
		if(day > N) return 0;
		
		if(dp[day][coupon] != -1) return dp[day][coupon];
		else dp[day][coupon] = Integer.MAX_VALUE;
		
		if(rest[day]) {
			dp[day][coupon] = Math.min(dp[day][coupon], go(day+1, coupon));
			return dp[day][coupon];
		} else {
			if(coupon >= 3) {
				dp[day][coupon] = Math.min(dp[day][coupon], go(day+1, coupon-3));
			}
			dp[day][coupon] = Math.min(dp[day][coupon], go(day+1, coupon) + 10000);
			dp[day][coupon] = Math.min(dp[day][coupon], go(day+3, coupon+1) + 25000);
			dp[day][coupon] = Math.min(dp[day][coupon], go(day+5, coupon+2) + 37000);
		}
		
		return dp[day][coupon];
	}

}
