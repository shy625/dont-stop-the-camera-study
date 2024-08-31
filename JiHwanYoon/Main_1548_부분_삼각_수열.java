

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1548_부분_삼각_수열 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// A의 길이가 3보다 작으면 삼각관계를 만들 수 없다.
		if (A.length < 3) {
			System.out.println(A.length);
			System.exit(0);
		}
		// 그리디 알고리즘 활용
		// A를 정렬한 후, 가장 작은 수 3개를 가지고 삼각관계를 확인
		// 만약 삼각관계 만족 시, 3개의 수 중 가장 큰 수를 더 큰 수로 바꿔서 삼각관계 확인
		// 삼각관계 불만족 시, 3개의 수 중 가장 작은 수를 더 큰 수로 바꿔서 삼각관계 확인
		Arrays.sort(A);
		int max = 2;
		for (int start = 0; start < N-2; start++) {
			int end = start+2;
			while (true) {
				if (A[start] + A[start+1] > A[end]) {
	                max = Math.max(max, end - start + 1);
	                if (++end == N) break;
				}
	            else break;
			}
		}		
		System.out.println(max);
	}
}
