

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_29336_월향_비상 {
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] A = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}
		// greedy algorithm을 활용
		// 문제들의 퀄리티 합을 최대로 하기 위해서는, 최대한 적은 인원으로 각 조건을 충족해야 한다.
		// 따라서, 역량이 높은 운영진부터 문제를 만들어 보다 적은 인원으로 조건을 충족할 수 있도록 한다.
		Arrays.sort(A);
		long sum = 0; // 문제들의 퀄리티 합
		int max = 0; // 마지막 조건이 있는 날
		int idx = N-1; // 현재 보고 있는 운영진의 역량
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			if (sum == -1) continue; // 모든 조건을 충족하는 것이 불가능한 경우
			// 조건을 충족시키기 위해 운영진을 한 명씩 뽑아 문제를 만든다. 
			while (idx >= 0 && sum < Q) {
				sum += A[idx--] + T;
			}
			// 모든 운영진을 동원해도 현재 조건을 충족시키지 못하는 경우
			if (sum < Q) {
				sum = -1;
				continue;
			}
			max = T;
		}
		// 모든 조건을 충족하는 것이 불가능한 경우
		if (sum == -1) {
			System.out.println(-1);
			return;
		}
		// 모든 조건을 충족시켰다면 마지막 날에 모든 운영진을 동원해 문제 퀄리티의 합을 높인다.
		for (int i = idx; i >= 0; i--) {
			sum += A[i] + max;
		}
		System.out.println(sum);
	}

}
