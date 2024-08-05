

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_31849_편세권 {
	static int N, M, R, C;
	static int[][] map;
	static int[][] minDistances;
	static int min;
	static Queue<int[]> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ArrayList<int[]> houses = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int p = Integer.parseInt(st.nextToken());
			map[a][b] = p;
			houses.add(new int[] {a, b});
		}
		// 편의점을 기준으로 너비 우선 탐색을 수행
		q = new LinkedList<>();
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			q.offer(new int[] {c, d});
		}
		min = Integer.MAX_VALUE;
		minDistances = new int[N][M]; // 각 지점에서의 편의점까지 최소 거리
		for (int i = 0; i < N; i++) {
			Arrays.fill(minDistances[i], Integer.MAX_VALUE);
		}
		int l = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && minDistances[nr][nc] > l) {
						minDistances[nr][nc] = l;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			l++;
		}
		// 편세권 점수의 최솟값을 구한다.
		for (int[] house : houses) {
			min = Math.min(min, map[house[0]][house[1]] * minDistances[house[0]][house[1]]);
		}
		System.out.println(min);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
