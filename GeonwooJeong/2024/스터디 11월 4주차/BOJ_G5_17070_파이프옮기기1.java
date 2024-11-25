import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17070_파이프옮기기1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 벽 여부를 저장할 2차원 배열
		boolean [][] map = new boolean[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				// 벽이 있는 곳만 true 처리 해준다.
				if(n == 1) map[i][j] = true;
			}
		}
		
		// 3차원 dp 배열을 사용한다. (가로, 대각선, 세로 방향)
		int [][][] dp = new int[N+1][N+1][3];
		
		// 처음 파이프는 가로 위치로 고정이다.
		dp[1][2][0] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 첫 위치이거나, 해당 자리에 벽이 있으면 넘어간다.
				if((i == 1 && j == 2) || map[i][j]) continue;
				
				// 가로 파이프는 이전 자리의 파이프가 가로 또는 대각선이어야 한다.
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				// 세로 파이프는 이전 자리의 파이프가 대각선 또는 세로이어야 한다.
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				
				// 대각선 파이프는 왼쪽, 위에 벽이 있으면 설치할 수 없다.
				if(!map[i-1][j] && !map[i][j-1]) {
					// 대각선 파이프는 이전 자리의 파이프 방향이 상관 없다.
					dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		int ans = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
		
		System.out.println(ans);
		
	}

}
