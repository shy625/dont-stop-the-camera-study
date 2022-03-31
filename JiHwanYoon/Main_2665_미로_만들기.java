

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_2665_미로_만들기 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		char[] temp = new char[N];
		for (int i = 0; i < N; i++) {
			temp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = (temp[j] - '0') ^ 1; // 원활한 dijkstra's algorithm 적용을 위해 0은 1로, 1은 0으로 바꿔준다.
			}
		}
		// 노드는 map의 각 좌표들, 간선은 모든 인접한 좌표들 사이에 있다고 생각하며, 간선의 비용은 간선의 도착지가 검은 방인 경우 1, 흰 방인 경우 0으로 간주한다.
		// 그러면 dijkstra's algorithm와 BFS를 혼합 적용하면 dijkstra[N-1][N-1]은 (N-1, N-1)지점에 도착하기 위해 지나야 하는 검은 방의 개수의 최솟값이 된다.
		int[][] dijkstra = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dijkstra[i], Integer.MAX_VALUE);
		}
		dijkstra[0][0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		pq.offer(new int[] {0, 0, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == N-1 && u[1] == N-1) break;
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && dijkstra[nr][nc] > dijkstra[u[0]][u[1]] + map[nr][nc]) {
					visited[nr][nc] = true;
					dijkstra[nr][nc] = dijkstra[u[0]][u[1]] + map[nr][nc];
					pq.offer(new int[] {nr, nc, dijkstra[nr][nc]});
				}
			}
		}
		System.out.println(dijkstra[N-1][N-1]);
	}
}
