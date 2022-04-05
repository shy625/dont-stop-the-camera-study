

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열_폭발 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		int N = t.length();
		// 문자열 조사를 위한 스택
		Stack<Character> stack = new Stack<>();
		// 문자열 폭발을 보조하는 스택
		Stack<Character> tempS = new Stack<>();
		for (char c : s.toCharArray()) {
			stack.push(c);
			// 현재 조사한 문자가 폭발시키려는 문자열의 끝 원소와 같으면 한 번 폭발을 시도해본다.
			if (c == t.charAt(N-1)) {
				int temp = N-1; // 문자열 제거 수
				while (temp >= 0 && !stack.isEmpty() && stack.peek() == t.charAt(temp)) {
					tempS.push(stack.pop());					
					temp--;
				}
				// 만약 temp가 0보다 크거나 같으면 문자열 폭발이 제대로 이루어지지 않았으므로 다시 stack에 집어넣는다.
				if (temp >= 0) {
					while (!tempS.isEmpty()) {
						stack.push(tempS.pop());
					}
				// 문자열 폭발이 제대로 이뤄졌으면 tempS를 비워준다.
				} else {
					tempS.clear();
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		// 문자열 폭발 결과 출력을 위해 stack에서 문자들을 뽑아 sb에 추가한다.
		// 이때 결과 문자열이 거꾸로 저장되므로 뒤집어준다.
		while (!stack.isEmpty()) sb.append(stack.pop());
		sb.reverse();
		System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());

	}

}
