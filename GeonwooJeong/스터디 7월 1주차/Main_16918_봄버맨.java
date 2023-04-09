import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16918_봄버맨 {
	
	static int R, C, N;
	static char [][] map;
	static int [][] time; // 폭탄 터지기까지 남은 시간
	static int t; // 경과 시간
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		time = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'O') {
					q.add(new int[]{i, j});
					time[i][j] = 3;
				}
			}
		}
		
		// N초까지 반복 진행
		while(t++ < N) {
			// 짝수일 경우 폭탄 설치
			if(t%2 == 0) {
				insert();
			// 홀수일 경우 폭탄 터트리기
			} else {
				bfs();
			}
		}
		
		// 결과 출력
		print();
	}

	// 빈 공간에 폭탄을 설치하는 함수
	private static void insert() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '.') {
					map[i][j] = 'O';
					time[i][j] = t+3;
				}
			}
		}
	}

	// 폭탄을 터트리는 함수
	private static void bfs() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if(time[r][c] == t) {
					// 현재 폭탄이 있는 위치 폭파
					map[r][c] = '.';
					
					// 폭탄의 4방향 폭파
					for (int d = 0; d < 4; d++) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						
						if(check(nr, nc) && map[nr][nc] == 'O' && time[nr][nc] != t) {
							time[nr][nc] = 0;
							map[nr][nc] = '.';
						}
					}
				}
			}
		}
		
	}

	private static boolean check(int r, int c) {	
		return r>=0 && r<R && c>=0 && c<C;
	}

	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}

}
