import java.util.Scanner;

public class Main_2156_포도주시식 {
	
	static int N;
	static int [] arr;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		arr=new int [N+1];
		for (int i = 1; i <= N; i++) {
			arr[i]=scann.nextInt();
		}
		
		//구하기
		int dp[]=new int[N+1];
		dp[0]=0;
		if(N>=1)
		dp[1]=arr[1];
		if(N>=2)
		dp[2]=arr[1]+arr[2];
		//점화식
		//n을 마시는 경우  : n-1을 마실 경우->n-2는 마실수 없기에 dp[n-3]+arr[n-1]+arr[n] , n-1을 마시지 않는 경우->dp[n-2]+arr[n];//n-3이 있기에 0=0으로 시작
		//n을 마시지 않는 경우: dp[n-1]
		//3가지 경우 중 최댓값 찾기
		for (int i = 3; i <=N; i++) {
			dp[i]=Math.max(dp[i-3]+arr[i-1]+arr[i],dp[i-2]+arr[i]);
			dp[i]=Math.max(dp[i], dp[i-1]);
		}
		
		
		
		System.out.println(dp[N]);
	}

}
