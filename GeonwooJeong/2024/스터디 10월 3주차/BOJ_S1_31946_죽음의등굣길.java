import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_31946_죽음의등굣길 {
	static int N, M, X;
	static int [][] map;
	static boolean [][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N][M];
		v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		X = Integer.parseInt(br.readLine());
		
		bfs();
		
		System.out.println("DEAD");
		
	}

	private static void bfs() {
		Queue<int []> q = new ArrayDeque<>();
		q.add(new int[] {0, 0});
		v[0][0] = true;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int i = 1; i <= X; i++) {
				// dr을 먼저 고정한다.
				for (int dr = -i; dr <= i; dr++) {
					// dc가 -값일 때, +값일 때 총 2가지 경우
					for (int j = -1; j <= 1; j+=2) {
						// 거리 i를 맞추기 위한 dc값을 계산한다.
						int dc = (i - Math.abs(dr))*j;
						
						// 이후의 과정은 일반적인 bfs와 동일
						int nr = curR+dr;
						int nc = curC+dc;
						
						if(!check(nr, nc)) continue;
						
						if(v[nr][nc] || map[0][0] != map[nr][nc]) continue;
						
						if(nr == N-1 && nc == M-1) {
							System.out.println("ALIVE");
							System.exit(0);
						}
						
						v[nr][nc] = true;
						q.add(new int[] {nr, nc});
						
					}
				}
			}
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
