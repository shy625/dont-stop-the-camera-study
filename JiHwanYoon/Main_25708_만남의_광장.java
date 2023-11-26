

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25708_만남의_광장 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 누적합을 활용한다.
		int[] rowSum = new int[N]; // 각 행의 누적합
		int[] colSum = new int[M]; // 각 열의 누적합
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				rowSum[i] += map[i][j];
			}
		}
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				colSum[j] += map[i][j];
			}
		}
		int max = Integer.MIN_VALUE; // 광장의 아름다움의 최댓값
		// 길을 놓을 r1, r2번째 행과 c1, c2번째 열을 선택하고 이때 광장의 아름다움을 계산한다.
		for (int r1 = 0; r1 < N; r1++) {
			for (int r2 = r1+1; r2 < N; r2++) {
				for (int c1 = 0; c1 < M; c1++) {
					for (int c2 = c1+1; c2 < M; c2++) {
						max = Math.max(max, rowSum[r1] + rowSum[r2] + colSum[c1] + colSum[c2] - map[r1][c1] - map[r1][c2] - map[r2][c1] - map[r2][c2] + (r2 - r1 - 1)*(c2 - c1 - 1));
					}
				}
			}
		}
		System.out.println(max);
	}

}
