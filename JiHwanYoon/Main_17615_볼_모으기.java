

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17615_볼_모으기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		// 볼 이동횟수의 최솟값
		int min = Integer.MAX_VALUE;
		// 1. 파란색 또는 빨간색 볼을 모두 왼쪽으로 옮기는 경우
		// 첫번째 공의 색과 같은 공을 왼쪽으로 옮기는 경우에는 첫번째 공 직후로 연속해서 나오는 같은 색의 공은 무시하다가 
		// 다른 색의 공이 한 번이라도 나오면 그 이후로 나오는 공들은 옮겨준다.
		char c = s.charAt(0);
		boolean check = false;
		int same = 0;
		int diff = 0;
		for (int i = 0; i < N; i++) {
			if (c != s.charAt(i)) check = true;
			if (c == s.charAt(i) && check) same++;
			if (c != s.charAt(i)) diff++;
		}
		// 2. 파란색 또는 빨간색 볼을 모두 오른쪽으로 옮기는 경우
		// 위와 같은 방식을 적용한다.
		min = Math.min(same, diff);
		c = s.charAt(N-1);
		check = false;
		same = 0;
		diff = 0;
		for (int i = N-1; i >= 0; i--) {
			if (c != s.charAt(i)) check = true;
			if (c == s.charAt(i) && check) same++;
			if (c != s.charAt(i)) diff++;
		}
		min = Math.min(same, Math.min(min, diff));
		System.out.println(min);

	}
}
