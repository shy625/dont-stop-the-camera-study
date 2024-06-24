

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2922_즐거운_단어 {
	static char[] strs;
	static int N;
	static long res;
	static String vowel = "AEIOU";
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = s.length();
		strs = new char[N];
		for (int i = 0; i < N; i++) {
			strs[i] = s.charAt(i);
		}
		bf(0);
		System.out.println(res);
	}
	// 브루트포스 알고리즘을 활용
	private static void bf(int cnt) {
		if (cnt == N) {
			if (!check()) return;
			res += count();
			return;
		}
		if (strs[cnt] != '_') {
			bf(cnt+1);
			return;
		}
		strs[cnt] = '0'; // 모음
		bf(cnt+1);
		strs[cnt] = '1'; // 자음
		bf(cnt+1);
		strs[cnt] = '2'; // L
		bf(cnt+1);
		strs[cnt] = '_'; // 백트래킹
	}
	// 조건을 만족하는지 확인하는 함수
	private static boolean check() {
		boolean isL = false; // L 존재 여부
		int cntV = 0; // 연속되는 모음의 개수
		int cntC = 0; // 연속되는 자음의 개수
		for (int i = 0; i < N; i++) {
			if (strs[i] == 'L' || strs[i] == '2') isL = true;
			if (vowel.indexOf(strs[i]+"") != -1 || strs[i] == '0') {
				cntV++;
				cntC = 0;
			} else {
				cntV = 0;
				cntC++;
			}
			if (cntV == 3 || cntC == 3) return false;
		}
		return isL;
	}
	// 경우의 수를 세는 함수
	private static long count() {
		long cnt = 1l;
		for (int i = 0; i < N; i++) {
			if (strs[i] == '0') cnt *= 5l;
			else if (strs[i] == '1') cnt *= 20l;
		}
		return cnt;
	}
}
