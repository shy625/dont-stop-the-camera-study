

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1195_킥다운 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		int N = s1.length();
		int M = s2.length();
		// s1을 짧은 문자열, s2를 긴 문자열로 둔다.
		if (N > M) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
			N = s1.length();
			M = s2.length();
		}
		int min = N + M; // 맞물렸을 때 너비의 최솟값
		boolean possible = false; // 맞물릴 수 있는지를 나타내는 변수
		// 첫 번째 경우
		// 길이가 짧은 기어의 왼쪽 끝이 길이가 긴 기어의 왼쪽 끝보다 더 왼쪽에 있는 경우
		for (int i = 0; i < N; i++) {
			possible = true;
			for (int j = 0; j <= i; j++) {
				if (s2.charAt(j) == '2' && s1.charAt(N-1-i+j) == '2') {
					possible = false;
					break;
				}
			}
			if (possible) min = Math.min(min, N+M-1-i);
		}
		// 두 번째 경우
		// 길이가 짧은 기어의 오른쪽 끝이 길이가 긴 기어의 오른쪽 끝보다 더 오른쪽에 있는 경우
		for (int i = 0; i < N; i++) {
			possible = true;
			for (int j = 0; j <= i; j++) {
				if (s2.charAt(M-1-i+j) == '2' && s1.charAt(j) == '2') {
					possible = false;
					break;
				}
			}
			if (possible) min = Math.min(min, N+M-1-i);
		}
		// 세 번째 경우
		// 길이가 짧은 기어가 길이가 긴 기어 위에 있는 경우(이 때는 너비가 항상 길이가 긴 기어와 같다.)
		for (int i = 0; i< M-N; i++) {
			possible = true;
			for (int j = 0; j < N; j++) {
				if (s1.charAt(j)=='2' && s2.charAt(i+j) == '2') {
					possible = false;
					break;
				}
			}
			if (possible) min = Math.min(min, M);
		}
		System.out.println(min);
	}
}
