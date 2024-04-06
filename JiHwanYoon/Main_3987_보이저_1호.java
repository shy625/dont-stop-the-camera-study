

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3987_보이저_1호 {
	static int N, M;
	static char[][] map;
	static int[] start;
	static char[] direction = {'U', 'R', 'D', 'L'};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] changeDir = {{ 1, 0, 3, 2}, // '/'을 만났을 때 각 방향에 대해 바뀐 방향
							  	{ 3, 2, 1, 0}}; // '\'을 만났을 때 각 방향에 대해 바뀐 방향
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		start = new int[2];
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken())-1;
		start[1] = Integer.parseInt(st.nextToken())-1;
		int max = 0; // 항성계 내부에 있는 시간의 최댓값
		int maxDir = 0; // 항성계 내부에 있는 시간이 최대일 때 방향
		for (int d = 0; d < 4; d++) {
			int distance = simul(d);
			if (distance > max) {
				max = distance;
				maxDir = d;				
			}
		}
		System.out.println(direction[maxDir]);
		// 무한히 순환 가능할 때 Voyager 출력
		System.out.println(max == Integer.MAX_VALUE ? "Voyager" : max);
	}
	// start에서 방향 d로 출발할 때 항성계 내부에 있는 시간을 반환하는 함수
	private static int simul(int d) {
		int[] cur = Arrays.copyOf(start, 2); // 현재 위치
		boolean[][][] visited = new boolean[N][M][4]; // (r, c)를 d 방향으로 방문한 적이 있는지에 대한 배열
		int time = 0; // 항상계 내부에 머문 시간
		while (check(cur[0], cur[1])) {
			// 같은 위치를 같은 방향으로 방문한 적이 있다면 무한히 순환하게 된다.
			if (visited[cur[0]][cur[1]][d]) {
				return Integer.MAX_VALUE;
			}
			visited[cur[0]][cur[1]][d] = true; 
			time++;
			// '/'을 만난 경우
			if (map[cur[0]][cur[1]] == '/') {
				d = changeDir[0][d];
			// '\'을 만난 경우
			} else if (map[cur[0]][cur[1]] == '\\') {
				d = changeDir[1][d];
			}
			cur[0] += dr[d];
			cur[1] += dc[d];
		}
		return time;
	}
	// (r, c)를 방문할 수 있는지를 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M && map[r][c] != 'C';
	}

}
