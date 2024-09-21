

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_30508_고인물이싫어 {
	static int N, M, h, w;
	static int[][] map;
	static int[][] sum;
	static boolean[][] visited;
	static Queue<int[]> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		sum = new int[N+1][M+1]; // 횡단보도에 발을 딛을 수 있는지 확인할 때 누적합을 활용하기 위한 배열
		// BFS를 위해 사전 변수 정의
		visited = new boolean[N+1][M+1]; 
		q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (sum[r][c] == 0) {				
				sum[r][c] = 1;
				q.offer(new int[] {r, c});
				visited[r][c] = true;
			}
		}
		// BFS를 이용해 물이 빠진 칸을 sum 배열에 1로 표시
		bfs();
		// 누적합을 활용해, (r, c)칸에 대해서 (1, 1)부터 (r, c)까지 r*c 영역 중 물이 빠진 칸의 개수를 구한다.
		// 그리고 sum[r][c] - sum[r-h][c] - sum[r][c-w] + sum[r-h][c-w]을 통해,
		// (r-h+1, c-w+1)부터 (r, c)까지 h*w 영역 중 물이 빠진 칸의 개수를 구했을 때,
		// 그 갯수가 h*w와 같으면 그 영역에 발을 딛을 수 있다.
		for (int r = 1; r <= N; r++) {
			for (int c = 2; c <= M; c++) {
				sum[r][c] += sum[r][c-1];
			}
		}
		for (int c = 1; c <= M; c++) {
			for (int r = 2; r <= N; r++) {
				sum[r][c] += sum[r-1][c];
			}
		}
		int cnt = 0;
		for (int r = h; r <= N; r++) {
			for (int c = w; c <= M; c++) {
				if (sum[r][c] - sum[r-h][c] - sum[r][c-w] + sum[r-h][c-w] == h*w) cnt++;
			}
		}
		System.out.println(cnt);
	}
	// BFS를 통해 물이 빠진 칸을 구한다.
	private static void bfs() {
		while (q.size() > 0) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] >= map[u[0]][u[1]]) {
					sum[nr][nc] = 1;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 1<=r && r<=N && 1<=c && c<=M;
	}

}
