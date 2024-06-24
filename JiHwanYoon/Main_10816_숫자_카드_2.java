

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_10816_숫자_카드_2 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		// 이분 탐색을 위해 배열 정렬
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());
		s = br.readLine().split(" ");
		int[] res = new int[M];
		// M개의 숫자에 대해 이분 탐색을 통해 해당 숫자가 몇 개 있는지 파악
		for (int i = 0; i < M; i++) {
			res[i] = binarySearch(Integer.parseInt(s[i]));
		}
		// M이 크기 때문에 출력은 StringBuilder를 이용
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			sb.append(res[i]+" ");
		}
		System.out.println(sb.toString());
	}
	// 이분 탐색을 하되 같은 원소가 있기 때문에 특정 원소를 찾는 것이 목적이 아니라, 
	// 특정 원소 중 가장 왼쪽에 있는 원소의 위치와, 가장 오른쪽에 있는 위치를 알아내는 것이 목적이 된다. 
	private static int binarySearch(int k) {
		// 가장 왼쪽에 있는 k의 위치를 알아내는 이분 탐색
		int l = 0;
		int r = N-1;
		while (l < r) {
			int mid = (l+r)/2;
			if (arr[mid] < k) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		// 가장 왼쪽에 있는 k의 위치
		int start = l;
		// 가장 오른쪽에 있는 k의 위치를 알아내기 위해, k가 아닌 k+1에 대해 위와 같은 로직을 사용
		l = 0;
		r = N-1;
		while (l < r) {
			int mid = (l+r)/2;
			if (arr[mid] < k+1) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		// 가장 오른쪽에 있는 k의 위치 + 1
		int end = l;
		// 예외로 end가 배열 인덱스의 끝일 경우 end 인덱스에 해당하는 값이 k가 되는데, 이를 보정하기 위해 end에 1을 더한다.
		if (arr[end] == k) {
			end++;
		}
		// k의 개수 반환
		return end - start;
		
	}

}
