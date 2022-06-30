import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9207_페그솔리테어 {
	static char [][] map;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static int pinres, moveres;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			map = new char[5][9];
			int pin = 0;
			moveres = 0;
			for (int i = 0; i < 5; i++) {
				String str = br.readLine();
				for (int j = 0; j < 9; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'o') pin++;
				}
			}
			pinres = pin;
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 9; j++) {
					if(map[i][j] == 'o') dfs(i, j, pin, 0);
				}
			}
			
			if(t != T) {
				String tmp = br.readLine();
			}
			
			System.out.println(pinres+" "+moveres);
			
		}

	}

	private static void dfs(int r, int c, int pin, int cnt) {
		if(pin <= pinres) {
			pinres = pin;
			moveres = cnt;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr, nc)) continue;
			
			if(map[nr][nc] == 'o') {
				int nr2 = nr+dr[d];
				int nc2 = nc+dc[d];
				
				if(!check(nr2, nc2)) continue;
				
				if(map[nr2][nc2] == '.') {
					map[r][c] = '.';
					map[nr][nc] = '.';
					map[nr2][nc2] = 'o';
					
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 9; j++) {
							if(map[i][j] == 'o') dfs(i, j, pin-1, cnt+1);
						}
					}
					
					map[r][c] = 'o';
					map[nr][nc] = 'o';
					map[nr2][nc2] = '.';
				}
			}
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<9;
	}

}
