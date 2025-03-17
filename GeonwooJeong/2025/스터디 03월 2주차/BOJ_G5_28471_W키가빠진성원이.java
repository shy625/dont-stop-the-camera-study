import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_28471_W키가빠진성원이 {
	static int N, ans;
	static char [][] map;
	static boolean [][] v;
	static int [] dr = {-1, -1, 0, 1, 1, 0, -1};
	static int [] dc = {0, 1, 1, 1, -1, -1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		v = new boolean[N][N];
		
		// 시작점 sr, sc
		int sr = -1;
		int sc = -1;
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				
				if(map[i][j] == 'F') {
					sr = i;
					sc = j;
				}
			}
		}
		
		// 도착지부터 갈 수 있는 모든 땅을 가본다.
		bfs(sr, sc);
		// 시작지는 포함하지 않기 때문에 ans-1 해준다.
		ans--;
		
		System.out.println(ans);

	}

	private static void bfs(int sr, int sc) {
		Queue<int []> q = new ArrayDeque<>();
		v[sr][sc] = true;
		q.add(new int[] {sr, sc});
		
		while(!q.isEmpty()) {
			ans++;
			
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			// 성원이는 위로 한 칸 갈 수 없지만, 도착지를 기준으로 생각하면 아랫칸으로 한 칸 갈 수 없다.
			// 나머지 7가지 방향으로 bfs를 돌려 갈 수 있는 모든 곳을 탐색한다.
			for (int d = 0; d < 7; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				
				if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == '#') continue;
				
				v[nr][nc] = true;
				q.add(new int[] {nr, nc});	
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
