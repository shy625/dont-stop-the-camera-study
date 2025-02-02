

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17179_케이크_자르기 {
	static int N, M, L;
	static int[] cut;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		// 자를 수 있는 부분을 배열로 저장
		cut = new int[M+2];
		for (int i = 1; i <= M; i++) {
			cut[i] = Integer.parseInt(br.readLine());
		}
		cut[M+1] = L;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int l = binarySearch(Integer.parseInt(br.readLine()));
			sb.append(l).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 이분 탐색을 활용해 Q번 자를 때 만들 수 있는 가장 작은 케이크 조각의 길이의 최댓값을 구한다.
	private static int binarySearch(int Q) {
		int l = 0;
		int r = L;
		while (l < r) {
			int mid = (l+r)/2; // 각 케이크 조각에 대해 허용 가능한 최대 길이
			int cnt = 0; // 케이크를 자르는 횟수
			int cur = 0; // 현재 케이크 조각의 길이
			for (int i = 1; i <= M+1; i++) {
				cur += cut[i] - cut[i-1];
				// 현재 케이크 조각이 길이의 경계보다 더 길면 자른다.
				if (cur >= mid) {
					cur = 0;
					cnt++;
				}
			}
			if (cnt <= Q) {
				r = mid;
			} else {
				l = mid+1;
			}
		}
		return l-1;
	}

}
