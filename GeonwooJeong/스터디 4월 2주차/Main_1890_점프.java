import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1890_점프 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		int [][] map = new int[N+1][N+1];
		// 경로의 개수가 2^63-1개 이하이므로 long 타입으로 선언한다.
		long [][] dp = new long[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 출발점 설정
		dp[1][1] = 1;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 몇 칸을 점프할 지 저장할 변수
				int jump = map[i][j];
				// 도착점인 경우 빠져나간다.
				if(i == N && j == N) break;
				// 아래로 갔을 때 게임판을 넘어가지 않는다면, jump 수 만큼 점프한다.
				if(i+jump <= N) {
					// 해당 칸에 도달할 수 있는 가짓수를 추가한다.
					dp[i+jump][j] += dp[i][j];
				// 오른쪽으로 갔을 때 게임판을 넘어가지 않는다면, jump 수 만큼 점프한다.	
				} if(j+jump <= N) {
					// 해당 칸에 도달할 수 있는 가짓수를 추가한다. 
					dp[i][j+jump] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[N][N]);
		
	}

}
