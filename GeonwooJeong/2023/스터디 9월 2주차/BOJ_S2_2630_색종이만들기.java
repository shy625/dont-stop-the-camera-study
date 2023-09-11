import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_2630_색종이만들기 {
	static int white, blue;
	static int [][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
		
	}

	private static void solve(int r, int c, int size) {
		int color = map[r][c];
		
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(color != map[i][j]) {
					solve(r, c, size/2);
					solve(r, c+(size/2), size/2);
					solve(r+(size/2), c, size/2);
					solve(r+(size/2), c+(size/2), size/2);
					return;
				}
			}
		}
		
		if(color == 0) {
			white++;
		} else {
			blue++;
		}
		
	}

}
