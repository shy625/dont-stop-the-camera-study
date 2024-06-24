

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_13460_구슬_탈출_2 {
	static char[][] map;
	static int N, M;
	static int[] hole; // 구멍의 위치
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new char[N][M];
		// 구슬의 위치
		int[] red = new int[2];
		int[] blue = new int[2];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') red = new int[] {i, j};
				if (map[i][j] == 'B') blue = new int[] {i, j};
				if (map[i][j] == 'O') hole = new int[] {i, j};
			}
		}
		System.out.println(bfs(red, blue));
	}
	// 처음 주어지는 구슬의 위치를 바탕으로 BFS를 실행해 어느 한 구슬이 구멍에 도달하거나 보드를 기울이는 횟수가 10회 초과면 종료
	private static int bfs(int[] red, int[] blue) {
		// BFS 큐에 빨간 구슬과 파란 구슬의 좌표를 모두 집어넣는다.
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {red[0], red[1], blue[0], blue[1]});
		// 빨간 구슬과 파란 구슬이 각각 특정 지점에 도달했는지를 기록하기 위해 4차원 배열을 만든다.
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[red[0]][red[1]][blue[0]][blue[1]] = true;
		// 보드를 기울인 횟수로 10회가 넘어가면 종료
		int cnt = 1;
		while (!q.isEmpty() && cnt <= 10) {
			// 기울인 횟수가 같은 경우에 대해서만 큐에서 꺼내 BFS를 실행한다.
			int qLen = q.size();
			while (qLen > 0) {
				int[] loc = q.poll();
				for (int d = 0; d < 4; d++) {
					int[] cur_red = new int[] {loc[0], loc[1]};
					int[] cur_blue = new int[] {loc[2], loc[3]};
					// 보드를 기울인다.
					int[] next_loc = moveMap(cur_red, cur_blue, d, visited);
					// 파란 구슬의 위치는 {next_loc[2], next_loc[3]}으로, moveMap에서 구멍에 빠지면 {-1, -1}이 되도록 설정함
					if (next_loc[2] == -1) continue;
					// 빨간 구슬의 위치는 {next_loc[0], next_loc[1]}으로, moveMap에서 구멍에 빠지면 {-1, -1}이 되도록 설정함
					else if (next_loc[0] == -1) return cnt;
					if (!visited[next_loc[0]][next_loc[1]][next_loc[2]][next_loc[3]]) {
						q.offer(next_loc);
					}
				}
				qLen -= 1;
			}
			cnt++;
		}
		// 불가능한 경우
		return -1;
		
		
	}
	// 보드를 기울였을 때 구슬의 위치를 찾는 함수
	private static int[] moveMap(int[] red, int[] blue, int d, boolean[][][][] visited) {
		// 깊은 복사
		int[] red_start = Arrays.copyOf(red, 2);
		int[] blue_start = Arrays.copyOf(blue, 2);
		// 구슬들을 구멍이나 벽을 만나기 전까지 굴린다. 단, 구슬끼리 겹치는 경우는 나중에 생각한다.
		while (0<=red[0]+dr[d] && red[0]+dr[d]<N && 0<=red[1]+dc[d] && red[1]+dc[d]<M && map[red[0]+dr[d]][red[1]+dc[d]] != '#') {
			red[0] += dr[d]; red[1] += dc[d];
			if (red[0] == hole[0] && red[1] == hole[1]) break;
		}
		while (0<=blue[0]+dr[d] && blue[0]+dr[d]<N && 0<=blue[1]+dc[d] && blue[1]+dc[d]<M && map[blue[0]+dr[d]][blue[1]+dc[d]] != '#') {
			blue[0] += dr[d]; blue[1] += dc[d];
			if (blue[0] == hole[0] && blue[1] == hole[1]) break;
		}
		// 각 구슬이 구멍에 들어가면 구슬의 좌표를 -1로 둬서 이 함수를 빠져나왔을 때 구슬이 구멍에 빠진지를 확인할 수 있게 한다.
		if (red[0] == hole[0] && red[1] == hole[1]) {
			Arrays.fill(red, -1);
		}
		if (blue[0] == hole[0] && blue[1] == hole[1]) {
			Arrays.fill(blue, -1);
		}
		// 두 구슬의 위치가 겹치는 경우
		// 구슬을 굴린 방향을 기준으로 어느 구슬이 앞에 있었는지 판단하고 뒤에 있었던 구슬의 위치를 한 칸 뒤로 이동시킨다.
		if (red[0] != -1 && blue[0] != -1 && red[0] == blue[0] && red[1] == blue[1]) {
			// 세로 방향으로 겹치는 경우
			if (red_start[0]*dr[d] > blue_start[0]*dr[d]) blue[0] -= dr[d];
			else red[0] -= dr[d];
			// 가로 방향으로 겹치는 경우
			if (red_start[1]*dc[d] > blue_start[1]*dc[d]) blue[1] -= dc[d];
			else red[1] -= dc[d];
		}
		return new int[] {red[0], red[1], blue[0], blue[1]};
	}

}
