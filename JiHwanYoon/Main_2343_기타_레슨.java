

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2343_기타_레슨 {
	static int N, M, min;
	static int[] lengths;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lengths = new int[N];
		st = new StringTokenizer(br.readLine());
		min = 0; // 블루레이 크기의 최솟값
		for (int i = 0; i < N; i++) {
			lengths[i] = Integer.parseInt(st.nextToken());
			// 블루레이는 최소한 가장 긴 강의 길이만큼의 크기를 가져야 한다.
			min = Math.max(min, lengths[i]);
		}
		System.out.println(binarySearch());
	}
	// 이분 탐색을 활용해 블루레이 크기의 최솟값을 구한다.
	private static int binarySearch() {
		int l = min;
		int r = Integer.MAX_VALUE/10;
		while (l < r) {
			int mid = (l+r)/2;
			int cnt = 1; // 불루레이 크기가 mid일 때 필요한 블루레이 개수
			int sum = 0; // 현재 고려 중인 블루레이에 담긴 강의 길이의 총합
			for (int i = 0; i < N; i++) {
				// 블루레이에 i번째 강의를 담았을 때 크기가 mid를 초과하면 다음 블루레이에 강의를 담는다.
				if (sum + lengths[i] > mid) {
					cnt++;
					sum = 0;
				}
				sum += lengths[i];
			}
			// 필요한 블루레이 개수에 따라 이분 탐색 변수 조정
			if (cnt <= M) r = mid;
			else l = mid+1;
		}
		return l;
	}

}
