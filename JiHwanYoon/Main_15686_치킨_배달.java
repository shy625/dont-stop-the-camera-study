

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_치킨_배달 {
	static int N, M, chickenCnt;
	static int[][] map;
	static int[][] chickens; // 각 치킨집의 좌표 저장 배열
	static int[] selected; // 몇 번 치킨집이 선택됐는지를 나타냄
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		// chickens 배열을 만들기 위해 필요
		chickenCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chickenCnt++;
			}
		}
		chickens = new int[chickenCnt][2];
		int cur = 0;
		// 치킨집 좌표 기록
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) chickens[cur++] = new int[] {i, j};
			}
		}
		selected = new int[M];
		combi(0, 0);
		System.out.println(min);
		
	}
	// 조합을 이용해 남겨둘 치킨집 선택
	private static void combi(int cnt, int start) {
		// 치킨집 선택 완료하면 도시의 치킨 거리를 구한다.
		if (cnt == M) {
			int chicken_dist = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						int minDist = Integer.MAX_VALUE;
						for (int s : selected) {
							int[] chicken = chickens[s];
							minDist = Math.min(minDist, Math.abs(i - chicken[0]) + Math.abs(j - chicken[1]));
						}
						chicken_dist += minDist;
					}
				}
			}
			min = Math.min(min, chicken_dist);
			return;
		}
		for (int i = start; i < chickenCnt; i++) {
			selected[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

}
