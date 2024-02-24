

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5546_파스타 {
	static int CONST = 10000;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// dynamic programming을 활용
		// dp[i][j][k]는 i-1번째 날 j번째 파스타, i번째 날 k번째 파스타를 먹는 경우의 수를 10000으로 나눈 나머지
		int[][][] dp = new int[N+1][3][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken())-1;
			// n번째 날에 k번째 파스타를 먹으면 나머지 날은 다른 파스타를 먹지 못한다.
			// 이를 dp[n][_][j]와 dp[n+1][j][_]에 표시
			for (int j = 0; j < 3; j++) {
				if (j == k) continue;
				for (int l = 0; l < 3; l++) {					
					dp[n][l][j] = -1;
					if (n < N) dp[n+1][j][l] = -1;
				}
			}
		}
		// 초기화
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (dp[2][i][j] == -1) continue;
				dp[2][i][j] = 1;
			}
		}
		for (int i = 3; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						// 3일 연속 같은 파스타를 먹을 수 없다.
						if (j == k && k == l) continue; 
						// i번째 날에 l번째 파스타를 먹지 못하거나, i-1번째 날에 k번째 파스타를 먹지 못하는 경우
						if (dp[i][k][l] == -1 || dp[i-1][j][k] == -1) continue;
						dp[i][k][l] = (dp[i][k][l] + dp[i-1][j][k])%CONST;
					}
				}
			}
		}
		int sum = 0; // 경우의 수의 합을 10000으로 나눈 나머지
		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				if (dp[N][j][k] == -1) continue;
				sum = (sum + dp[N][j][k])%CONST;
			}
		}
		System.out.println(sum);
	}

}
