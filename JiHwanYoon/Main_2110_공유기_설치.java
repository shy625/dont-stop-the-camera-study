

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기_설치 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		// 이분 탐색을 공유기 사이의 거리를 기준으로 한다.
		// l 값은 가능한 공유기 사이의 거리의 최솟값, r 값은 공유기 사이의 거리의 최댓값으로 한다.
		int l = Integer.MAX_VALUE;
		for (int i = 1; i < N; i++) {
			l = Math.min(l, arr[i] - arr[i-1]);
		}
		int r = arr[N-1] - arr[0];
		while (l < r) {
			int mid = (l+r)/2;
			// 주어진 공유기 사이의 거리에 대해 공유기가 몇 대 설치될 수 있는지를 조사
			int cnt = 1;
			int prev = arr[0];
			for (int i = 1; i < N; i++) {
				// 설치했던 공유기와 현재 집 좌표와의 거리가 mid보다 크거나 같으면 설치
				if (arr[i] - prev >= mid) { 
					cnt++;
					prev = arr[i];
				}
			}
			// 공유기 수가 C보다 크거나 같으면 공유기 사이의 거리를 좀더 늘려본다.
			if (cnt >= C) {
				l = mid+1;
			} else { // 그렇지 않으면 공유기 사이의 거리를 줄여본다.
				r = mid;
			}
		}
		// 위 연산의 결과 공유기의 수가 C 미만일때 가장 인접한 공유기 사이의 거리의 최솟값이 나오는데,
		// 여기에 1을 빼면 공유기 수가 C가 될 때 가장 인접한 공유기 사이의 거리의 최댓값이 나온다.
		System.out.println(r-1);
	}

}
