import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1577_도로의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 아래로 가는 경우, 오른쪽으로 가는 경우 2가지 경우의 수 존재
		boolean [][][] forbid = new boolean[N+1][M+1][2];
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			int sum1 = r1 + c1;
			int sum2 = r2 + c2;
			
			// r1, c1, r2, c2가 정렬이 되어있지 않기 때문에 값을 확인하여 해당 점과 방향을 금지시킨다.
			if(sum1 > sum2) {
				if(r1 > r2) forbid[r2][c2][0] = true;
				else forbid[r2][c2][1] = true;
			} else {
				if(r1 < r2) forbid[r1][c1][0] = true;
				else forbid[r1][c1][1] = true;
			}
			
		}
		
		// int 범위를 넘어가므로 long으로 dp배열 선언
		long [][] dp = new long[N+1][M+1];
		// 초기값 설정
		dp[0][0] = 1;
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				// i-1, j로부터 오는 길이 금지되어있지 않다면 경우의 수를 더해준다.
				if (i >= 1 && !forbid[i-1][j][0]) dp[i][j] += dp[i-1][j];
				// i, j-1로부터 오는 길이 금지되어있지 않다면 경우의 수를 더해준다.
				if (j >= 1 && !forbid[i][j-1][1]) dp[i][j] += dp[i][j-1];
			}
		}
		
		System.out.println(dp[N][M]);
		
	}

}
