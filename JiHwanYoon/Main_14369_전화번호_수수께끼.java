package algoStudy_2024_10월_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_14369_전화번호_수수께끼 {
	static String[] intToStr;
	static StringBuilder sb;
	static int[] numChar;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 정수를 문자열로 바꿔주는 배열
		intToStr = new String[] {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
		sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			// 문자열 내 문자의 개수를 구한다.
			numChar = new int[26];
			for (char c : s.toCharArray()) {
				numChar[c - 'A']++;
			}
			sb.append("Case #" + t + ": ");
			simulate(s.length(), new ArrayList<>(), 0);
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 주어진 문자열을 숫자로 바꾼 결과를 구하는 함수
	// rem : 현재 남은 문자의 개수, chars : 현재 찾은 숫자들을 모아두는 리스트, min : 현재 찾은 숫자 중 가장 작은 값
	private static void simulate(int rem, ArrayList<Integer> chars, int min) {
		if (rem < 0) return;
		// 문자열 내 숫자들을 모두 찾은 경우
		if (rem == 0) {
			Collections.sort(chars);
			for (int c : chars) {
				sb.append(c);
			}
			sb.append("\n");
			return;
		}
		outer: for (int i = min; i <= 9; i++) {
			String str = intToStr[i];
			// 해당 숫자가 문자열 내 존재하는지 확인
			boolean possible = true;
			for (int j = 0; j < str.length(); j++) {
				int c = str.charAt(j) - 'A';
				if (numChar[c] == 0) {
					possible = false;
				}
			}
			// 해당 숫자가 문자열 내 존재하지 않는 경우
			if (!possible) continue outer;
			// 해당 숫자가 문자열 내 존재하는 경우
			for (int j = 0; j < str.length(); j++) {
				int c = str.charAt(j) - 'A';
				numChar[c]--;
			}
			chars.add(i);
			simulate(rem - str.length(), chars, i);
			// 백트래킹
			for (int j = 0; j < str.length(); j++) {
				int c = str.charAt(j) - 'A';
				numChar[c]++;
			}
			chars.remove(chars.size()-1);
		}
	}

}
