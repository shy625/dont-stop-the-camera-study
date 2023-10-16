import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S2_5212_지구온난화 {
	
	static int R, C;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int maxR = Integer.MIN_VALUE;
		int minR = Integer.MAX_VALUE;
		int maxC = Integer.MIN_VALUE;
		int minC = Integer.MAX_VALUE;
		
		char [][] map = new char[R][C];
		List<int []> list = new ArrayList<>();
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '.') continue;
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					
					if(!check(nr, nc) || map[nr][nc] == '.') {
						cnt++;
					}
				}
				
				if(cnt < 3) {
					maxR = Math.max(maxR, i);
					minR = Math.min(minR, i);
					maxC = Math.max(maxC, j);
					minC = Math.min(minC, j);
					list.add(new int[] {i, j});
				}
				
			}
		}
		
		int newR = maxR - minR + 1;
		int newC = maxC - minC + 1;
		char [][] newMap = new char[newR][newC];
		
		for (int i = 0; i < newR; i++) {
			Arrays.fill(newMap[i], '.');
		}
		
		for(int [] arr : list) {
			newMap[arr[0]-minR][arr[1]-minC] = 'X';
		}
		
		for (int i = 0; i < newR; i++) {
			for (int j = 0; j < newC; j++) {
				System.out.print(newMap[i][j]);
			}
			System.out.println();
		}
		
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}
