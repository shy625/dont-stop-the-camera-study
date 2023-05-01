import java.util.Scanner;

public class BOJ_G5_2877_4와7 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int K = scann.nextInt();
		int N = 0;
		int digit = 1;
		int square = 2;
		
		// 2의 제곱수를 더하면서 몇자리 수인지 구한다.
		// 2의 제곱수 N과 다음 2의 제곱수를 더한수 N+(2^n) 사이에 있는 K가 중간보다 큰지 작은지 판별한다.
		// 중간보다 크다면 7, 작다면 4를 해당 자리에 넣는다.
		// 자리에 숫자를 넣은 후, K에서 정해진 수를 빼준다.
		// *정해진 수 : 7를 넣었다면 2^n, 4를 넣었다면 2^(n-1)
		
		while(true) {
			if(N + square >= K) break;
			N += square;
			digit++;
			square *= 2;
		}
		
		int nNext = N + square;
		
		for (int i = 0; i < digit; i++) {
			int mid = N/2 + nNext/2;
			if(mid < K) {
				sb.append(7);
				K -= square;
			} else {
				sb.append(4);
				K -= square/2;
			}
			
			nNext -= square;
			square /= 2;
			N -= square;
			
		}
		
		System.out.println(sb.toString());
		
		
	}
		
}
