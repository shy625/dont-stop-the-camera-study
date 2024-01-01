import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1915_가장큰정사각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// dp를 사용하기 위한 배열
		int [][] dp = new int[N+1][M+1];
		// 가장 큰 정사각형의 한 변의 길이
		int ans = 0;
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				char c = str.charAt(j-1);
				
				// c == 0 이면 dp[i][j]는 항상 0이다.
				// c == 1 이면 dp[i][j]에 왼쪽대각선, 위, 왼쪽 값 중에 가장 작은 값 +1을 저장한다.
				if(c == '1') {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
					// 가장 큰 정사각형의 한 변의 길이를 갱신해준다.
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		
		// 넓이를 구하라고 했으므로 ans*ans를 출력한다.
		System.out.println(ans*ans);
		
	}

}
