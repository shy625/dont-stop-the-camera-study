import java.util.Scanner;

public class Main_1563_개근상 {

	static int N;
	static int [][][] dp;//날짜,지각, 결석 연속 수 세기
	static int answer;
	static int NUM=1000000;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		dp=new int[N+1][2][3];
		answer=0;
		
		//초기화
		dp[1][0][0]=1;
		dp[1][0][1]=1;
		dp[1][1][0]=1;

		for (int i = 2; i <=N; i++) {
			//지각 0회 결석 연속 수 
			dp[i][0][0]=(dp[i-1][0][0]+dp[i-1][0][1]+dp[i-1][0][2])%NUM;//지각 0회일 때 현재가 O인것만 가능
			dp[i][0][1]=dp[i-1][0][0] % NUM;//지각 0일때 O일 때 a인 경우만 가능 
			dp[i][0][2]=dp[i-1][0][1] % NUM;//결석 1회일때 현재 A인 경우만 가능
			
			//지각 1회 결석
			dp[i][1][0]=(dp[i-1][0][0]+dp[i-1][0][1]+dp[i-1][0][2]+dp[i-1][1][0]+dp[i-1][1][1]+dp[i-1][1][2]) % NUM;//지각 0회일 때 L인 경우들+지각 1회일 때 O인 경우
			dp[i][1][1]=dp[i-1][1][0] % NUM;//현재 무조건 A나와야해서 지각 0회는 더할 수 없다
			dp[i][1][2]=dp[i-1][1][1] % NUM;
		}
		
		answer=(dp[N][0][0]+dp[N][0][1]+dp[N][0][2]+dp[N][1][0]+dp[N][1][1]+dp[N][1][2])%NUM;
		
		System.out.println(answer);
	}

}
