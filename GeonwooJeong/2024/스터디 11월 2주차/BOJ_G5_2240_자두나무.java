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
		// 자두의 떨어지는 위치를 저장할 배열
		int [] arr = new int[T+1];
		
		for (int i = 1; i <= T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 3차원 dp 배열, 시간/이동횟수/나무번호
		int [][][] dp = new int[T+1][W+2][3];
		
		// 1초부터 T초까지의 결과를 갱신한다.
		for (int i = 1; i <= T; i++) {
			// 이동거리 1부터 W+1까지의 결과를 갱신한다.
			// 점화식을 편하게 세우기 위해 0~W에서 1~W+1로 변경함
			for (int j = 1; j <= W+1; j++) {
				// 1번 자두나무에서 떨어진 경우
				if(arr[i] == 1) {
					// 1번 자리에 있으면 1개를 더 먹을 수 있다. (가만히 있는 경우, 자리를 옮긴 경우)
					dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2])+1;
					// 2번 자리에 있으면 그대로이다. (가만히 있는 경우, 자리를 옮긴 경우)
					dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]);
				// 2번 자두나무에서 떨어진 경우
				} else {
					// 2번 자두나무에서 제일 먼저 떨어지는 경우는 넘어간다.
					if(i == 1 && j == 1) continue;
					dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]);
					dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1])+1;
				}
			}
		}
		
		// 움직인 횟수마다, 마지막에 위치한 자리마다 max를 갱신해준다.
		int max = 0;
		for (int i = 1; i <= W+1; i++) {
			max = Math.max(max, Math.max(dp[T][i][1], dp[T][i][2]));
		}
		
		System.out.println(max);
		
	}

}
