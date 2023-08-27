

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1411_비슷한_단어 {
	static int L;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] strs = new String[N];
		for (int i = 0; i < N; i++) {
			strs[i] = br.readLine();
		}
		L = strs[0].length(); // 문자열의 길이
		int cnt = 0; // 비슷한 쌍의 개수
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				// 서로 다른 두 문자열이 비슷하면 cnt를 증가시킨다.
				if (check(strs[i], strs[j])) cnt++;
			}
		}
		System.out.println(cnt);
	}
	// s1과 s2가 비슷한 단어인지 확인하는 함수
	private static boolean check(String s1, String s2) {
		int[] chars = new int[26]; // s1의 각 알파벳이 s2를 만들기 위해 어떤 알파벳으로 바뀌었는지를 나타내는 배열
		boolean[] changedTo = new boolean[26]; // 각 알파벳에 대해, 해당 알파벳으로 바꾼 적이 있는지를 나타내는 배열
		Arrays.fill(chars, -1); // 초기화
		for (int i = 0; i < L; i++) {
			int c1 = s1.charAt(i)-'a';
			int c2 = s2.charAt(i)-'a';
			// 알파벳 c1을 처음으로 바꾸는 경우, 다른 알파벳을 c2로 바꾼적이 없어야 한다.
			// 알파벳 c1을 예전에 바꾼 경우, c1은 항상 c2로 바뀌어야 한다.
			// 위 두 조건 중 하나라도 만족하지 않으면, false를 반환
			if ((chars[c1] == -1 && changedTo[c2]) || (chars[c1] != -1 && chars[c1] != c2)) return false;
			// 알파벳 c1을 c2로 바꾼다.
			chars[c1] = c2;
			changedTo[c2] = true;
		}
		// 두 문자열이 비슷한 경우
		return true;
	}

}
