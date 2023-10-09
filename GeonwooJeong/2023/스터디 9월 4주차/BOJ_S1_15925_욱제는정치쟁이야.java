import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_15925_욱제는정치쟁이야 {
	static int N, power;
	static int [][] map;
	static boolean [][] complete;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		power = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		complete = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int l = 0; l < 2; l++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(complete[i][j]) continue;
					
					checkVertical(j);
					checkHorizontal(i);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!complete[i][j]) {
					System.out.println(0);
					System.exit(0);
				}
			}
		}
		
		System.out.println(1);
		
	}

	private static void checkVertical(int c) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(map[i][c] == power) cnt++;
		}
		
		if(cnt > N/2) {
			turnVertical(c);
		}
		
	}

	private static void turnVertical(int c) {
		for (int i = 0; i < N; i++) {
			map[i][c] = power;
			complete[i][c] = true;
		}
		
	}
	
	private static void checkHorizontal(int r) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if(map[r][i] == power) cnt++;
		}
		
		if(cnt > N/2) {
			turnHorizontal(r);
		}
		
	}

	private static void turnHorizontal(int r) {
		for (int i = 0; i < N; i++) {
			map[r][i] = power;
			complete[r][i] = true;
		}
		
	}

}
