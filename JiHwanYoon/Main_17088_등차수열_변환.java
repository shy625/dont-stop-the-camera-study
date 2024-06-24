

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17088_등차수열_변환 {
	static int N, min;
	static int[] B;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		min = Integer.MAX_VALUE; // 연산 횟수의 최솟값
		// 수열의 크기가 1인 경우
		if (N == 1) {
			System.out.println(0);
			return;
		}
		// 수열의 시작과 끝에 대한 연산 여부를 지정하고 각 경우에 대해 연산 횟수를 계산한다.
		for (int d1 = -1; d1 <= 1; d1++) {
			for (int d2 = -1; d2 <= 1; d2++) {
				check(d1, d2);
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// 수열의 시작에 d1, 끝에 d2를 더했을 때 수열이 등차수열을 이루기 위한 연산 횟수를 계산하는 함수
	private static void check(int d1, int d2) {
		int[] C = Arrays.copyOf(B, N);
		C[0] += d1;
		C[N-1] += d2;
		// 수열의 시작과 끝을 이용해 공차를 계산하되, 공차가 정수인지 확인한다.
		int d = (C[N-1] - C[0])/(N-1);
		if (d*(N-1) != (C[N-1] - C[0])) return;
		int cnt = 0; // 연산 횟수
		// 수열의 처음과 끝에 대한 연산 적용 여부에 따라 cnt를 초기화
		if (C[0] != B[0]) cnt++;
		if (C[N-1] != B[N-1]) cnt++;
		// 수열의 각 수에 연산을 적용해야 하는지 확인한다.
		for (int i = 1; i < N; i++) {
			// 연산 적용이 필요없는 경우
			if (C[i-1] + d == C[i]) continue; 
			// 연산을 적용했을 때 등차수열을 만들 수 있는 경우
			else if (Math.abs(C[i-1] + d - C[i]) == 1) {
				C[i] = C[i-1] + d;
				cnt++;
			}
			// 연산을 적용해도 등차수열을 못 만드는 경우
			else return;
		}
		min = Math.min(min, cnt);
	}

}
