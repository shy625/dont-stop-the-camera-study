import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_15558_점프게임 {
	static int N, K;
	static int [][] map;
	static boolean [][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[2][N+1];
		v = new boolean[2][N+1];
		
		for (int i = 0; i < 2; i++) {
			String str = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = str.charAt(j-1) - '0';
			}
		}
		
		System.out.println(bfs());
		
	}

	private static int bfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {0, 1});
		v[0][1] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for (int i = 0; i < size; i++) {
				int [] cur = q.poll();
				
				int curR = cur[0];
				int curC = cur[1];
				int [] dr = {curR, curR, (curR+1)%2};
				int [] dc = {curC+1, curC-1, curC+K};
				
				for (int d = 0; d < 3; d++) {
					int nr = dr[d];
					int nc = dc[d];
					
					if(nc > N) return 1;
					
					if(nc <= 0 || nc == time) continue;
					
					if(!v[nr][nc] && map[nr][nc] == 1) {
						v[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
				}
				
			}
			
			v[0][time] = true;
			v[1][time] = true;
		}
		
		return 0;
	}

}
