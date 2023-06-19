import java.util.Scanner;

public class BOJ_S1_1522_문자열교환 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		String str = scann.next();
		// 슬라이딩 윈도우 알고리즘을 사용할 때, 윈도우의 길이를 저장할 변수
		int trade = 0;
		int ans = 1001;
		
		// a를 모두 연속으로 만들어야 하므로, 문자열 속 a의 개수만큼을 윈도우의 길이로 지정한다.
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == 'a') trade++;
		}
		
		// 슬라이딩 윈도우 알고리즘
		for (int i = 0; i < str.length(); i++) {
			int cnt = 0;
			for (int j = i; j < i+trade; j++) {
				// 처음과 끝은 서로 인접해있기 때문에 % 연산자 사용
				int idx = j % str.length();
				char c = str.charAt(idx);
				// 윈도우 속 b의 개수만큼 교환을 해야한다.
				if(c == 'b') cnt++;
			}
			ans = Math.min(ans, cnt);
		}
		
		System.out.println(ans);

	}

}
