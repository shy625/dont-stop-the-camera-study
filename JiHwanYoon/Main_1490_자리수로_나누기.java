

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_1490_자리수로_나누기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strN = br.readLine();
		long N = Long.parseLong(strN);
		Set<Integer> nums = new HashSet<>(); // N의 0이 아닌 모든 자릿수
		for (char c : strN.toCharArray()) {
			int n = c - '0';
			if (n != 0) nums.add(n);
		}
		// 브루트포스 알고리즘을 이용
		// N 뒤에 0~9, 00~99, ... 를 차례대로 붙여가면서 
		// N의 0이 아닌 모든 자릿수로 나누어 떨어지는지 확인
		long k = 1;
		while (true) {
			long temp = N*k;
			outer: for (int l = 0; l < k; l++) {
				long temp2 = temp + l;
				for (int num : nums) {
					if (temp2%num != 0) continue outer;
				}
				// N의 0이 아닌 모든 자릿수로 나누어 떨어지는 경우
				System.out.println(temp2);
				System.exit(0);
			}
			k *= 10;
		}
	}
}
