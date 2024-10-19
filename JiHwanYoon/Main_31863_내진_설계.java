package algoStudy_2024_10월_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_31863_내진_설계 {
	// 지진 발생 지점의 위치와 발생 범위를 나타내는 클래스
	static class Location {
		int r, c, range;
		public Location(int r, int c, int range) {
			this.r = r;
			this.c = c;
			this.range = range;
		}
	}
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		// 지진 발생 지점을 큐에 저장한다.
		Queue<Location> q = new LinkedList<>();
		int total = 0; // 전체 건물의 개수
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				// 진원을 큐에 저장
				if (map[i][j] == '@') {
					q.offer(new Location(i, j, 2));
					map[i][j] = '.';
				} else if (map[i][j] == '*' || map[i][j] == '#') total++;
			}
		}
		int destroyed = 0; // 파괴된 건물의 개수
		while (!q.isEmpty()) {
			Location l = q.poll();
			// 지진 발생 지점을 기준으로 주변으로 지진이 뻗어나갈 때 영향을 받는 건물을 조사
			outer: for (int d = 0; d < 4; d++) {
				for (int k = 1; k <= l.range; k++) {
					int nr = l.r + dr[d]*k;
					int nc = l.c + dc[d]*k;
					// 지진이 지역 밖으로 나가거나 방파제를 만나는 경우
					if (!check(nr, nc) || map[nr][nc] == '|') continue outer;
					// 내진 설계된 건물은 1번만 더 지진을 맞으면 무너지므로 *로 바꿔준다.
					if (map[nr][nc] == '#') {
						map[nr][nc] = '*';
					// 내진 설계되지 않은 건물은 무너지고 여진을 만든다.
					} else if (map[nr][nc] == '*') {
						map[nr][nc] = '.';
						destroyed++;
						q.offer(new Location(nr, nc, 1));
					}
				}
			}
		}
		System.out.println(destroyed + " " + (total - destroyed));
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
