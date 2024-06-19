import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_1012_유기농배추 {
	static int M, N;
	static int [][] map;
	static boolean [][] v;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			v = new boolean[N][M];
			int ans = 0;
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[b][a] = 1;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 배추가 심어져있고, 한 번도 방문하지 않은 땅이라면
					// 배추흰지렁이를 1개 사용하고 bfs를 돌린다.
					if(map[i][j] == 1 && !v[i][j]) {
						ans++;
						bfs(i, j);
					}
				}
			}
			
			sb.append(ans+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());

	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		v[r][c] = true;
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				if(!check(nr, nc)) continue;
				
				// 배추가 심어져있고, 아직 방문하지 않았다면 방문처리를 해준다.
				if(map[nr][nc] == 1 && !v[nr][nc]) {
					v[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
				
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
