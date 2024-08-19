

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2115_갤러리 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		boolean[][][] set = new boolean[M][N][4]; // 벽의 특정 방향에 그림을 걸었는지를 나타내는 배열
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) == 'X' ? 1 : 0;
			}
		}
		int cnt = 0; // 걸 수 있는 그림의 최대 개수
		// 2*2 격자를 탐색하면서 그림을 걸 수 있는지 확인
		for (int i = 0; i < M-1; i++) {
			for (int j = 0; j < N-1; j++) {
				// X.
				// X.
				if (map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 0 && map[i+1][j+1] == 0 && !set[i][j][0] && !set[i+1][j][0]) {
					cnt++;
					set[i][j][0] = set[i+1][j][0] = true;
				}
				// .X
				// .X
				if (map[i][j] == 0 && map[i+1][j] == 0 && map[i][j+1] == 1 && map[i+1][j+1] == 1 && !set[i][j+1][1] && !set[i+1][j+1][1]) {
					cnt++;
					set[i][j+1][1] = set[i+1][j+1][1] = true;
				}
				// XX
				// ..
				if (map[i][j] == 1 && map[i+1][j] == 0 && map[i][j+1] == 1 && map[i+1][j+1] == 0 && !set[i][j][2] && !set[i][j+1][2]) {
					cnt++;
					set[i][j][2] = set[i][j+1][2] = true;
				}
				// ..
				// XX
				if (map[i][j] == 0 && map[i+1][j] == 1 && map[i][j+1] == 0 && map[i+1][j+1] == 1 && !set[i+1][j][3] && !set[i+1][j+1][3]) {
					cnt++;
					set[i+1][j][3] = set[i+1][j+1][3] = true;
				}
			}
		}
		System.out.println(cnt);
	}
}
