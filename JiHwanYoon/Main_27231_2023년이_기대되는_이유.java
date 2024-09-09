

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_27231_2023년이_기대되는_이유 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			boolean onlyOneContains = true; // 0과 1만으로 구성된 숫자인지 확인
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if ('1' < c && c <= '9') onlyOneContains = false;
			}
			// 만약 0과 1만으로 구성된 숫자면 조건을 만족하는 m이 무수히 많다.
			if (onlyOneContains) {
				sb.append("Hello, BOJ 2023!").append("\n");
				continue;
			}
			int m = 1;
			int cnt = 0; // 조건을 만족하는 m의 개수
			while (true) {
				long sum = 0; // d1^m + d2^m + ... dn^m
				for (int i = 0; i < s.length(); i++) {
					sum += (int)(Math.pow(s.charAt(i) - '0', m));
				}
				// 만약 위에서 구한 값이 입력으로 주어진 정수보다 커지면 절대 조건을 만족하는 m을 찾을 수 없다.
				if (sum > Integer.parseInt(s)) break;
				// sum과 같아지도록 입력으로 주어진 정수에 +를 넣을 수 있는지 확인
				if (simulate(s, 0, 0, sum)) cnt++;
				m++;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 정수 s에 +를 넣어서 sum을 만들 수 있는지 확인하는 함수
	private static boolean simulate(String s, int cnt, int cur, long sum) {
		// 정수 s에 +를 넣어서 구한 값이 sum보다 커지면 return
		if (cur > sum) return false;
		// +를 전부 다 넣은 경우 합을 구한 뒤 sum과 비교
		if (cnt == s.length()) {
			if (sum == cur) return true;
			else return false;
		}
		boolean result = false;
		for (int i = cnt+1; i <= s.length(); i++) {
			result = result || simulate(s, i, cur + Integer.parseInt(s.substring(cnt, i)), sum);
			if (result) break;
		}
		return result;
	}

}
