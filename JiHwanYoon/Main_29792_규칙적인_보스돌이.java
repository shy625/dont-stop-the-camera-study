

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_29792_규칙적인_보스돌이 {
	// 보스의 체력과 드랍 메소를 나타내는 클래스 
	static class Boss {
		long P, Q;
		public Boss(long P, long Q) {
			this.P = P;
			this.Q = Q;
		}
	}
	static int N, M, K;
	static long[] damage; // 각 캐릭터가 1초에 가하는 데미지를 나타내는 배열
	static Boss[] bosses; // 각 보스의 정보를 담는 배열
	static int MIN15 = 900; // 15분
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		damage = new long[N];
		for (int i = 0; i < N; i++) {
			damage[i] = Long.parseLong(br.readLine());
		}
		bosses = new Boss[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			bosses[i] = new Boss(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		}
		// 각 캐릭터가 15분 동안 얻을 수 있는 최대 메소를 구한 뒤, 가장 많은 메소를 얻을 수 있는 캐릭터부터 선택한다.
		PriorityQueue<Long> meso = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			meso.offer(slayBoss(i));
		}
		long result = 0;
		for (int i = 0; i < M; i++) {
			result += meso.poll();
		}
		System.out.println(result);
	}
	// idx번째 캐릭터가 15분 동안 보스를 잡아서 얻을 수 있는 최대 메소를 구하는 함수
	private static long slayBoss(int idx) {
		long d = damage[idx];
		// 배낭 문제와 동일한 풀이로 진행
		// dp[i][j]는 i번째 보스까지 고려했을 때, j초 안에 얻을 수 있는 최대 메소를 의미한다.
		long[][] dp = new long[K+1][MIN15+1];
		for (int i = 1; i <= K; i++) {
			Boss b = bosses[i-1];
			// i번째 보스를 안 잡는 경우
			for (int j = 0; j <= MIN15; j++) {
				dp[i][j] = dp[i-1][j];
			}
			// i번째 보스를 잡는데 걸리는 시간
			long timeToSlay = (b.P-1)/d+1;
			// 15분 안에 i번째 보스를 못 잡는 경우
			if (timeToSlay > 900) continue;
			// i번째 보스를 잡는 경우에 대해 얻을 수 있는 최대 메소 갱신 시도
			for (int j = (int)timeToSlay; j <= MIN15; j++) {
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j-(int)timeToSlay] + b.Q);
			}
		}
		return dp[K][900];
	}

}
