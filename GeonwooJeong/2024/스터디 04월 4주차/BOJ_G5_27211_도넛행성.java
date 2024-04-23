import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_27211_도넛행성 {
	static int [][] map;
	static int N, M, ans;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 도넛 행성을 저장할 2차원 배열
		// 0: 빈곳, 1:숲, 2:이미 방문한 곳
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 아직 방문하지 않은 곳이라면 bfs를 돌리고, ans++ 해준다.
				if(map[i][j] == 0) {
					ans++;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans);
		
	}

	private static void bfs(int sr, int sc) {
		Queue<int []> q = new ArrayDeque<int[]>();
		q.add(new int[] {sr, sc});
		// 방문처리는 2로 바꿔주는 것으로 한다.
		map[sr][sc] = 2;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				// nr이 범위를 벗어나면 반대편으로 가게 한다.
				if(nr >= N) nr = 0;
				else if(nr < 0) nr = N-1;
				
				// nc가 범위를 벗어나면 반대편으로 가게 한다.
				if(nc >= M) nc = 0;
				else if(nc < 0) nc = M-1;
				
				// nr, nc가 비어있는 곳이라면 방문처리를 해주고 q에 넣어준다.
				if(map[nr][nc] == 0) {
					map[nr][nc] = 2;
					q.add(new int[] {nr, nc});
				}
				
			}
		}
		
	}

}
