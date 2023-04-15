import java.util.Scanner;

public class Main_17626_FourSquares {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int N = scann.nextInt();
		int [] dp = new int[N+1];
		int min = 0;
		// 초기값 설정
		dp[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			// 만들 수 있는 조합 중 최소의 가짓수
			min = Integer.MAX_VALUE;
			// j*j이 i보다 작을 때 까지 계산한다.
			for (int j = 1; j*j < i; j++) {
				// 최소 가짓수 업데이트
				min = Math.min(min, dp[i-j*j]);
			}
			// 1을 더해줘야 계산이 맞는다.
			dp[i] = min+1;
		}

		System.out.println(dp[N]);
	}

}
