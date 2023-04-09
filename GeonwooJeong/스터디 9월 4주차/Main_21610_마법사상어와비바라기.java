import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cloud {
	int r;
	int c;
	
	public Cloud(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_21610_마법사상어와비바라기 {
	
	static int N, M;
	static int [][] map;
	static int [] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int [] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int [][] moving;
	static boolean [][] v;
	static Queue<Cloud> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		moving = new int[M][2];
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			moving[i][0] = Integer.parseInt(st.nextToken()) - 1;
			moving[i][1] = Integer.parseInt(st.nextToken());
		}
		
		q.add(new Cloud(N-1, 0));
		q.add(new Cloud(N-1, 1));
		q.add(new Cloud(N-2, 0));
		q.add(new Cloud(N-2, 1));
		
		for (int i = 0; i < M; i++) {
			v = new boolean[N][N];
			// 1~3번
			move_rain(moving[i][0], moving[i][1]);
			// 4번
			add();
			// 5번
			make();
			
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += map[i][j];
			}
		}
		
		System.out.println(ans);

	}
	
	private static void make() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 물의 양이 2 이상이고 이전에 구름이 있던 칸이 아니라면 구름을 생성한다.
				if(map[i][j] >= 2 && !v[i][j]) {
					map[i][j] -= 2;
					q.add(new Cloud(i, j));
				}
			}
		}
		
	}

	private static void add() {
		int size = q.size();
		
		for (int i = 0; i < size; i++) {
			Cloud cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = 0;
			
			// 대각선 4방향을 확인한다.
			for (int d = 1; d < 8; d+=2) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				// 맵 범위를 넘어서지 않고, 물의 양이 1 이상이면 cnt++
				if(check(nr, nc) && map[nr][nc] > 0) {
					cnt++;
				}
				
			}
			// cnt 만큼 물의 양을 더해준다.
			map[r][c] += cnt;
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

	private static void move_rain(int d, int s) {
		int size = q.size();
		
		for (int i = 0; i < size; i++) {
			Cloud cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			// 1번 조건 (s만큼 d 방향으로 옮긴다.)
			for (int j = 0; j <s ; j++) {
				r += dr[d];
				c += dc[d];
				
				// 범위를 벗어날 경우 반대 편으로 옮겨준다.
				r = change(r);
				c = change(c);
				
			}
			
			// 5번 조건에 구름이 새로 생기는 곳은 이전에 구름이 사라진 칸이 아니므로 visit 배열로 체크해준다.
			v[r][c] = true;
			// 2번 조건 비 내리기(물의 양 1 증가)
			map[r][c]++;
			
			q.add(new Cloud(r, c));
			
		} 
		
	}

	private static int change(int n) {
		if(n < 0) {
			n += N;
		} else if(n > N-1) {
			n -= N;
		}
		
		return n;
		
	}

}
