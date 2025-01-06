

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1421_나무꾼_이다솜 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[] lengths = new int[N];
		int maxLen = -1; // 가장 긴 나무의 길이
		long max = Integer.MIN_VALUE; // 벌 수 있는 돈의 최댓값
		for (int i = 0; i < N; i++) {
			lengths[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, lengths[i]);
		}
		// 1부터 maxLen까지의 길이로 나무를 잘라보고,
		// 각각의 경우 벌 수 있는 돈을 구한다.
		for (int i = 1; i <= maxLen; i++) {
			long sum = 0; // 벌 수 있는 돈
			for (int j = 0; j < N; j++) {
				int cnt = 0; // 나무를 자른 횟수
				// 나무 길이가 자르는 길이 이상인 경우만 고려 
				if (lengths[j] >= i) {
					if (lengths[j]%i == 0) {
						cnt = lengths[j]/i - 1;
					} else cnt = lengths[j]/i;
					int temp = W*i*(lengths[j]/i) - cnt*C;
					// 나무를 자르는 비용이 더 큰 경우는 제외
					if (temp > 0) sum += temp;
				}
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
