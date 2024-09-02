

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_1599_민식어 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		// 민식어를 영어로 바꿔서 정렬한다.
		Map<String, Character> map = new HashMap<>(); // 민식어를 영어로 변환하기 위한 HashMap
		String[] newChars = {"a", "b", "k", "d", "e", "g", "h", "i", "l", "m", "n", "ng", "o", "p", "r", "s", "t", "u", "w", "y"};
		for (int i = 0; i < 20; i++) {
			map.put(newChars[i], (char)('a' + i));
		}
		String[] transWords = new String[N]; // 민식어를 영어로 변환한 결과를 저장하는 배열
		for (int i = 0; i < N; i++) {
			String s = words[i];
			String transWord = "";
			for (int j = 0; j < s.length(); j++) {
				// ng 변환 시 주의
				if (j < s.length()-1 && s.charAt(j) == 'n' && s.charAt(j+1) == 'g') {
					transWord += map.get("ng");
					j++;
				} else {
					transWord += map.get(s.charAt(j)+"");
				}
			}
			transWords[i] = transWord;
		}
		Arrays.sort(transWords); // 정렬
		// 민식어로 재변환 후 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String transWord = transWords[i];
			for (int j = 0; j < transWord.length(); j++) {
				sb.append(newChars[transWord.charAt(j)-'a']);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
