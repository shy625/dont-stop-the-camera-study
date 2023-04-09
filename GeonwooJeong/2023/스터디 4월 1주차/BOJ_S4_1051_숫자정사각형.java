import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_1051_숫자정사각형 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int [][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int max = Math.min(N, M);
		
		while(max > 1) {
			for (int r = 0; r < N-max+1; r++) {
				for (int c = 0; c < M-max+1; c++) {
					int n = map[r][c];
					
					if(map[r][c+max-1] == n && map[r+max-1][c] == n && map[r+max-1][c+max-1] == n) {
						System.out.println(max*max);
						System.exit(0);
					}
				}
			}
			
			max--;
		}
		
		System.out.println(1);

	}

}
