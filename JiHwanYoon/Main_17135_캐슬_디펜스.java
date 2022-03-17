

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17135_캐슬_디펜스 {
	static int N, M, D;
	static int[][] map;
	static int[] archers; // 궁수가 위치한 열
	static int max; // 제거할 수 있는 적의 최대 수
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int num = 0; // 총 적의 수
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N+1][M];
		archers = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) num++; 
			}
		}
		max = 0;
		// 조합으로 궁수를 배치할 열을 찾는다.
		combi(0, 0);
		System.out.println(max);
	}
	private static void combi(int cnt, int start) {
		// 3명 모두 배치하면 시뮬레이션을 돌린다.
		if (cnt == 3) {
			max = Math.max(max, simulation());
			return;
		}
		for (int j = start; j < M; j++) {
			archers[cnt] = j;
			combi(cnt+1, j+1);
		}
	}
	private static int simulation() {
		// 각 시뮬레이션은 독립적이므로 map을 깊은 복사해준다.
		int[][] temp = new int[N+1][M];
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}
		// 남아있는 적의 수
		int remain = num;
		// 제거한 적의 수
		int cnt = 0;
		// 적이 남아있는 동안
		while (remain > 0) {
			// 각 궁수가 지정한 적의 위치
			int[][] toRemove = new int[3][2];
			for (int i = 0; i < 3; i++) {
				Arrays.fill(toRemove[i], -1);
			}
			// 각 궁수에 대해 가장 가까운 적을 찾는다.
			for (int i = 0; i < 3; i++) {
				toRemove[i] = findEnemy(i, temp);
			}
			// 각 궁수가 찾은 적을 잡는다.
			for (int i = 0; i < 3; i++) {
				int[] e = toRemove[i];
				if (e[0] == -1) continue;
				if (temp[e[0]][e[1]] == 1) {
					temp[e[0]][e[1]] = 0;
					cnt++; remain--;
				}
			}
			// 성으로 온 적은 게임에서 제외한다. 
			for (int j = 0; j < M; j++) {
				if (temp[N-1][j] == 1) {
					temp[N-1][j] = 0;
					remain--;
				}
			}
			// 성으로 온 적을 제외한 나머지 적은 한 칸 아래로 이동시킨다.
			for (int i = N-2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					temp[i+1][j] = temp[i][j];
					temp[i][j] = 0;
				}
			}
		}
		return cnt;
	}
	private static int[] findEnemy(int arc_idx, int[][] cur_map) {
		int[] toRemove = new int[] {-1, -1};
		int[] archer = new int[] {N, archers[arc_idx]};
		int minD = Integer.MAX_VALUE;
		// 가장 가까운 적이 여러 마리면 가장 왼쪽에 있는 적이 우선이므로, 왼쪽 열부터 오른쪽 열까지 순회하는 방식으로 진행한다.
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				if (cur_map[i][j] == 0) continue;
				// 적과 궁수 사이의 맨해튼 거리
				int distance = Math.abs(archer[0] - i) + Math.abs(archer[1] - j);
				// 거리가 D보다 작거나 같으면서 가장 가까운 적을 찾는다.
				if (minD > distance && distance <= D) {
					minD = Math.abs(archer[0] - i) + Math.abs(archer[1] - j);
					toRemove[0] = i;
					toRemove[1] = j;
				}
			}
		}
		return toRemove;
	}

}
