

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1802_종이_접기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			sb.append(check(s) ? "YES" : "NO").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// s가 동호의 종이접기를 통해 얻을 수 있는 문자열인지 확인하는 함수
	// 동호의 종이접기 방식으로 진행하면, 가운데를 기준으로 왼쪽과 오른쪽이 데칼코마니 형태로 비교했을 때 모두 다른 숫자가 나와야 한다.
	// 예를 들어, s의 길이가 7인 경우 왼쪽 1번째 문자가 1이면, 오른쪽 1번째 문자는 0이여야 하고,
	// 왼쪽 2번째 문자가 0이면, 오른쪽 2번째 문자가 1이어야 한다.
	// 또한, 이와 같은 조건을 가운데를 기준으로 왼쪽과 오른쪽 부분문자열이 재귀적으로 만족해야 한다.
	private static boolean check(String s) {
		if (s.length() == 1) return true;
		int N = s.length();
		for (int i = 0; i < N/2; i++) {
			if (s.charAt(i) == s.charAt(N-1-i)) return false;
		}
		return check(s.substring(0, N/2)) && check(s.substring(N/2+1, N));
	}

}
