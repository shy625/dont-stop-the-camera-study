

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1577_도로의_개수 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		// (r, c)까지 특정 방향으로 이동 가능한지 나타내는 배열
		// canPass[r][c][0]은 (r-1, c) -> (r, c), canPass[r][c][1]은 (r, c-1) -> (r, c)로 이동 가능한지를 나타낸다.
		boolean[][][] canPass = new boolean[N+1][M+1][2];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				Arrays.fill(canPass[i][j], true);
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int minR = Math.min(r1, r2);
			int minC = Math.min(c1, c2);
			int maxR = Math.max(r1, r2);
			int maxC = Math.max(c1, c2);
			if (minR < maxR) canPass[maxR][maxC][0] = false;
			if (minC < maxC) canPass[maxR][maxC][1] = false;
		}
		// dynamic programming을 활용
		// dp[i][j]는 (0, 0)에서 (i, j)까지 가는 방법의 수 
		long[][] dp = new long[N+1][M+1];
		dp[0][0] = 1;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				if (i >= 1 && canPass[i][j][0]) dp[i][j] += dp[i-1][j];
				if (j >= 1 && canPass[i][j][1]) dp[i][j] += dp[i][j-1];
			}
		}
		System.out.println(dp[N][M]);
	}

}
