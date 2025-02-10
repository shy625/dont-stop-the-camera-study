import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_14271_그리드게임 {
	static int K;
	static boolean [][] map;
	static Queue<int []> q;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 초기 N, M이 50까지이고 1500초까지 증식할 수 있다.
		// 기본 51에 -방향으로 1500, +방향으로 1500까지 갈 수 있으므로 51+1500+1500 해서 3051칸으로 초기화한다.
		map = new boolean[3051][3051];
		q = new ArrayDeque<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				
				
				if(c == 'o') {
					// 첫 그리드를 중앙에 놓기 위해 1500을 더한 숫자로 큐에 넣어준다.
					map[i+1500][j+1500] = true;
					q.add(new int[] {i+1500, j+1500});
				}
			}
		}
		
		K = Integer.parseInt(br.readLine());
		
		// 큐 사이즈가 0이면 증식을 하지 않는다.
		if(q.size() == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		// 너비우선탐색을 통해 그리드를 증식시킨다.
		bfs();
		
		int ans = 0;
		for (int i = 0; i < 3051; i++) {
			for (int j = 0; j < 3051; j++) {
				if(map[i][j]) ans++;
			}
		}
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		int cnt = 0;
		// 무한으로 증식할 수 있으므로 !q.empty() 대신에
		// K초까지 증식할 수 있으므로 cnt < K 조건을 걸어준다.
		while(cnt < K) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int [] cur = q.poll();
				int curR = cur[0];
				int curC = cur[1];
				
				for (int d = 0; d < 4; d++) {
					int nr = curR+dr[d];
					int nc = curC+dc[d];
					
					if(!map[nr][nc]) {
						map[nr][nc] = true;
						q.add(new int[] {nr, nc});
					}
					
				}
				
			}
			
			cnt++;
			
		}
		
	}

}
