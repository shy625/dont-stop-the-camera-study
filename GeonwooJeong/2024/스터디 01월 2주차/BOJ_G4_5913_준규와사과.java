import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_5913_준규와사과 {
	static int ans, er, ec, K;
	static int [][] map;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	// 돌 -1, 준규 1, 해빈 2
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[5][5];
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			
			map[r][c] = -1;
		}
		
		map[0][0] = 1;
		moveJ(0, 0, 0);
		
		System.out.println(ans);
		
	}

	private static void moveJ(int r, int c, int cnt) {
		if(cnt == (25-K)/2) {
			er = r;
			ec = c;
			map[4][4] = 2;
			moveH(4, 4, 0);
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr, nc) || map[nr][nc] != 0) continue;
			
			map[nr][nc] = 1;
			cnt++;
			moveJ(nr, nc, cnt);
			cnt--;
			map[nr][nc] = 0;
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<5;
	}

	private static void moveH(int r, int c, int cnt) {
		if(cnt == (25-K)/2 - 1) {
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr, nc) || map[nr][nc] != 1) continue;
				
				if(nr == er && nc == ec) {
					ans++;
					break;
				}
			}
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr, nc) || map[nr][nc] != 0) continue;
			
			map[nr][nc] = 2;
			cnt++;
			moveH(nr, nc, cnt);
			cnt--;
			map[nr][nc] = 0;
		}
	
		
	}

}
