import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_1926_그림 {
	static int N, M;
	static int [][] map;
	static boolean [][] v;
	static int max;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 탐색하지 않는 구역에 대해 bfs를 진행한다.
				if(map[i][j] == 1 && !v[i][j]) {
					bfs(i, j);
					// 그림의 개수를 추가한다.
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(max);

	}

	private static void bfs(int i, int j) {
		Queue<int []> q = new ArrayDeque<int[]>();
		v[i][j] = true;
		q.add(new int[] {i, j});
		// 그림의 넓이는 1로 시작한다.
		int size = 1;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				if(!check(nr, nc)) continue;
				
				if(!v[nr][nc] && map[nr][nc] == 1) {
					// 그림의 넓이를 넓힌다.
					size++;
					map[nr][nc] = 0;
					v[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
			}
		}
		
		// 해당 구역의 넓이를 모두 확인했다면, 최대 넓이를 갱신한다.
		max = Math.max(max, size);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
