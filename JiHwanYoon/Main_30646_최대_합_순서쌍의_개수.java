

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_30646_최대_합_순서쌍의_개수 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N+1];
		long[] sum = new long[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		// 누적합을 활용
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i-1] + arr[i];
		}
		// 해시맵 활용
		Map<Long, Integer> minIdxs = new HashMap<>(); // 각 숫자에 대해, 가장 왼쪽에 있는 숫자의 인덱스를 value로 하는 해시맵
		Map<Long, Integer> maxIdxs = new HashMap<>(); // 각 숫자에 대해, 가장 오른쪽에 있는 숫자의 인덱스를 value로 하는 해시맵
		for (int i = 1; i <= N; i++) {
			minIdxs.putIfAbsent(arr[i], Integer.MAX_VALUE);
			minIdxs.put(arr[i], Math.min(minIdxs.get(arr[i]), i));
			maxIdxs.putIfAbsent(arr[i], -1);
			maxIdxs.put(arr[i], Math.max(maxIdxs.get(arr[i]), i));
		}
		long max = -1; // 순서쌍의 합의 최댓값
		int cnt = 0; // 순서쌍의 합이 max인 순서쌍의 개수
		for (long n : minIdxs.keySet()) {
			int minIdx = minIdxs.get(n);
			int maxIdx = maxIdxs.get(n);
			long s = sum[maxIdx] - sum[minIdx-1];
			if (max < s) {
				max = s;
				cnt = 1;
			} else if (max == s) {
				cnt++;
			}
		}
		System.out.println(max+" "+cnt);
	}

}
