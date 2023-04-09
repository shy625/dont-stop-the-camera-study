import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_3709_레이저빔은어디로 {
	
	static int N, ansR, ansC;
	static int [][] map;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			st = new StringTokenizer(br.readLine());
			int lr = Integer.parseInt(st.nextToken());
			int lc = Integer.parseInt(st.nextToken());
			
			// 아래쪽 방향
			if(lr == 0) {
				lr = 1;
				dfs(lr, lc, 2, 0);
			// 오른쪽 방향
			} else if(lc == 0) {
				lc = 1;
				dfs(lr, lc, 1, 0);
			// 위쪽 방향	
			} else if(lr == N+1) {
				lr = N;
				dfs(lr, lc, 0, 0);
			// 왼쪽 방향
			} else if(lc == N+1) {
				lc = N;
				dfs(lr, lc, 3, 0);
			}
			
			System.out.println(ansR+" "+ansC);
			
		}

	}

	private static void dfs(int r, int c, int d, int cnt) {
		if(cnt > N*N) {
			ansR = 0;
			ansC = 0;
			return;
		}
		
		// 탈출 조건
		if(!check(r, c)) {
			ansR = r;
			ansC = c;
		// 우향우 거울을 만났을 때
		} else if(map[r][c] == 1) {
			d = (d+1)%4;
			dfs(r+dr[d], c+dc[d], d, cnt+1);
		// 앞으로 직진
		} else {
			dfs(r+dr[d], c+dc[d], d, cnt);
		}
		
	}

	private static boolean check(int r, int c) {
		return r >= 1 && r < N+1 && c >= 1 && c < N+1;
	}

}
