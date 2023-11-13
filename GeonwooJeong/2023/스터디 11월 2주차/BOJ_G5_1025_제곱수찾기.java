import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1025_제곱수찾기 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char [][] map = new char[N][M];
		int max = -1;
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				for (int dr = -N; dr < N; dr++) {
					for (int dc = -M; dc < M; dc++) {
						if(dr == 0 && dc == 0) continue;
						
						StringBuilder sb = new StringBuilder();
						int nr = r;
						int nc = c;
						
						while(check(nr, nc)) {
							sb.append(map[nr][nc]);
							
							int n1 = Integer.parseInt(sb.toString());
							int n2 = (int) Math.sqrt(n1);
							if(n2*n2 == n1) {
								max = Math.max(max, n1);
							}
							
							nr += dr;
							nc += dc;
							
						}
						
					}
				}
			}
		}
		
		System.out.println(max);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
