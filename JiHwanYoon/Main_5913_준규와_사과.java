

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5913_준규와_사과 {
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	static long res;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		map = new int[N][N];
		// 방문 여부 확인 배열
		visited = new boolean[N][N];
		visited[0][0] = true;
		visited[N-1][N-1] = true;
		K = Integer.parseInt(br.readLine());
		res = 0; // 가능한 경우의 수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = -1;
		}
		check(0, 0, 0, 4, 4);
		System.out.println(res);
	}
	// 재귀를 통해 모든 경우를 시도하고, 그 중 모든 사과나무를 방문하면서, 한 곳에서 만나는 경우의 수를 구한다.
	private static void check(int cnt, int r1, int c1, int r2, int c2) {
		// 한 곳을 제외한 나머지 사과나무를 방문한 경우
		if (cnt == (N*N - K)/2-1) {
			// 두 사람과 인접한 같은 지역이 방문하지 않은 사과나무면 한 곳에서 만날 수 있다.
			for (int d1 = 0; d1 < 4; d1++) {
				int nr1 = r1 + dr[d1];
				int nc1 = c1 + dc[d1];
				if (!checkMap(nr1, nc1) || visited[nr1][nc1] || map[nr1][nc1] == -1) continue;
				for (int d2 = 0; d2 < 4; d2++) {
					int nr2 = r2 + dr[d2];
					int nc2 = c2 + dc[d2];
					if (!checkMap(nr2, nc2) || visited[nr2][nc2] || map[nr2][nc2] == -1) continue;
					if (nr1 == nr2 && nc1 == nc2) res++;
				}
			}
			return;
		}
		// 두 사람 각각 인접한 사과나무가 있는 칸으로 이동
		for (int d1 = 0; d1 < 4; d1++) {
			int nr1 = r1 + dr[d1];
			int nc1 = c1 + dc[d1];
			if (!checkMap(nr1, nc1) || visited[nr1][nc1] || map[nr1][nc1] == -1) continue;
			visited[nr1][nc1] = true;
			for (int d2 = 0; d2 < 4; d2++) {
				int nr2 = r2 + dr[d2];
				int nc2 = c2 + dc[d2];
				if (!checkMap(nr2, nc2) || visited[nr2][nc2] || map[nr2][nc2] == -1) continue;
				visited[nr2][nc2] = true;
				check(cnt+1, nr1, nc1, nr2, nc2);
				visited[nr2][nc2] = false;
			}
			visited[nr1][nc1] = false;
		}
	}
	// 경계 확인용 함수
	private static boolean checkMap(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}
