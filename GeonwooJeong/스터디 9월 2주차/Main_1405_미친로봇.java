import java.util.Scanner;

public class Main_1405_미친로봇 {
	
	static int N;
	static double [] prob;
	// 가운데(15, 15)에서 시작하고, 한 방향으로 최대 14번 갈 수 있으므로 배열의 크기는 30x30이다.
	static boolean [][] v = new boolean[30][30];
	static double ans;
	static int [] dr = {0, 0, 1, -1}; // 동서남북
	static int [] dc = {1, -1, 0, 0};

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		N = scann.nextInt();
		prob = new double[4];
		
		for (int i = 0; i < 4; i++) {
			prob[i] = scann.nextInt() * 0.01;
		}
		
		// 15,15 에서 시작하고, 해당 지점을 visit 처리해준다.
		v[15][15] = true;
		dfs(15, 15, 1.0, 0);
		
		System.out.println(ans);

	}

	private static void dfs(int r, int c, double percent, int cnt) {
		if (cnt == N) {
			ans += percent;
			return;
		}
		
		// 동서남북 4방탐색
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			// 방문한 적이 있으면 단순하지 않고, 확률이 0이면 계산하지 않는다.
			if(!v[nr][nc] && prob[d] > 0) {
				v[nr][nc] = true;
				dfs(nr, nc, percent*prob[d], cnt+1);
				v[nr][nc] = false;
			}
		}
		
	}

}
