

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10427_빚 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long[] arr = new long[N+1];
			for (int i = 1; i <= N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			// greedy algorithm을 이용
			// 우선, 주어진 숫자들을 오름차순으로 정렬한다.
			// 그 다음, l번째 숫자부터 N번째 숫자까지
			// (i번째 숫자)*l - (i번째 숫자보다 작은 가장 큰 숫자 l개의 합)을 모두 구하고,
			// 그 중 가장 작은 값을 S(l)로 한다.
			// 이때, (i번째 숫자보다 작은 가장 큰 숫자 l개의 합)을 구할 때 누적합을 활용
			Arrays.sort(arr);
			long[] sum = Arrays.copyOf(arr, N+1); // 누적합 배열
			for (int i = 1; i <= N; i++) {
				sum[i] += sum[i-1];
			}
			long res = 0; // S(1) + ... + S(N)
			// S(1)은 항상 0이므로 S(2)부터 계산
			for (int l = 2; l <= N; l++) {
				long min = Long.MAX_VALUE; // S(l)
				for (int i = l; i <= N; i++) {
					min = Math.min(min, arr[i]*l - (sum[i] - sum[i-l]));
				}
				res += min;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
	}

}
