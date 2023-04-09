import java.util.Scanner;

public class Main_14501_퇴사 {

	static int N;
	static int[] dp;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		dp=new int[N+2];
		
		for (int i = 1; i <=N; i++) {
			int time = scann.nextInt();
			int money= scann.nextInt();
			
			//일을 하거나
			if(i+time<=N+1) {
				dp[i+time]=Math.max(dp[i+time], dp[i]+money);
			}
			//일을 하지 않거나
			if(i+1<=N+1) {
				dp[i+1]=Math.max(dp[i+1], dp[i]);
			}
		}
		
		System.out.println(dp[N+1]);

	}

}
