import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_30508_고인물이싫어 {
	static int N, M;
	static int [][] map;
	static boolean [][] able;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static Queue<int []> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		// 해당 칸을 밟을 수 있는지 여부를 저장할 2차원 배열
		able = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q = new ArrayDeque<>();
		int K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			
			able[r][c] = true;
			q.add(new int[] {r, c});
		}
		
		// 하수구들부터 bfs를 사용하여 물이 빠지는 공간을 찾는다.
		bfs();
		
		int ans = 0;
		
		// N*M 칸을 발의 크기만큼 확인하여 디딜 수 있는 공간의 개수를 찾는다.
		for (int i = 0; i <= N - H; i++) {
			for (int j = 0; j <= M - W; j++) {
				boolean check = true;
				for (int l = 0; l < H; l++) {
					for (int k = 0; k < W; k++) {
						if (!able[i+l][j+k]) {
							check = false;
							break;
						}
					}
					if (!check) {
						break;
					}
				}
				if (check) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);

	}

	private static void bfs() {
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr, nc) || able[nr][nc]) continue;
				
				// 현재 칸의 높이가 더 높으면 물이 빠질 수 없다.
				if(map[r][c] > map[nr][nc]) continue;
				
				able[nr][nc] = true;
				q.add(new int[] {nr, nc});
				
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
