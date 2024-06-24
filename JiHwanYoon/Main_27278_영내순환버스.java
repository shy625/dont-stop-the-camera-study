

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_27278_영내순환버스 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] times = new int[N];
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}
		// 누적합을 활용해 효율적으로 계산
		// sum[i](1<=i<=N)는 0번부터 i번까지 이동하는데 걸리는 시간, sum[i](N<i<=2*N)은 0번부터 순환을 한 번 한 뒤 i번까지 오는데 걸리는 시간
		int[] sum = new int[2*N+2];
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i-1] + times[i-1];
		}
		for (int i = 1; i <= N; i++) {
			sum[N+i] = sum[N+i-1] + times[i-1];
		}
		int max = -1; // 병사들 중 가장 마지막으로 하차하는 시간
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			// 해당 병사를 태우기 위해 기본적으로 얼마나 순환하는지 계산
			int base = (c/sum[N])*sum[N];
			c -= base;
			base = c <= sum[p] ? base : base + sum[N];
			// 병사를 태운 뒤 하차 지점에 내리는 시간을 계산
			if (p < r) {
				max = Math.max(max, base + sum[r]);
			} else {
				max = Math.max(max, base + sum[r+N]);
			}
		}
		System.out.println(max);
	}

}
