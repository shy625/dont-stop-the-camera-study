

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_27375_금공강_사수 {
	// 과목의 시작 시간, 종료 시간을 나타내는 클래스
	static class Subject {
		int s, e;
		public Subject(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
	static int N, K;
	static public Subject[] subjects; // 과목들을 저장하는 배열
	static public boolean[] visited; // 각 시간마다 강의를 수강 중인지를 나타내는 배열
	static public long res;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		subjects = new Subject[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			// 날짜를 동시에 고려하기 위해 각 일자에 10을 곱한 숫자를 시작 시간 및 종료 시간에 더해준다.
			subjects[i] = new Subject(w*10+s, w*10+e);
		}
		visited = new boolean[5*10+1];
		res = 0;
		// 가능한 경우의 수를 구한다.
		subset(0, 0);
		System.out.println(res);
	}
	// 브루트포스 알고리즘을 활용해 가능한 경우의 수를 구하는 함수
	private static void subset(int cnt, int curK) {
		// K학점을 들은 경우
		if (curK == K) {
			res++;
			return;
		}
		// 모든 강의를 고려했거나, 수강한 강의의 학점 합이 K 초과인 경우
		if (cnt == N || curK > K) {
			return;
		}
		Subject cur = subjects[cnt]; // 현재 고려하는 강의
		// 현재 강의를 수강하는 경우
		// 금요일 강의가 아닌 경우만 고려
		if (cur.s <= 50 && cur.e <= 50) {
			// 다른 강의와 시간이 겹치지 않는지 확인
			boolean possible = true;
			for (int t = cur.s; t <= cur.e; t++) {
				if (visited[t]) {
					possible = false;
					break;
				}
			}
			// 다른 강의와 시간이 겹치지 않는 경우
			if (possible) {	
				// 방문 처리
				for (int t = cur.s; t <= cur.e; t++) {
					visited[t] = true;
				}
				subset(cnt+1, curK + cur.e - cur.s + 1);
				// 백트래킹
				for (int t = cur.s; t <= cur.e; t++) {
					visited[t] = false;
				}
			}
		}
		// 현재 강의를 수강하지 않는 경우
		subset(cnt+1, curK);
	}

}
