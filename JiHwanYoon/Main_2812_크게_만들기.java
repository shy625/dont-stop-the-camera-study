

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_크게_만들기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		// 문자열을 조사하기 위한 스택
		Stack<Character> stack = new Stack<>();
		// 제거 횟수
		int removeCnt = 0;
		for (int i = 0; i < N; i++) {
			// 현재 찾은 수가 stack의 끝에 있는 수보다 큰 경우 제거한다.
			while (removeCnt < K && !stack.isEmpty() && stack.peek() < s.charAt(i)) {
				stack.pop();
				removeCnt++;
			}
			stack.push(s.charAt(i));
		}
		// 위 규칙대로 했음에도 불구하고 K개만큼 제거하지 못했으면 맨 끝자리 수부터 제거한다.
		if (removeCnt < K) {
			while (removeCnt < K) {
				stack.pop();
				removeCnt++;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		sb.reverse();
		System.out.println(sb.toString());
	}

}
