

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_신기한_소수 {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder(); // 출력을 저장할 StringBuilder
		// 브루트포스 알고리즘을 활용
		// 숫자를 뒤에 하나씩 붙이면서, 소수가 되는지 확인한다.
		// 만약 N자리를 만들 때까지 계속 소수가 된다면, 출력에 추가
		perm("");
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 브루트포스 알고리즘을 활용해 N자리 신기한 소수를 찾는 함수
	private static void perm(String num) {
		// N자리 신기한 소수를 찾은 경우
		if (num.length() == N) {
			sb.append(num).append("\n");
			return;
		}
		for (int i = 1; i <= 9; i++) {
			String temp = num + i;
			// 뒤에 숫자를 붙였을 때 소수가 되지 않으면 다음으로 넘어간다.
			if (!check(temp)) continue;
			perm(temp);
		}
	}
	// num이 소수인지 판별하는 함수
	private static boolean check(String num) {
		int n = Integer.parseInt(num);
		if (n == 1) return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n%i == 0) return false;
		}
		return true;
	}

}
