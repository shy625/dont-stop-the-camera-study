

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1254_팰린드롬_만들기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		// 입력으로 받은 문자열을 앞쪽부터 길이가 l인 문자열로 잘라보면서 뒤에 뒤집은 뒤 붙여보고 팰린드롬인지 판단한다.
		outer: for (int l = 0; l < s.length(); l++) {
			// 길이가 l인 문자열로 자르기
			StringBuilder fraction = new StringBuilder(s.substring(0, l));
			// 자른 문자열 뒤집기
			fraction.reverse();
			// 자른 문자열을 기존 문자열 뒤에 붙여보기
			StringBuilder cur = new StringBuilder(s).append(fraction);
			// 팰린드롬인지 확인
			for (int i = 0; i < cur.length()/2; i++) {
				if (cur.charAt(i) != cur.charAt(cur.length() - i - 1)) continue outer;
			}
			// 위 for문을 무사히 통과했다면 팰린드롬이라는 뜻이므로 현재 문자열의 길이를 출력
			System.out.println(cur.length());
			System.exit(0);
		}
	}

}
