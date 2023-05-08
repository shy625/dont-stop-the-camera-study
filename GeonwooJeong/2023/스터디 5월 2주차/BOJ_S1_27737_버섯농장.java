import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_27737_버섯농장 {
	
	static int N, M, K;
	static int [][] map;
	static boolean [][] v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int origin = M;
		
		map = new int[N][N];
		v = new boolean[N][N];
		
		// 맵 정보 받아오기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// bfs로 확인하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int n = map[i][j];
				if(n == 0) {
					bfs(i, j);
				}
			}
		}
		
		// 버섯을 하나도 사용하지 않았을 경우에도 IMPOSSIBLE을 출력해야 한다.
		if(origin == M) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println("POSSIBLE");
			System.out.println(M);
		}
		
	}

	private static void bfs(int r, int c) {
		// 연결되있는 버섯이 자랄 수 있는 칸을 셀 변수 cnt
		int cnt = 0;
		v[r][c] = true;
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			cnt++;
			map[curR][curC] = 2;
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				if(!check(nr, nc)) continue;
				
				if(map[nr][nc] != 1 && !v[nr][nc]) {
					v[nr][nc] = true;
					q.add(new int[] {nr, nc});
				}
			}
		}
		
		// cnt가 K로 딱 나누어 떨어지면 버섯 포자를 cnt/K개 만큼 사용해야 하고,
		// 나누어 떨어지지 않는다면 1개를 추가로 더 사용해야 한다.
		
		if(cnt % K == 0) {
			M -= cnt/K;
		} else {
			M -= (cnt/K + 1);
		}
		
		// 위 과정에서 정해진 버섯 포자 개수를 초과하였을 때 IMPOSSIBLE 출력
		if(M < 0) {
			System.out.println("IMPOSSIBLE");
			System.exit(0);
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
