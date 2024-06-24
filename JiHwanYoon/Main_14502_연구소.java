

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
	static int N, M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int wall;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) wall++; // 벽의 개수를 미리 세어 둔다.
			}
		}
		combi(0, 0);
		System.out.println(N*M - min - wall - 3);
	}
	// 조합을 통해 빈 공간에 벽을 3개 세운 뒤 바이러스가 얼마나 퍼지는지 시뮬레이션해본다.
	private static void combi(int cnt, int start) {
		if (cnt == 3) {
			min = Math.min(min, test());
			return;
		}
		for (int i = start; i < N*M; i++) {
			if (map[i/M][i%M] == 0) {
				map[i/M][i%M] = 1;
				combi(cnt+1, i+1);
				map[i/M][i%M] = 0;
			}
		}
	}
	// 지도 내 모든 구역을 탐색하면서 바이러스를 발견하면 이 바이러스로 인해 감염될 수 있는 영역의 크기를 구한다.
	private static int test() {
		boolean[][] visited = new boolean[N][M];
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 2) {
					res += bfs(i, j, visited);
					// 만약 지금까지 구한 감염 가능 영역의 최솟값보다 감염 가능 영역의 크기가 더 큰 경우
					// 이 이상으로 시뮬레이션을 해도 의미가 없다.
					if (res >= min) return res;
				}
			}
		}
		return res;
	}
	// BFS를 통해 바이러스를 퍼뜨린 뒤 바이러스가 퍼진 영역의 크기를 구한다.
	private static int bfs(int i, int j, boolean[][] visited) {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int res = 1;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
					res++;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});					
				}
			}
		}
		return res;
	}
}
