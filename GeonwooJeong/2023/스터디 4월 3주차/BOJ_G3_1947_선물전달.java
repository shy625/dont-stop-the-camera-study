import java.util.Scanner;

public class BOJ_G3_1947_선물전달 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		
		int div = 1000000000;
		long [] dp = new long[N+2];
		
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = 1;
		
		for (int i = 3; i <= N; i++) {
			dp[i] = ((dp[i-1]+dp[i-2]) % div * (i-1)) % div;
		}
		
		System.out.println(dp[N]);

	}

}
