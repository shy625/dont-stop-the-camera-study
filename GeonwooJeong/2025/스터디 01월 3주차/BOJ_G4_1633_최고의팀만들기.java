import java.util.Scanner;

public class BOJ_G4_1633_최고의팀만들기 {

	public static void main(String[] args) {	
		Scanner scann = new Scanner(System.in);
		
		// 총 플레이어의 수
		int N = 0;
		// 플레이어의 능력치를 저장할 배열
		int [][] arr = new int[1001][2];
		
		// N이 주어지지 않았으므로 늘려가면서 저장한다.
		while(scann.hasNextInt()) {
			arr[++N][0] = scann.nextInt();
			arr[N][1] = scann.nextInt();
		}		
		
		// 1번째 : 플레이어 idx, 2번째 : 지금까지 선택한 백플레이어 수, 3번째 : 지금까지 선택한 흑플레이어 수
		int [][][] dp = new int[N+1][16][16];
		// i번째 플레이어까지 확인했을 때 최댓값, 플레이어를 받으면서 계속해서 갱신한다.
		int max = 0;
		
//		// 첫 플레이어를 흑팀, 백팀으로 골랐을 때의 값을 설정한다. (초기값 설정)
		dp[1][1][0] = arr[1][0];
		dp[1][0][1] = arr[1][1];
		
		for (int i = 2; i <= N; i++) {
			for (int w = 0; w <= 15; w++) {
				for (int b = 0; b <= 15; b++) {
					// 현재 플레이어를 백팀에 넣을 때 능력치 총합
					int white = 0;
					// 현재 플레이어를 흑팀에 넣을 때 능력치 총합
					int black = 0;
					
					// 현재 플레이어를 1. 백팀에 넣기 2. 흑팀에 넣기 3. 아무데도 안넣기 할 수 있다.
					// 1. 백팀에 넣으려면 w가 0보다 커야하고, 이전 플레이어까지 w-1명 선택한 값에서 현재 플레이어를 백팀에 넣는다.
					if(w > 0) white = dp[i-1][w-1][b] + arr[i][0];
					// 2. 흑팀에 넣으려면 b가 0보다 커야하고, 이전 플레이어까지 b-1명 선택한 값에서 현재 플레이어를 흑팀에 넣는다.
					if(b > 0) black = dp[i-1][w][b-1] + arr[i][1];
					// 세 경우 중 가장 큰 값을 dp 배열에 갱신한다.
					dp[i][w][b] = Math.max(dp[i-1][w][b], Math.max(white, black));
					
					// 각각 15명씩 선정했을 때 max 값을 갱신한다.
					if(w == 15 && b == 15) {
						max = Math.max(max, dp[i][w][b]);
					}
				}
			}
		}
		
		System.out.println(max);

	}

}
