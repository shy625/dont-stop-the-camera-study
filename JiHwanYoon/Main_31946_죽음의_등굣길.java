package algoStudy_2024_10월_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_31946_죽음의_등굣길 {
	// 현재 위치를 나타내는 클래스
	static class Location {
		int r, c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int X = Integer.parseInt(br.readLine());
		// 너비 우선 탐색을 활용
		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(new Location(0, 0));
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Location l = q.poll();
			// 맨해튼 거리로 X 이하만큼 떨어진 곳 탐색
			for (int x = 1; x <= X; x++) {
				for (int dr = -x; dr <= x; dr++) {
					for (int k = -1; k <= 1; k += 2) {
						int dc = k*(x - Math.abs(dr));
						int nr = l.r + dr;
						int nc = l.c + dc;
						if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[0][0]) {
							// 목적지에 도착한 경우
							if (nr == N-1 && nc == M-1) {
								System.out.println("ALIVE");
								System.exit(0);
							}
							visited[nr][nc] = true;
							q.offer(new Location(nr, nc));
						}
					}					
				}
			}
		}
		// 목적지에 도착하지 못하는 경우
		System.out.println("DEAD");
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
