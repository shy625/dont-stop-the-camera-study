

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1890_점프 {
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int N = Integer.parseInt(br.readLine());
		 int[][] map = new int[N][N];
		 for (int i = 0; i < N; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			 for (int j = 0; j < N; j++) {
				 map[i][j] = Integer.parseInt(st.nextToken());
			 }
		 }
		 // 각 좌표까지 가는 경로의 수를 기록하는 배열
		 long[][] dp = new long[N][N];
		 // 초기화
		 dp[0][0] = 1;
		 // 아래 방향과 오른쪽 방향만 갈 수 있기 때문에, 이중 for문으로도 풀 수 있다.
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 int d = map[i][j];
				 // 갈 수 없는 지점
				 if (d == 0) continue;
				 // 아래 방향과 오른쪽 방향으로 이동
				 // 단, 게임 판을 넘어가지 않도록 주의
				 if (i<N-d) dp[i+d][j] += dp[i][j];
				 if (j<N-d) dp[i][j+d] += dp[i][j];
			 }
		 }
		 System.out.println(dp[N-1][N-1]);
	}

}
