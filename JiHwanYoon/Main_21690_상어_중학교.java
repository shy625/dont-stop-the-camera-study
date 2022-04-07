

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21690_상어_중학교 {
	static int N, M;
	static int[][] map;
	static PriorityQueue<BlockGroup> pq;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	// 블록 그룹 클래스
	static class BlockGroup implements Comparable<BlockGroup>{
		// 각 블록 그룹의 총 블록 개수, 무지개 블록 개수, 기준 블록의 행 번호, 열 번호
		int area, rainbows, r, c;
		public BlockGroup(int area, int rainbows, int r, int c) {
			this.area = area;
			this.rainbows = rainbows;
			this.r = r;
			this.c = c;
		}
		// 블록 그룹을 우선 순위에 따라 비교
		public int compareTo(BlockGroup bg) {
			return this.area == bg.area ? 
					this.rainbows == bg.rainbows ? 
						this.r == bg.r ? Integer.compare(bg.c, this.c)
						: Integer.compare(bg.r, this.r)
					: Integer.compare(bg.rainbows, this.rainbows)
				: Integer.compare(bg.area, this.area);
		}
		
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 블록 그룹을 우선 순위 큐에 넣어 제거할 블록 그룹을 빠르게 선택한다.
		pq = new PriorityQueue<>();
		int score = 0; // 점수
		while (true) {
			pq.clear(); // 블록 그룹들을 다시 조사하기 위해 우선 순위 큐 초기화
			if (!findBlockGroup()) break; // 블록 그룹들을 조사하고 각 블록 그룹의 총 블록 수가 모두 1이면 while 문을 빠져나간다.
			BlockGroup bg = pq.poll(); // 제거할 블록 그룹 선택
			score += removeBlockGroup(bg); // 블록 그룹 제거 후 점수 추가
			gravityToMap(); // 중력 작용
			rotate(); // 회전
			gravityToMap(); // 중력 작용
		}
		System.out.println(score);
	}
	// 블록 그룹 찾기
	private static boolean findBlockGroup() {
		boolean found = false;
		// BFS를 위해 visited 배열을 만들지만 무지개 블록의 경우 여러 번 방문 가능하므로 
		// 1번 ~ M번 블록 그룹에 대한 visited 배열을 분리해서 생각한다.
		boolean[][][] visited = new boolean[N][N][M+1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 방문하지 않은 블록 그룹을 발견하면 해당 블록 그룹에 대한 BFS를 수행해 조사한다. 
				if (map[i][j] > 0 && !visited[i][j][map[i][j]]) {
					int area = bfs(i, j, visited);
					// 블록 개수가 2 이상인 블록 그룹을 찾으면 게임을 계속 진행
					if (area >= 2) found = true;
				}
			}
		}
		return found;
	}
	private static int bfs(int i, int j, boolean[][][] visited) {
		int cnt = 1; // 총 블록 개수
		int rainbows = 0; // 무지개 블록 개수
		int color = map[i][j]; // 현재 블록의 색
		// BFS
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j][color] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc][color] && (map[nr][nc] == 0 || map[nr][nc] == color)) {
					// 무지개 블록 별도 고려
					if (map[nr][nc] == 0) {
						rainbows++;
					}
					cnt++;
					visited[nr][nc][color] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// BFS 결과로 나온 블록 그룹에 대한 정보를 pq에 넣는다.
		pq.offer(new BlockGroup(cnt, rainbows, i, j));
		return cnt;
	}
	// BFS 도중 map의 좌표를 벗어나지 않는지 조사하는 메서드
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	// 인자로 주어진 블록 그룹을 제거
	private static int removeBlockGroup(BlockGroup bg) {
		int area = bg.area;
		int i = bg.r;
		int j = bg.c;
		int color = map[i][j];
		map[i][j] = -2; // 제거한 블록은 map에 -2로 표시
		// BFS를 이용해 제거한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == color)) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					map[nr][nc] = -2;
				}
			}
		}
		return area*area; // 제거한 블록 그룹에 대한 점수를 반환
	}
	// map을 반시계 방향으로 90도 회전한다.
	private static void rotate() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[N-1-j][i] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(temp[i], N);
		}
		
	}
	// map에 중력을 작용시킨다.
	private static void gravityToMap() {
		// 각 열에 대해 아래쪽 행부터 중력을 작용시킨다.
		for (int j = 0; j < N; j++) {
			for (int i = N-1; i >= 0; i--) {
				// 검은색 블록은 중력이 작용하지 않는다.
				if (map[i][j] == -1) continue;
				int temp = map[i][j]; 
				int temp_i = i+1; // 중력에 의해 어느 행까지 내려갈 수 있는지를 나타내는 변수
				// map에서 빈 공간(-2)이 나오는 동안에는 아래로 떨어진다.
				while (temp_i < N && map[temp_i][j] == -2) {
					temp_i++;
				}
				// 위 while 문에 의해 temp_i는 N이거나 map[temp_i][j]가 -2가 아닌 첫 블록이므로 
				// 1을 빼줘야 이동하고자 하는 공간이 나온다.
				temp_i--;
				// swap을 활용해 블록을 이동시킨다.
				map[i][j] = -2;
				map[temp_i][j] = temp;
			}
		}
		
	}

}
