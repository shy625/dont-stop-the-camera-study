

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_6503_망가진_키보드 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int m = Integer.parseInt(br.readLine());
			if (m == 0) break;
			String s = br.readLine();
			// 투 포인터 알고리즘 활용
			int N = s.length();
			int max = 0; // m개 이하의 문자를 포함하는 가장 긴 부분 문자열의 길이
			int l = 0; // 현재 탐색 중인 문자열의 왼쪽 끝
			int r = 0; // 현재 탐색 중인 문자열의 오른쪽 끝
			Map<Character, Integer> map = new HashMap<>(); // 현재 탐색 중인 문자열에 있는 각 문자들의 개수
			int cnt = 0; // 현재 탐색 중인 문자열에 있는 서로 다른 문자의 개수
			while (l <= r && l < N) {
				if (r < N) {
					// 다음 오른쪽 문자가 이미 문자열 내에 존재하고, 서로 다른 문자가 m개 이하인 경우 
					// 다음 오른쪽 문자를 문자열에 포함
					if (map.containsKey(s.charAt(r)) && map.get(s.charAt(r)) > 0 && cnt <= m) {
						map.put(s.charAt(r), map.get(s.charAt(r))+1);
						r++;
						max = Math.max(max, r-l);
					// 다음 오른쪽 문자가 문자열 내에 존재하지 않으나 서로 다른 문자가 m개 미만이라 문자열에 포함시킬 수 있는 경우
					// 다음 오른쪽 문자를 문자열에 포함
					} else if ((!map.containsKey(s.charAt(r)) || map.get(s.charAt(r)) == 0) && cnt < m) {
						map.put(s.charAt(r), 1);
						r++;
						cnt++;
						max = Math.max(max, r-l);
					// 나머지 경우에 대해서는 왼쪽 문자를 문자열에서 제외
					} else {
						map.put(s.charAt(l), map.get(s.charAt(l))-1);
						if (map.get(s.charAt(l)) == 0) cnt--;
						l++;
					}
				} else {
					map.put(s.charAt(l), map.get(s.charAt(l))-1);
					if (map.get(s.charAt(l)) == 0) cnt--;
					l++;
				}
			}
			sb.append(max).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
