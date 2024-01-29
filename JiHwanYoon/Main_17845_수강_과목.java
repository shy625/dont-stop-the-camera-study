

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17845_수강_과목 {
	// 과목의 중요도와 학습 시간을 나타내는 클래스
	static class Subject {
		int I, T;
		public Subject(int I, int T) {
			this.I = I;
			this.T = T;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Subject[] subjects = new Subject[K+1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			subjects[i] = new Subject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// 배낭 문제 풀이 방식을 적용
		// dp[i][j]는 i번째 과목까지 고려했을 때, 공부 시간의 합이 j인 경우 중요도의 합의 최댓값
		int[][] dp = new int[K+1][N+1];
		for (int i = 1; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
				if (j <= N-subjects[i].T) dp[i][j+subjects[i].T] = Math.max(dp[i-1][j+subjects[i].T], dp[i-1][j] + subjects[i].I);
			}
		}
		int max = 0; // 중요도의 합의 최댓값
		for (int i = 0; i <= N; i++) {
			max = Math.max(max, dp[K][i]);
		}
		System.out.println(max);
	}

}
