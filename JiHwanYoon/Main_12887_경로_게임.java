

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_12887_경로_게임 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 2;
		M = Integer.parseInt(br.readLine());
		char[][] map = new char[N][M];
		int cnt = 0; // 흰색 칸의 개수
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == '.') cnt++;
			}
		}
		int minLen = Integer.MAX_VALUE; // 왼쪽-오른쪽 경로의 최단 거리
		// 왼쪽 끝 열 2칸에 대한 너비 우선 탐색을 수행하고,
		// 두 경로 중 더 짧은 경로를 찾는다.
		for (int i = 0; i < N; i++) {
			char start = map[i][0];
			if (start == '#') continue; // 검은색 칸에서는 출발할 수 없다.
			// 너비 우선 탐색 수행
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][M];
			q.offer(new int[] {i, 0});
			visited[i][0] = true;
			int l = 1; // 현재 칸에서 출발했을 때 왼쪽-오른쪽 경로의 최단 거리
			outer: while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int[] u = q.poll();
					if (u[1] == M-1) break outer;
					for (int d = 0; d < 4; d++) {
						int nr = u[0] + dr[d];
						int nc = u[1] + dc[d];
						if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					}
					qLen--;
				}
				l++;
			}
			minLen = Math.min(minLen, l);
		}
		// 가장 짧은 왼쪽-오른쪽 경로를 제외한 나머지 칸은 검은색 칸으로 바꿔도 무방하다.
		System.out.println(cnt - minLen);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
