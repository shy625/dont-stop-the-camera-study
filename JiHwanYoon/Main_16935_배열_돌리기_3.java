

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16935_배열_돌리기_3 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		// 배열에 적용할 연산
		int[] types = new int[R];
		for (int i = 0; i < R; i++) {
			types[i] = Integer.parseInt(st.nextToken());
		}
		// 결과
		int[][] result = new int[N][M];
		for (int type : types) {
			// 배열 연산 시 행과 열의 개수가 반전이 될 수 있어 항상 행과 열의 수를 파악해야 한다.
			N = map.length; 
			M = map[0].length;
			// 상하 반전
			if (type == 1) {
				for (int i = 0; i < N; i++) {
					result[i] = Arrays.copyOf(map[N-i-1], M);
				}
			// 좌우 반전
			} else if (type == 2) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						result[i][j] = map[i][M-j-1];
					}
				}
			// 오른쪽 90도 회전
			// 이 경우, 행과 열의 개수가 반전이 된다.
			} else if (type == 3) {
				result = new int[M][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						result[j][N-i-1] = map[i][j];
					}
				}
			// 왼쪽 90도 회전
			// 이 경우, 행과 열의 개수가 반전이 된다.
			} else if (type == 4) {
				result = new int[M][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						result[M-1-j][i] = map[i][j];
					}
				}
			// 4개의 부분 배열로 나눠 이동시키는 연산
			} else if (type == 5) {
				for (int i = 0; i < N/2; i++) {
					for (int j = 0; j < M/2; j++) {
						result[i][j+M/2] = map[i][j];
						result[i+N/2][j+M/2] = map[i][j+M/2];
						result[i+N/2][j] = map[i+N/2][j+M/2];
						result[i][j] = map[i+N/2][j];
					}
				}
			} else if (type == 6) {
				for (int i = 0; i < N/2; i++) {
					for (int j = 0; j < M/2; j++) {
						result[i][j] = map[i][j+M/2];
						result[i][j+M/2] = map[i+N/2][j+M/2];
						result[i+N/2][j+M/2] = map[i+N/2][j];
						result[i+N/2][j] = map[i][j];
					}
				}
			}
			// 바뀐 결과를 이전 맵에 저장
			map = new int[result.length][result[0].length];
			for (int i = 0; i < result.length; i++) {
				map[i] = Arrays.copyOf(result[i], result[0].length);
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
