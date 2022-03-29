

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇_청소기 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 청소한 칸의 수
		int cnt = 0;
		while (true) {
			// 청소 안 한 칸이면 청소한다.
			if (map[x][y] == 0) {
				map[x][y] = -1;
				cnt++;
			}
			// 청소 안 한 칸이 청소기 근방에 있는지를 나타내는 변수
			boolean cleaned = false;
			int dir = 0;
			// 조건에 따라 이동할 지점을 찾는다.
			for (int i = 0; i < 4; i++) {
				dir = (d-i+3)%4;
				if (map[x+dr[dir]][y+dc[dir]] == 0) {
					d = dir;
					x += dr[d];
					y += dc[d];
					cleaned = true;
					break;
				} 
			}
			d = dir;
			// 청소 안 한 칸이 청소기 근방에 있을 때
			if (cleaned) continue;
			// 청소 안 한 칸이 청소기 근방에 없으면 뒤로 후진하는데, 만약 후진이 가능하면 후진을 하고, 
			// 벽으로 막혀 있으면 청소기 작동을 중지한다.
			if (map[x-dr[d]][y-dc[d]] != 1) {
				x -= dr[d];
				y -= dc[d];
			} else {
				break;
			}
		}
		System.out.println(cnt);
	}

}
