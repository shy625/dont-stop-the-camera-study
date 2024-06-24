

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1669_멍멍이_쓰다듬기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int monkey = Integer.parseInt(st.nextToken());
		int dog = Integer.parseInt(st.nextToken());
		long diff = dog - monkey; // 키 차이만 고려하면 된다.
		// 일자가 짝수일 때와, 홀수일 때 늘릴 수 있는 키의 길이 범위를 생각하면 된다.
		// 짝수(2*N)인 경우, 최대 N(N+1)까지 늘릴 수 있고,
		// 홀수(2*N+1)인 경우, 최대 N(N+1) + (N+1)까지 늘릴 수 있다.
		// 키를 늘리기 위한 최소 일자를 구하기 때문에, 범위는 일자별 가능한 키 증가량의 최댓값을 고려한다.
		long N = 0;
		while (true) {
			if (diff == 0 || ((N-1)*N + N < diff && diff <= N*(N+1))) {
				System.out.println(2*N);
				return;
			} else if (N*(N+1) < diff && diff <= N*(N+1) + (N+1)) {
				System.out.println(2*N+1);
				return;
			}
			N++;
		}
	}

}
