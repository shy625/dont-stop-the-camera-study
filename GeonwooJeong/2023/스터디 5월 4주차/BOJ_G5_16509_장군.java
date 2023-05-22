import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G5_16509_장군 {
	
	// 움직이는 경로에 왕이 없는 경우를 제외한 모든 경우 bfs
	static int R2, C2, ans;
	static int [] dr1 = {-1, 0, 1, 0};
	static int [] dc1 = {0, -1, 0, 1};
	static int [] dr2 = {-1, -1, -1, 1, 1, 1, 1, -1};
	static int [] dc2 = {1, -1, -1, -1, -1, 1, 1, 1};
	static boolean [][] v = new boolean[10][9];

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int R1 = scann.nextInt();
		int C1 = scann.nextInt();
		
		R2 = scann.nextInt();
		C2 = scann.nextInt();
		
		ans = -1;
		
		bfs(R1, C1);
		
		System.out.println(ans);
		
	}

	private static void bfs(int r1, int c1) {
		v[r1][c1] = true;
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {r1, c1, 0});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			int cnt = cur[2];
			
			for (int d = 0; d < 8; d++) {
				int nr = curR+dr1[d/2];
				int nc = curC+dc1[d/2];

				if(!check1(nr, nc)) continue;
				if(check2(nr, nc)) continue;
				
				int nr2 = nr+dr2[d];
				int nc2 = nc+dc2[d];

				if(!check1(nr2, nc2)) continue;
				if(check2(nr2, nc2)) continue;
				
				int nr3 = nr2+dr2[d];
				int nc3 = nc2+dc2[d];
				
				if(!check1(nr3, nc3)) continue;
				if(check2(nr3, nc3)) {
					ans = cnt+1;
					return;
				}
				
				if(!v[nr3][nc3]) {
					v[nr3][nc3] = true;
					q.add(new int[] {nr3, nc3, cnt+1});
				}
			}
			
		}
		
	}
	
	// 맵을 나갔는지 확인
	private static boolean check1(int r, int c) {
		return r>=0 && r<10 && c>=0 && c<9;
	}
	
	// 왕이 있는 자리인지 확인
	private static boolean check2(int r, int c) {
		return r == R2 && c == C2;
	}

}
