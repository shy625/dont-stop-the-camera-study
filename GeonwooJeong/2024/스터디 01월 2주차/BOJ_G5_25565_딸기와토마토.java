import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_25565_딸기와토마토 {
	static int N, M;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		
		int [][] map = new int[N][M];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		
	
		// 겹치는 곳이 하나도 없는 경우
		if(2*K == cnt) {
			System.out.println(0);
			System.exit(0);
		// K가 1인데 겹치는 곳이 존재하는 경우, 씨앗이 심어진 곳을 찾아 출력한다. (단 한 군데)
		} else if(K == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 1) {
						System.out.println(1);
						System.out.println((i+1)+" "+(j+1));
						System.exit(0);
					}
				}
			}
		// cnt가 2*K-1인 경우. 즉, 겹치는 곳이 단 한 곳인 경우
		} else if(cnt == 2*K-1) {
			// 가로와 세로가 겹치는지 확인해본다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0) continue;
					for (int d = 0; d < 4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						int nr2 = i+dr[(d+1)%4];
						int nc2 = j+dc[(d+1)%4];
						
						if(!check(nr, nc)) continue;
						if(!check(nr2, nc2)) continue;
						
						if(map[nr][nc] == 1 && map[nr2][nc2] == 1) {
							System.out.println(1);
							System.out.println((i+1)+" "+(j+1));
							System.exit(0);
						}
					}
				}
			}
		}
		// 그 외에는 딸기와 토마토가 가로+가로 로 겹치거나, 세로+세로 로 겹치는 경우이므로
		// 둘 중 어느 케이스인지 확인하고, 겹친 갯수만큼 겹친 부분을 출력한다.
		// sr, sc : 겹치기 시작한 부분, er, ec : 겹치기 끝난 부분
		int sr = 2001;
		int sc = 2001;
		int er = -1;
		int ec = -1;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 1) {
					sr = Math.min(r, sr);
					sc = Math.min(c, sc);
					er = Math.max(r, er);
					ec = Math.max(c, ec);
				}
			}
		}
		
		System.out.println(2*K - cnt);
		
		// 세로로 겹치는 경우
		if(sr == er) {
			// 세로로 겹친 부분을 모두 출력한다.
			for (int i = 0; i < 2*K - cnt; i++) {
				sb.append(sr+1).append(" ").append(sc+cnt-K+i+1).append("\n");
			}
		// 가로로 겹치는 경우
		} else if(sc == ec) {
			// 가로로 겹친 부분을 모두 출력한다.
			for (int i = 0; i < 2*K-cnt; i++) {
				sb.append(sr+cnt-K+i+1).append(" ").append(sc+1).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
