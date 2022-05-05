

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15684_사다리_조작 {
	static int[][] map;
	static int N, H, M;
	static int cnt = Integer.MAX_VALUE;
	static boolean[][] cantBuild;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H+1][N+1];
		// 각 지점에 가로선을 놓을 수 있는지를 나타내는 배열
		cantBuild = new boolean[H+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
			// (r, c) 지점에 가로선을 놓으면 (r, c), (r, c-1), (r, c+1) 지점에 가로선을 놓을 수 없다.
			cantBuild[r][c] = true;
			if (c > 0) cantBuild[r][c-1] = true;
			if (c < N) cantBuild[r][c+1] = true;
		}
		recur(0, 1);
		System.out.println((cnt==Integer.MAX_VALUE)?-1:cnt);
	}
	private static void recur(int num, int startH) {
		// i번 세로선의 결과가 i번이 나오는지 확인
		if (check()) {
			cnt = Math.min(cnt, num);
			return;
		}
		// 정답이 3보다 커지는 경우 return
		else if (num == 3) return;
		// 조합을 통해 각 지점마다 가로선 놓기 시도
		for (int i = startH; i <= H; i++) {
			for (int j = 0; j < N; j++) {
				// 가로선을 놓을 수 있는 경우
				if (!cantBuild[i][j]) {
					// 가로선을 놓으면서 이로 인해 가로선을 놓을 수 없는 지점을 확인
					cantBuild[i][j] = true;
					if (j > 0) cantBuild[i][j-1] = true;
					if (j < N) cantBuild[i][j+1] = true;
					map[i][j] = 1;
					// 가로선을 놓고 다음으로 가로선을 놓을 곳을 재귀적으로 탐색
					recur(num+1, i);
					// 위의 시도를 모두 끝내면 backtracking을 통해 원래대로 돌아간다.
					// 단, (i, j-1)과 (i, j+1)은 주의해서 backtracking해야 한다.
					cantBuild[i][j] = false;
					if (j == 1 || (j > 1 && map[i][j-2] == 0)) cantBuild[i][j-1] = false;
					if (j == N-1 || (j < N-1 && map[i][j+2] == 0)) cantBuild[i][j+1] = false;
					map[i][j] = 0;
				}
			}
		}
		
	}
	private static boolean check() {
		for (int c = 1; c <= N; c++) {
			int cur = c;
			for (int h = 1; h <= H; h++) {
				if (cur < N && map[h][cur] == 1) {
					cur++;
				} else if (cur > 1 && map[h][cur-1] == 1) {
					cur--;
				}
			}
			if (cur != c) return false;
		}
		return true;
	}

}

