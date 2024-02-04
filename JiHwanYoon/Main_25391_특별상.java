

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_25391_특별상 {
	// 주최자가 매긴 점수와 심판이 매긴 점수를 나타내는 클래스
	static class Score {
		int a, b;
		public Score(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Score> scores = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			scores.add(new Score(a, b));
		}
		long sum = 0; // 주최자가 매긴 점수의 합의 최댓값
		// greedy algorithm을 이용
		// 우선, 심판이 매긴 점수가 높은 순서대로 수상자를 결정한다.
		// 기본적으로 심판이 매긴 점수가 가장 높은 상위 K명은, 심판이 고르든 주최자가 고르든 무조건 수상하기 때문에,
		// 주최자가 매긴 점수의 합을 최대로 하기 위해서는 해당 K명을 제외한 나머지 M명을 고르는 것이 중요하다.
		Collections.sort(scores, (x, y) -> Integer.compare(x.b, y.b));
		for (int i = 0; i < K; i++) {
			sum += (long)(scores.get(scores.size()-1).a);
			scores.remove(scores.size()-1);
		}
		// 이제, 상위 K명을 제외하고 나머지 인원들 중 주최자가 매긴 점수가 높은 순서대로 수상자를 결정한다.
		Collections.sort(scores, (x, y) -> Integer.compare(y.a, x.a));
		for (int i = 0; i < M; i++) {
			sum += (long)(scores.get(i).a);
		}
		System.out.println(sum);
	}

}
