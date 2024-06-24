

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25565_딸기와_토마토 {
	static int N, M, K;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int cnt = 0; // 씨앗이 존재하는 칸의 개수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) cnt++;
			}
		}
		// 겹치는 칸이 없는 경우
		if (cnt == 2*K) {
			System.out.println(0);
			return;
		}
		// 겹치는 칸의 개수를 우선 출력
		System.out.println(2*K - cnt);
		// K가 1이고, 겹치는 칸이 있는 경우 해당 칸만 출력하면 된다.
		if (K == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						System.out.println((i+1)+" "+(j+1));
						return;
					}
				}
			}
		// 각각 가로와 세로 줄로 연속해서 심어서 한 칸만 겹치는 경우
		} else if (cnt == 2*K-1) {
			// 한 칸만 겹치는 경우는 가로와 세로 줄로 겹치는 경우도 있지만,
			// 가로와 가로 줄끼리, 세로와 세로 줄끼리도 한 칸만 겹칠 수 있기 때문에
			// 가로와 세로 줄이 겹치는 건지 확인한다.
			int[] arr = checkCross(); 
			if (arr[0] != -1) {
				System.out.println((arr[0]+1)+" "+(arr[1]+1));
				return;
			}
		}
		// 가로와 가로 줄끼리, 또는 세로와 세로 줄끼리 겹치는 경우
		// 여러 칸이 겹칠 수 있다.
		StringBuilder sb = new StringBuilder();
		int[] start = new int[] {-1, -1};
		// 각 가로 줄에 대해 씨앗이 연속으로 심어진 칸 중 가장 왼쪽에 있는 칸을 찾는다.
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j < M-1 && map[i][j] == 1 && map[i][j+1] == 1) {
					start = new int[] {i, j};
					break outer;
				}
			}
		}
		// 씨앗이 심어지기 시작하는 칸으로부터 (cnt - K)만큼 떨어져 있는 칸부터 
		// (K-1)만큼 떨어져 있는 칸까지 겹친다.
		if (start[0] != -1) {
			for (int j = cnt - K; j < K; j++) {
				sb.append((start[0]+1)+" "+(start[1]+j+1)).append("\n");
			}
			if (sb.length() > 0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return;
		}
		// 각 세로 줄에 대해 씨앗이 연속으로 심어진 칸 중 가장 위쪽에 있는 칸을 찾는다.
		outer: for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				if (i < N-1 && map[i][j] == 1 && map[i+1][j] == 1) {
					start = new int[] {i, j};
					break outer;
				}
			}
		}
		// 위와 동일한 과정을 거쳐 겹치는 칸을 구한다.
		if (start[0] != -1) {
			for (int i = cnt - K; i < K; i++) {
				sb.append((start[0]+i+1)+" "+(start[1]+1)).append("\n");
			}
			if (sb.length() > 0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
			return;
		}
	}
	// 가로와 세로 줄이 겹치는지 확인하는 함수
	private static int[] checkCross() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) continue;
				for (int d = 0; d < 4; d++) {
					int nr1 = i + dr[d];
					int nr2 = i + dr[(d+1)%4];
					int nc1 = j + dc[d];
					int nc2 = j + dc[(d+1)%4];
					if (check(nr1, nc1) && check(nr2, nc2) && map[nr1][nc1] == 1 && map[nr2][nc2] == 1) {
						return new int[] {i, j};
					}
				}
			}
		}
		return new int[] {-1, -1};
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
