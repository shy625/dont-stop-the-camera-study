

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1629_곱셈 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		System.out.println(multiply(A, B, C)%C);
	}
	// 분할정복을 활용해, 지수가 반인 경우를 계산한 뒤 그 값을 활용해 원래 값을 빠르게 계산
	// 위 과정을 재귀적으로 반복
	private static long multiply(int a, int b, int c) {
		if (b == 1) return a%c;
		long temp = multiply(a, b/2, c)%c;
		if (b%2 == 0) {
			return (temp * temp) % c;
		} else return ((temp * temp)%c * (a%c)) % c;
	}

}
