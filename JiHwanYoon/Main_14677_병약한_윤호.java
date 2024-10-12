

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_14677_병약한_윤호 {
	// 현재 마지막으로 약을 먹은 시간, 왼쪽에서 먹으려는 약의 위치, 오른쪽에서 먹으려는 약의 위치를 나타내는 클래스
	static class State {
		char time;
		int l, r;
		public State(char time, int l, int r) {
			this.time = time;
			this.l = l;
			this.r = r;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String medicines = br.readLine();
		// 너비 우선 탐색을 활용
		Queue<State> q = new LinkedList<>();
		boolean[][] visited = new boolean[3*N][3*N];
		q.offer(new State('D', 0, 3*N-1));
		visited[0][N-1] = true;
		int max = 0; // 가장 많이 먹은 약의 개수
		for (int i = 0; i <= 3*N; i++) {
			int qLen = q.size();
			while (qLen > 0) {
				State s = q.poll();
				// 왼쪽 약을 먹는 경우
				if (s.l <= Math.min(s.r, 3*N-2) && !visited[s.l+1][s.r] && check(s.time, medicines.charAt(s.l))) {
					visited[s.l+1][s.r] = true;
					q.offer(new State(medicines.charAt(s.l), s.l+1, s.r));
					max = Math.max(max, (s.l+1)+(3*N-1-s.r));
				}
				// 오른쪽 약을 먹는 경우
				if (Math.max(s.l, 1) <= s.r && !visited[s.l][s.r-1] && check(s.time, medicines.charAt(s.r))) {
					visited[s.l][s.r-1] = true;
					q.offer(new State(medicines.charAt(s.r), s.l, s.r-1));
					max = Math.max(max, (s.l)+(3*N-s.r));
				}
				qLen--;
			}
		}
		System.out.println(max);
	}
	// prev 약 다음으로 next 약을 먹을 수 있는지 판별하는 함수
	private static boolean check(char prev, char next) {
		return (prev == 'B' && next == 'L') || (prev == 'L' && next == 'D') || (prev == 'D' && next == 'B');
	}

}
