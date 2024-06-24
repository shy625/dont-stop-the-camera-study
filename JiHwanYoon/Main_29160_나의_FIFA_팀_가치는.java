

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_29160_나의_FIFA_팀_가치는 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 각 포지션에 대해 선수들의 가치를 우선순위 큐에 저장한다.
		ArrayList<PriorityQueue<Integer>> pqs = new ArrayList<>();
		for (int i = 0; i <= 11; i++) {
			pqs.add(new PriorityQueue<>(Collections.reverseOrder()));
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			pqs.get(P).offer(W);
		}
		// K년 동안 선수들을 선발한다.
		for (int i = 0; i < K; i++) {
			for (int p = 1; p <= 11; p++) {
				if (pqs.get(p).isEmpty()) continue;
				pqs.get(p).offer(Math.max(0, pqs.get(p).poll()-1));
			}
		}
		// K년 12월의 팀 가치를 구한다.
		int sum = 0;
		for (int p = 1; p <= 11; p++) {
			if (pqs.get(p).isEmpty()) continue;
			sum += pqs.get(p).poll();
		}
		System.out.println(sum);
	}

}
