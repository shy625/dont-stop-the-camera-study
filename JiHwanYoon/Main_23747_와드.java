

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23747_와드 {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M]; // 시야를 확보한 지역을 표시하는 배열 
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		int[] cur = new int[2];
		st = new StringTokenizer(br.readLine());
		cur[0] = Integer.parseInt(st.nextToken())-1;
		cur[1] = Integer.parseInt(st.nextToken())-1;
		String history = br.readLine();
		for (char c : history.toCharArray()) {
			// 다른 지역으로 이동하는 경우
			if (c == 'U') cur[0]--;
			else if (c == 'D') cur[0]++;
			else if (c == 'L') cur[1]--;
			else if (c == 'R') cur[1]++;
			// 와드를 설치하는 경우
			else if (c == 'W') ward(cur);
		}
		// 최종 위치 기준 인접한 지역도 시야를 확보한 상태
		visited[cur[0]][cur[1]] = true;
		for (int d = 0; d < 4; d++) {
			int nr = cur[0] + dr[d];
			int nc = cur[1] + dc[d];
			if (check(nr, nc)) visited[nr][nc] = true;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(visited[i][j] ? "." : "#");
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// BFS를 활용해 와드를 설치한 영역을 확인하는 함수
	private static void ward(int[] cur) {
		int r = cur[0]; int c = cur[1];
		if (visited[r][c]) return; // 이미 와드를 설치한 영역인 경우
		char area = map[r][c]; // 와드를 설치한 영역
		Queue<int[]> q = new LinkedList<>();
		q.offer(cur);
		visited[r][c] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == area) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
