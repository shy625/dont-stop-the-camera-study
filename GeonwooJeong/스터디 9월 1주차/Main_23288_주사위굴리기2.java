import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288_주사위굴리기2 {
	static int [] dr = {0, 1, 0, -1}; // 오른쪽부터 시계
	static int [] dc = {1, 0, -1, 0};
	static int N, M, K, d, ans;
	static int[][] map, Scoremap; // 실제 map, 해당 칸에 도달했을 때 얻게되는 점수 map

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Scoremap = new int[N][M];
		ans = 0;
		
		// map 구성하기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Scoremap에 점수를 미리 구해놓는다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				BFS(i, j);
			}
		}
		
		dice D = new dice(4, 3, 5, 2, 6, 1, 0, 0); // (좌 우 앞 뒤 아래 위 r, c)
		d = 0;
		 // K번 주사위 굴리기
		for (int i = 0; i < K; i++) {
			if(!check(D.r+dr[d], D.c+dc[d])) { // 맵 범위를 벗어나면
				d = (d + 2) % 4; // 반대방향으로 바꿔주기
			}
			int nr = D.r + dr[d];
			int nc = D.c + dc[d];
		
			switch (d) {
			case 0: // 오른쪽 방향
				D.rollRight(); // 주사위를 오른쪽으로 굴린다. (면 바꾸기)
                // 굴린 뒤 주사위의 위치를 갱신한다.
				D.r = nr;
				D.c = nc;
				ans += Scoremap[nr][nc]; // 움직인 칸에서의 점수를 추가해준다.
                // 맵의 숫자와 바닥면의 숫자와 비교해서 다음 방향을 정한다.
				if (D.bottom > map[D.r][D.c]) {
					d = (d + 1) % 4;
				} else if (D.bottom < map[D.r][D.c]) {
					d = (4 + d - 1) % 4;
				}
				break;
			case 1: // 아래 방향
				D.rollFront();
				D.r = nr;
				D.c = nc;
				ans += Scoremap[nr][nc];
				if (D.bottom > map[D.r][D.c]) {
					d = (d + 1) % 4;
				} else if (D.bottom < map[D.r][D.c]) {
					d = (4 + d - 1) % 4;
				}
				break;
			case 2: // 왼쪽 방향
				D.rollLeft();
				D.r = nr;
				D.c = nc;
				ans += Scoremap[nr][nc];
				if (D.bottom > map[D.r][D.c]) {
					d = (d + 1) % 4;
				} else if (D.bottom < map[D.r][D.c]) {
					d = (4 + d - 1) % 4;
				}
				break;
			case 3: // 위 방향
				D.rollBack();
				D.r = nr;
				D.c = nc;
				ans += Scoremap[nr][nc];
				if (D.bottom > map[D.r][D.c]) {
					d = (d + 1) % 4;
				} else if (D.bottom < map[D.r][D.c]) {
					d = (4 + d - 1) % 4;
				}
				break;
			}
		}
		
		System.out.println(ans);

	}

	public static void BFS(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[N][M];
		q.add(new int[] {r, c});
		v[r][c] = true;
		
		int sum = map[r][c];
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(!check(nr, nc)) continue;
				
				if (!v[nr][nc] && map[r][c] == map[nr][nc]) {
					v[nr][nc] = true;
					sum += map[nr][nc];
					q.add(new int[]{nr, nc});
				}
			}
		}
		Scoremap[r][c] = sum;
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

	static class dice {
		int left;
		int right;
		int front;
		int back;
		int bottom;
		int up;
		int r;
		int c;

		public dice(int left, int right, int front, int back, int bottom, int up, int r, int c) {
			this.left = left;
			this.right = right;
			this.front = front;
			this.back = back;
			this.bottom = bottom;
			this.up = up;
			this.r = r;
			this.c = c;
		}

		public void rollLeft() { 
			int tempLeft = this.left;
			int tempRight = this.right;
			int tempBottom = this.bottom;
			int tempUp = this.up;

			this.left = tempUp;
			this.right = tempBottom;
			this.bottom = tempLeft;
			this.up = tempRight;

		}

		public void rollRight() {
			int tempLeft = this.left;
			int tempRight = this.right;
			int tempBottom = this.bottom;
			int tempUp = this.up;

			this.left = tempBottom;
			this.right = tempUp;
			this.bottom = tempRight;
			this.up = tempLeft;

		}

		public void rollFront() {
			int tempFront = this.front;
			int tempBack = this.back;
			int tempBottom = this.bottom;
			int tempUp = this.up;

			this.bottom = tempFront;
			this.up = tempBack;
			this.front = tempUp;
			this.back = tempBottom;

		}

		public void rollBack() {
			int tempFront = this.front;
			int tempBack = this.back;
			int tempBottom = this.bottom;
			int tempUp = this.up;

			this.bottom = tempBack;
			this.up = tempFront;
			this.front = tempBottom;
			this.back = tempUp;

		}

	}

}
