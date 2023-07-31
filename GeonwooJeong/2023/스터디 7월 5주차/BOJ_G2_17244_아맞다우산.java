import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L, sr, sc, er, ec, ans;
	static int [][] map;
	static boolean [][][] v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 비트마스킹을 사용하기 위해 int 배열로 맵을 구성
		map = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				// 벽일 경우 9로 저장
				if(c == '#') {
					map[i][j] = 9;
				// 시작 지점을 따로 저장하고 -1로 저장
				} else if(c == 'S') {
					sr = i;
					sc = j;
					map[i][j] = -1;
				// 도착 지점을 따로 저장하고 -1로 저장
				} else if(c == 'E') {
					er = i;
					ec = j;
					map[i][j] = -1;
				// 물건일 경우 0, 1, 2... 순서로 맵에 저장
				} else if(c == 'X') {
					map[i][j] = L++;
				// 이동 가능한 경우 -1로 저장
				} else {
					map[i][j] = -1;
				}
			}
		}
		
		// 비트마스킹을 사용하기 위해 visit 배열을 3차원으로 저장(마지막 공간은 비트마스킹)
		v = new boolean[M][N][1 << L];
		
		bfs();
		
		System.out.println(ans);
		
	}

	private static void bfs() {
		Queue<int []> q = new LinkedList<>();
		// r, c, v, cnt 순서로 int 배열 구성
		q.add(new int[] {sr, sc, 0, 0});
		v[sr][sc][0] = true;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				// 맵 밖을 벗어나거나, 벽인 경우에는 넘긴다.
				if(!check(nr, nc) || map[nr][nc] == 9) continue;
				
				// 도착 지점에 도착했고, 필요한 물건을 모두 가지고 있는 경우가 정답이 된다.
				if(nr == er && nc == ec && cur[2] == (1 << L)-1) {
					ans = cur[3]+1;
					return;
				}
				
				// 물건 x개를 가지고 해당 지점을 방문하지 않은 경우
				if(!v[nr][nc][cur[2]]) {
					// 해당 지점에 물건이 없는 경우
					if(map[nr][nc] == -1) {
						// 단순히 방문 처리만 해주고 q에 다시 집어넣는다.
						v[nr][nc][cur[2]] = true;
						q.add(new int[] {nr, nc, cur[2], cur[3]+1});
					// 해당 지점에 물건이 있는 경우
					} else if(map[nr][nc] >= 0) {
						// 몇 번쨰 물건인지 확인한다.
						int stuff = map[nr][nc];
						// 비트마스킹으로 stuff번째 물건도 획득했음을 처리한다.
						v[nr][nc][cur[2] | (1 << stuff)] = true;
						// 비트마스킹 처리를 해주고 q에 다시 집어넣는다.
						q.add(new int[] {nr, nc, (cur[2] | (1 << stuff)), cur[3]+1});
					}
					
				}
			}
		}
		
	}

	private static boolean check(int r, int c) {
		return r>= 0 && r<M && c>=0 && c<N;
	}

}
