

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17265_나의_인생에는_수학과_함께 {
	// 현재 연산 결과 및 최근에 만난 연산자를 저장하는 클래스
	static class State {
		int r, c, result; char oper;
		public State(int r, int c, int result, char oper) {
			this.r = r;
			this.c = c;
			this.result = result;
			this.oper = oper;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public void setOper(char oper) {
			this.oper = oper;
		}
	}
	static int N;
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		// (0, 0)에서 (N-1, N-1)까지 가는 모든 최단 경로를 파악하고 이를 바탕으로 모든 연산 결과를 고려한다.
		Queue<State> q = new LinkedList<>();
		q.offer(new State(0, 0, map[0][0] - '0', ' '));
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			State s = q.poll();
			// (N-1, N-1)에 도달한 경우
			if (s.r == N-1 && s.c == N-1) {
				max = Math.max(max, s.result);
				min = Math.min(min, s.result);
			}
			for (int d = 0; d < 2; d++) {
				int nr = s.r + dr[d];
				int nc = s.c + dc[d];
				if (check(nr, nc)) {
					if ('0' <= map[nr][nc] && map[nr][nc] <= '9') {
						int n = map[nr][nc] - '0';
						if (s.oper == '+') {							
							q.offer(new State(nr, nc, s.result + n, ' '));
						} else if (s.oper == '-') {
							q.offer(new State(nr, nc, s.result - n, ' '));
						} else {
							q.offer(new State(nr, nc, s.result * n, ' '));
						}
					} else {
						q.offer(new State(nr, nc, s.result, map[nr][nc]));
					}
				}
			}
		}
		System.out.println(max + " " + min);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
