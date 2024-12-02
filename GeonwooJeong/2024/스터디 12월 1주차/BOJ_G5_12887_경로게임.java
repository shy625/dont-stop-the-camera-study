import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_12887_경로게임 {
	static int M;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());
		int [][] map = new int[2][M];
		// 하얀 칸의 총 개수
		int total = 0;
		
		for (int i = 0; i < 2; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				if(c == '#') map[i][j] = -1;
				else total++;
			}
		}
		
		// 최단 경로중 최솟값을 저장할 변수
		int min = Integer.MAX_VALUE;
		
		// 두 개의 시작점을 모두 확인해본다.
		for (int i = 0; i < 2; i++) {
			// 시작 칸이 검은색이라면 넘어간다.
			if(map[i][0] == -1) continue;
			
			Queue<int []> q = new ArrayDeque<>();
			boolean [][] v = new boolean[2][M];
			q.add(new int[] {i, 0});
			v[i][0] = true;
			
			int cnt = 1;
			
			outer:
				while(!q.isEmpty()) {
					int size = q.size();
					for (int j = 0; j < size; j++) {
						int [] cur = q.poll();
						int curR = cur[0];
						int curC = cur[1];
						if(curC == M-1) break outer;
						
						for (int d = 0; d < 4; d++) {
							int nr = curR + dr[d];
							int nc = curC + dc[d];
							
							if(!check(nr, nc) || v[nr][nc] || map[nr][nc] == -1) continue;
							
							v[nr][nc] = true;
							q.add(new int[] {nr, nc});
						}
					}
					
					cnt++;
					
				}
			
			min = Math.min(min, cnt);
			
		}
		
		// 최단거리를 제외한 나머지 하얀칸은 바꿔도 되기 때문에 전체-최단거리를 출력한다.
		System.out.println(total-min);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<2 && c>=0 && c<M;
	}

}
