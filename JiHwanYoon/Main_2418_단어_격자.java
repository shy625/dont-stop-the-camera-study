

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2418_단어_격자 {
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		for (int h = 0; h < H; h++) {
			String s = br.readLine();
			for (int w = 0; w < W; w++) {
				map[h][w] = s.charAt(w);
			}
		}
		char[] cs = br.readLine().toCharArray();
		// dynamic programming을 활용
		// dp[i][j][k]는 (i, j)에 있는 문자를 마지막으로 고려했을 때 l번째 문자까지 확인한 경우의 수
		long[][][] dp = new long[H][W][L];
		// 첫 번째 문자에 대한 초기화
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (map[h][w] == cs[0]) dp[h][w][0] = 1;
			}
		}
		// 그 이후의 문자에 대해서는 인접한 문자까지의 경우의 수를 고려해 계산
		for (int l = 1; l < L; l++) {
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (map[h][w] == cs[l]) {
						for (int d = 0; d < 8; d++) {
							int nr = h + dr[d];
							int nc = w + dc[d];
							if (0<=nr && nr<H && 0<=nc && nc<W) dp[h][w][l] += dp[nr][nc][l-1];
						}
					}
				}
			}
		}
		long sum = 0; // 단어를 만드는 모든 경우의 수
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				sum += dp[h][w][L-1];
			}
		}
		System.out.println(sum);
	}

}
