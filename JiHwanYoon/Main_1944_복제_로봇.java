
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1944_복제_로봇 {
	static int N, M;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		int[] start = new int[2];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'S') start = new int[] {i, j}; // 시작 지점을 따로 고려
			}
		}
		// 여기서 찾은 그래프는 완전 그래프이기 때문에, Kruskal's Algorithm을 사용하면 시간(메모리) 초과 발생
		// 따라서 Prim's Algorithm을 사용
		// Prim's Algorithm을 위한, 정점들을 넣을 우선 순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		// 시작 지점부터 시작
		pq.offer(new int[] {start[0], start[1], 0});
		// Prim's Algorithm에서 특정 정점을 방문했는지를 기록
		boolean[][] v = new boolean[N][N];
		// 현재 MST에 포함되어 있는 정점의 개수
		int vertexCount = 0;
		// MST의 가중치
		int cost = 0;
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (v[u[0]][u[1]]) continue;
			v[u[0]][u[1]] = true;
			cost += u[2];
			vertexCount++;
			bfs(u[0], u[1], pq, v); // bfs를 통해 현재까지 만든 MST에서 가장 인접한 정점을 찾는다.
			if (vertexCount == M+1) break;
		}
		System.out.println((vertexCount == M+1) ? cost : -1);

	}
	private static void bfs(int i, int j, PriorityQueue<int[]> pq, boolean[][] v) {
		Queue<int[]> q = new LinkedList<>();
		// bfs를 위한 방문 여부 체크
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		// 현재까지 이동한 거리를 기록
		int distance = 1;
		while (!q.isEmpty()) {
			// 같은 거리에 있는 좌표들을 동시에 고려
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc] != '1') {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
						// 열쇠의 위치를 찾으면 해당 좌표를 정점으로 우선 순위 큐에 추가
						if (map[nr][nc] == 'K' && !v[nr][nc]) {
							pq.offer(new int[] {nr, nc, distance});
						}
					}
				}
				qLen--;
			}
			distance++;
		}
	}

}
