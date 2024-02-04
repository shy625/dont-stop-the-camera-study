

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20208_진우의_민트초코우유 {
	static int N, M, H, K;
	static ArrayList<int[]> locations;
	static boolean[] visited;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		locations = new ArrayList<>(); // 집의 위치와 민트초코우유의 위치를 저장하는 ArrayList
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) locations.add(new int[] {i, j});
				else if (map[i][j] == 1) locations.add(0, new int[] {i, j});
			}
		}
		// 브루트포스 알고리즘을 이용해 먹을 수 있는 민트초코우유 개수의 최댓값을 구한다.
		K = locations.size();
		visited = new boolean[K];
		max = 0;
		dfs(0, M, 0);
		System.out.println(max);
	}
	// 브루트포스 알고리즘을 이용해 먹을 수 있는 민트초코우유 개수의 최댓값을 구하는 함수
	// idx는 현재 위치한 지점을 의미하고, hp는 남은 체력, cnt는 지금까지 먹은 민트초코우유의 개수를 의미한다.
	private static void dfs(int idx, int hp, int cnt) {
		// 집으로 무사히 돌아온 경우
		if (idx == 0 && cnt > 0) {
			max = Math.max(max, cnt-1); // 집은 민트초코우유 개수를 셀 때 제외한다.
			return;
		}
		int[] start = locations.get(idx); // 출발지 위치
		for (int i = 0; i < K; i++) {
			if (i == idx || visited[i]) continue;
			int[] end = locations.get(i); // 목적지 위치
			int decHp = Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]); // 감소한 체력
			if (decHp > hp) continue; // 목적지까지 못 가는 경우
			visited[i] = true;
			dfs(i, hp - decHp + H, cnt+1);
			visited[i] = false;
		}
		
	}	

}
