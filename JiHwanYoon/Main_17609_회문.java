

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17609_회문 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			String s = br.readLine();
			int N = s.length();
			// 투 포인터를 이용
			int res = 0; // 회문인지, 유사회문인지, 일반 문자열인지를 나타내는 값
			int l = 0;
			int r = N-1;
			// 우선 회문인지 확인
			while (l <= r) {
				if (s.charAt(l) == s.charAt(r)) {
					l++; r--;
				} else break;
			}
			// 회문인 경우
			if (l > r) {
				sb.append(res).append("\n");
				continue;
			}
			// 유사회문인지 확인
			// 위 경우가 아니라면, 현재 l번째 문자와 r번째 문자가 다른 상황이므로,
			// l번째 문자를 삭제하거나 r번째 문자를 삭제해야 한다.
			res++;
			int L = l; int R = r;
			// l번째 문자를 삭제하는 경우
			l++;
			while (l <= r) {
				if (s.charAt(l) == s.charAt(r)) {
					l++; r--;
				} else break;
			}
			// l번째 문자를 삭제했을 때 유사회문인 경우
			if (l > r) {
				sb.append(res).append("\n");
				continue;
			}
			// r번째 문자를 삭제하는 경우
			l = L; r = R;
			r--;
			while (l <= r) {
				if (s.charAt(l) == s.charAt(r)) {
					l++; r--;
				} else break;
			}
			// r번째 문자를 삭제했을 때 유사회문인 경우
			if (l > r) {
				sb.append(res).append("\n");
				continue;
			}
			// 일반 문자열인 경우
			res++;
			sb.append(res).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
