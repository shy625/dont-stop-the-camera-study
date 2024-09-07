

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25603_짱해커_이동식 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] costs = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		// 이분탐색을 활용
		// 의뢰 비용 한계선을 기준으로 그 아래의 비용을 가지는 의뢰를 전부 수락했을 때,
		// 연속으로 K개 이상의 의뢰를 거절하는지 확인
		long l = 1;
		long r = Integer.MAX_VALUE;
		while (l < r) {
			long mid = (l+r)/2;
			int prev = 0;
			boolean possible = true;
			for (int i = 0; i < N; i++) {
				// 의뢰 비용 한계보다 아래의 비용을 가지는 의뢰면 수락
				if (costs[i] <= mid) prev = i;
				// 의뢰 비용 한계보다 높은 비용을 가지는 의뢰가 연속으로 K개 이상 나온 경우
				if (i - prev >= K) {
					possible = false;
					break;
				}
			}
			if (possible) r = mid;
			else l = mid+1;
		}
		System.out.println(l);
	}

}
