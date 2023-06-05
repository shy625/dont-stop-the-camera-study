import java.util.Scanner;

public class Main_11052_카드구매하기 {
	//DP로 풀어보자
	static int N;
	static int [] cards;
	static int [] dp;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		cards=new int[N+1];
		dp=new int[N+1];//dp 저장 배열
		
		for (int i = 1; i <=N; i++) {
			int temp=scann.nextInt();
			cards[i]=temp;
			dp[i]=temp;
		}
		
		for (int i = 1; i <=N; i++) {
			int half=i/2;
			
			for (int j = 0; j <= half; j++) {
				dp[i]=Math.max(dp[i], dp[j]+dp[i-j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}
