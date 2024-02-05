import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_1303_전쟁전투 {
	static int N, M, W, B;
	static int [][] map;
	static boolean [][] v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 가로 N, 세로 M 주의
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				// 아군일 경우 1
				if(c == 'W') {
					map[i][j] = 1;
				// 적군일 경우 2
				} else if(c == 'B') {
					map[i][j] = 2;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 이미 체크한 병사가 아니라면
				if(!v[i][j]) {
					// 뭉친 병사의 수를 확인한다.
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(W+" "+B);
		
	}

	private static void bfs(int r, int c, int k) {
		// 뭉친 병사의 수를 기록하기 위한 변수 cnt(자신을 포함하기 때문에 1로 시작한다.)
		int cnt = 1;
		v[r][c] = true;
		Queue<int []> q = new ArrayDeque<int[]>();
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				if(!check(nr, nc)) continue;
				
				// nr, nc에 있는 병사가 현재 병사의 종류(k)와 같아야 한다.
				if(map[nr][nc] == k && !v[nr][nc]) {
					v[nr][nc] = true;
					// 뭉친 병사 수를 1명 더해준다.
					cnt++;
					q.add(new int[] {nr, nc});
				}
			}
			
		}
		
		// 아군일 경우 W에 더해준다.
		if(k == 1) {
			W += cnt*cnt;
		// 적군일 경우 B에 더해준다.
		} else if(k == 2) {
			B += cnt*cnt;
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
