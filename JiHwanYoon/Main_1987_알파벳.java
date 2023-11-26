

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1987_알파벳 {
	static int R, C;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[] visitedA = new boolean[26];
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 지금까지 지나온 알파벳들을 저장하는 방문 배열
		visitedA[map[0][0]-'A'] = true;
		// 가장 많은 알파벳을 지났을 때 지나온 알파벳 수를 구한다.
		int res = dfs(0, 0);
		System.out.println(res);
	}
	// (0, 0)에서 시작 후 현재 (i, j)일 때 방문한 알파벳의 개수의 최댓값을 구하는 함수
	private static int dfs(int i, int j) {
		int max = 1; // 방문한 알파벳의 개수의 최댓값
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (0<=nr && nr<R && 0<=nc && nc<C && !visitedA[map[nr][nc]-'A']) {
				visitedA[map[nr][nc]-'A'] = true;
				max = Math.max(dfs(nr, nc)+1, max);
				visitedA[map[nr][nc]-'A'] = false;
			}
		}
		return max;
	}
}
