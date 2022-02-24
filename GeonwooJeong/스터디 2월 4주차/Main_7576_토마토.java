import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	
	static class tomato {
		int r;
		int c;
		
		public tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int M, N; // 가로, 세로
	static int [][] map; // 토마토 상자
	static int [] dr = {-1, 0, 1, 0}; // 위오아왼
	static int [] dc = { 0, 1, 0,-1}; // 위오아왼
	static int max = -1; // 일수를 확인하기 위한 변수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(); // bfs 사용
		
		System.out.println(max);
		
	}

	private static void bfs() {
		Queue<tomato> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) { // map에 1이 있다면
					q.add(new tomato(i, j)); // 큐에 집어 넣어서 익히기 전파를 시작한다.
				}
			}
		}
		
		while(!q.isEmpty()) { // 큐가 빌 때까지 전파
			tomato to = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = to.r+dr[d];
				int nc = to.c+dc[d];
				
				if(!check(nr, nc)) continue; // 범위를 벗어날 경우 continue
				
				if(map[nr][nc] == 0) { // map[nr][nc]에 익지 않은 토마토가 있다면
					map[nr][nc] = map[to.r][to.c] + 1; // 며칠 째에 익었는지 확인하기 위한 로직
					q.add(new tomato(nr, nc)); // q에 다시 넣어준다.
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					max = -1;
					return ;
				}
				max = Math.max(max, map[i][j]); // 일수 계산 로직
			}
		}
			max -= 1;  // map에는 일수+1 되어있기 때문에 1을 빼준다.
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
