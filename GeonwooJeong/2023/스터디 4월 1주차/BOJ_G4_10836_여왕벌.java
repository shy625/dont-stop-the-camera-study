import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_10836_여왕벌 {
	
	static int M;
	static int [][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken()); 
		map = new int[M][M];
		
		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], 1);
		}
		
		for (int i = 0; i < day; i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			grow(zero, one, two);		
		}
		
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				map[i][j] = Math.max(map[i-1][j], Math.max(map[i][j-1], map[i-1][j-1]));
			}
		}
		
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < M; k++) {
				System.out.print(map[j][k]+" ");
			}
			System.out.println();
		}

	}

	private static void grow(int zero, int one, int two) {
		int r = M-1;
		int c = 0;
		
		while(zero > 0) {
			growing(r, c, 0);
			if(r != 0) r--;
			else c++;
			
			zero--;
		}
		
		while(one > 0) {
			growing(r, c, 1);
			if(r != 0) r--;
			else c++;
			
			one--;
		}
		
		while(two > 0) {
			growing(r, c, 2);
			if(r != 0) r--;
			else c++;
			
			two--;
		}
		
	}

	private static void growing(int r, int c, int num) {
		map[r][c] += num;
	}

}
