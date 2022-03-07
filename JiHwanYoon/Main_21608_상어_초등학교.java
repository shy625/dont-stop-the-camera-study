

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21608_상어_초등학교 {
	static int N;
	// 교실의 상태를 나타내는 배열
	static int[][] classroom;
	// 각 상어들이 좋아하는 상어들을 저장하는 배열
	static int[][] favorites;
	// 자리 지정 순서
	static int[] order;
	// |r_1 - r_2| + |c_1 - c_2| = 1이 되는 경우는 4방으로 인접한 경우 뿐이다.
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	// 빈 자리를 나타내는 class
	static class Chair implements Comparable<Chair>{
		// r : 행, c : 열, empty : 인접한 빈 자리 수, fav : 인접한, 좋아하는 상어가 앉은 자리의 수 
		int r, c, empty, fav;
		public Chair(int r, int c, int empty, int fav) {
			this.r = r;
			this.c = c;
			this.empty = empty;
			this.fav = fav;
		}
		// 문제에서 주어진 조건을 활용해 빈 자리끼리 비교하는 방식을 나타냄
		@Override
		public int compareTo(Chair c) {
			if (this.fav == c.fav) {
				if (this.empty == c.empty) {
					if (this.r == c.r) {
						return this.c - c.c;
					} else {
						return this.r - c.r;
					}
				} else {
					return c.empty - this.empty;
				}
			} else {
				return c.fav - this.fav;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		classroom = new int[N][N];
		// 1번 상어~N^2번 상어까지 각 상어가 좋아하는 상어들(4마리)를 저장
		favorites = new int[N*N+1][4];
		// 1번 상어~N^2번 상어까지의 순서 저장
		order = new int[N*N];
		for (int i = 0; i < N*N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int cur = Integer.parseInt(st.nextToken());
			order[i] = cur;
			for (int j = 0; j < 4; j++) {
				favorites[cur][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 순서에 따라 상어를 빈 자리에 앉힌다.
		for (int cur : order) {
			sit(cur);
		}
		// 학생의 만족도
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<N) {
						for (int k = 0; k < 4; k++) {
							if (favorites[classroom[i][j]][k] == classroom[nr][nc]) cnt++;
						}
					}
				}
				if (cnt > 0) sum += Math.pow(10, cnt-1);
			}
		}
		System.out.println(sum);
	}
	private static void sit(int cur) {
		// 남아 있는 빈 자리 중 규칙에 따라 가장 우선 순위가 높은 자리를 선택하기 위한 우선 순위 큐
		PriorityQueue<Chair> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 자리가 빈 자리인 경우에 조사
				if (classroom[i][j] == 0) {
					// 인접한 자리 중 빈 자리의 수
					int empty = 0;
					// 인접한 자리 중 좋아하는 상어가 앉은 자리의 수
					int fav = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (0<=nr && nr<N && 0<=nc && nc<N) {
							if (classroom[nr][nc] == 0) empty++;
							for (int k = 0; k < 4; k++) {
								if (favorites[cur][k] == classroom[nr][nc]) fav++;
							}
						}
					}
					pq.offer(new Chair(i, j, empty, fav));
				}
			}
		}
		// 빈 자리 중 가장 우선 순위가 높은 자리를 찾아 상어를 그 자리에 앉힌다.
		Chair ch = pq.poll();
		classroom[ch.r][ch.c] = cur;		
	}
}
