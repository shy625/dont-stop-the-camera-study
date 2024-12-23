import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2240_자두나무 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		// 시간, 이동횟수, 현재위치를 기준으로 3차원 배열로 나눈다.
		// 점화식을 쉽게 구성하기 위해 이동횟수의 범위를 1~W+1로 변경함.
		int [][][] dp = new int[T+1][W+2][2];
		
		int first = Integer.parseInt(br.readLine());
		
		// 초기조건 설정
		if(first == 1) {
			dp[1][1][0] = 1;
		} else {
			dp[1][2][1] = 1;
		}
		
		for (int i = 2; i <= T; i++) {
			int jadu = Integer.parseInt(br.readLine())-1;
			
			for (int j = 1; j <= W+1; j++) {
				// 현재 0번 위치에 있다면, 이전에 0번 위치에 가만히 있거나 1번 위치에서 움직여야한다.
				dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j-1][1]);
				// 현재 1번 위치에 있다면, 이전에 0번 위치에서 움직이거나 1번 위치에 가만히 있어야한다.
				dp[i][j][1] = Math.max(dp[i-1][j-1][0], dp[i-1][j][1]);
				
				// 자두가 떨어진 위치에 +1 해준다.
				dp[i][j][jadu]++;
			}
		}
		
		int ans = 0;
		
		// 이동횟수, 마지막 위치를 모두 확인해준다.
		for (int i = 1; i <= W+1; i++) {
			ans = Math.max(ans, Math.max(dp[T][i][0], dp[T][i][1]));
		}
		
		System.out.println(ans);
		
	}

}
