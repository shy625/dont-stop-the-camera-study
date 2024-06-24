

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구_이동 {
	static int N, L, R;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 인구 이동이 발생한 일수
		int day = 0;
		while (true) {
			// 인구 이동이 일어나는 영역의 수
			int cnt = 0;
			// 각 칸이 어떤 인구 이동 영역에 해당하는지를 나타내는 배열
			int[][] openedTo = new int[N][N];
			// 초기값은 -1로 설정 - 아직 해당 칸을 방문하지 않았음을 의미하기도 함
			for (int i = 0; i < N; i++) {
				Arrays.fill(openedTo[i], -1);
			}
			// 각 영역에서 구한 인구의 평균값을 저장하는 ArrayList(실제 영역의 수를 바로 알 수 없어 ArrayList로 함)
			ArrayList<Integer> results = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 해당 칸을 방문하지 않았으면 그 칸을 중심으로 bfs를 수행하고, 이로부터 나온 해당 영역의 인구 평균 값을 반환받음
					if (openedTo[i][j] == -1) {
						int res = bfs(i, j, openedTo, cnt++);
						results.add(res);
					}
				}
			}
			// 만약 cnt가 N^2과 같다면 모든 칸이 다른 영역, 즉 인구 이동이 일어나지 않는다는 의미
			if (cnt == N*N) break;
			// 인구 이동이 완료된 후 상태를 map에 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = results.get(openedTo[i][j]);
				}
			}
			day++;
		}
		System.out.println(day);
	}
	// k : 현재 고려 중인 영역을 나타내는 수(0부터 시작)
	private static int bfs(int i, int j, int[][] openedTo, int k) {
		// 영역에 속한 칸의 인구 수 합
		int sum = map[i][j];
		// 영역에 속한 칸의 수
		int num = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		openedTo[i][j] = k;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<N && openedTo[nr][nc] == -1) {
					// 현재 위치와 인접한 위치 간의 인구 차가 L 이상 R 이하인 경우에만 인구 이동 영역에 포함시킴
					int diff = Math.abs(map[nr][nc] - map[u[0]][u[1]]);
					if (L<=diff && diff<=R) {
						openedTo[nr][nc] = k;
						sum += map[nr][nc];
						num++;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return sum/num;
	}

}
