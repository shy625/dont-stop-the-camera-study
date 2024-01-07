

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_27978_보물_찾기_2 {
	// 현재 위치의 좌표 및 해당 위치까지 이동하는데 소모한 연료의 양을 나타내는 클래스
	static class Location implements Comparable<Location> {
		int r, c, cost;
		public Location(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		public int compareTo(Location l) {
			return Integer.compare(this.cost, l.cost);
		}
	}
	static int H, W;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static Location start, end;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'K') start = new Location(i, j, 0); // 시작 위치
				if (map[i][j] == '*') end = new Location(i, j, 0); // 보물 위치
			}
		}
		// dijkstra's algorithm을 활용
		int[][] dp = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		dp[start.r][start.c] = 0;
		PriorityQueue<Location> pq = new PriorityQueue<>();
		pq.offer(start);
		boolean[][] visited = new boolean[H][W];
		while (!pq.isEmpty()) {
			Location u = pq.poll();
			if (visited[u.r][u.c]) continue;
			visited[u.r][u.c] = true;
			// 보물이 있는 곳에 도착한 경우 최소 비용 출력
			if (u.r == end.r && u.c == end.c) {
				System.out.println(u.cost);
				return;
			}
			for (int d = 0; d < 8; d++) {
				int nr = u.r + dr[d];
				int nc = u.c + dc[d];
				// 이동 방향에 따른 비용 산정
				int c = (1 <= d && d <= 3) ? 0 : 1;
				if (check(nr, nc) && map[nr][nc] != '#' && dp[nr][nc] > dp[u.r][u.c] + c) {
					dp[nr][nc] = dp[u.r][u.c] + c;
					pq.offer(new Location(nr, nc, dp[nr][nc]));
				}
			}
		}
		System.out.println(-1);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<H && 0<=c && c<W;
	}
}
