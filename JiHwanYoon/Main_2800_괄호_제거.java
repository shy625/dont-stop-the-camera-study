

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Main_2800_괄호_제거 {
	static String s;
	static int[] pair; // 각 괄호의 짝을 알아내기 위한 배열
	static HashSet<String> set; // 괄호를 제거한 결과들을 저장하는 집합(중복 제거를 위해 set으로 정의)
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		set = new HashSet<>();
		// 각 괄호의 짝이 어떤 괄호인지를 알아내기 위해 스택을 사용
		Stack<int[]> stack = new Stack<>();
		pair = new int[s.length()];
		// 각 괄호를 구분하기 위해 사용하는 정수
		int k = 1;
		for (int i = 0; i < s.length(); i++) {
			// 닫는 괄호를 발견하면 스택에서 여는 괄호, 즉 닫는 괄호와 짝이 되는 괄호를 찾을 때까지 스택에서 pop한다.
			// 이를 통해 닫는 괄호와 짝이 되는 여는 괄호를 발견하면 해당 괄호를 k번째 괄호라고 pair 배열에 저장한다.
			if (s.charAt(i) == ')') {
				while (!stack.isEmpty() && stack.peek()[1] != '(') stack.pop();
				int j = stack.pop()[0];
				pair[i] = pair[j] = k++; 
			} else {
				stack.push(new int[] {i, s.charAt(i)}); // 기타 경우는 스택에 push
			}
		}
		// 위에서 발견한 k개의 괄호 쌍에 대해 각 괄호를 제거하거나 그대로 두는 경우를 고려해본다.
		subset(1, k, new StringBuilder(s));
		// 괄호를 제거한 모든 경우들을 오름차순으로 출력하기 위해 집합을 배열로 바꿔준다.
		String[] strArr = set.toArray(new String[set.size()]);
		Arrays.sort(strArr); // 오름차순으로 정렬
		// 괄호를 제거하지 않은 경우(str의 길이가 입력으로 받은 문자열과 같은 경우)를 빼고 모두 출력
		for (String str : strArr) {
			if (str.length() != s.length()) System.out.println(str);
		}
	}
	// 부분집합 완전탐색을 활용
	private static void subset(int i, int limit, StringBuilder sb) {
		// 모든 괄호를 다 고려한 경우 제거 표시를 한 괄호를 제거하고 set에 넣는다.
		if (i == limit) {
			StringBuilder sb2 = new StringBuilder();
			for (char c : sb.toString().toCharArray()) {
				if (c != 'X') sb2.append(c);
			}
			set.add(sb2.toString());
			return;
		}
		// i번째 괄호의 시작 인덱스와 끝 인덱스
		int start = -1;
		int end = -1;
		// pair를 탐색하면서 i번째 괄호가 어디 있는지를 찾는다.
		for (int k = 0; k < s.length(); k++) {
			if (pair[k] != i) continue;
			if (start == -1) start = k;
			else if (end == -1) { // start와 end까지 다 찾은 경우(항상 start가 end보다 작으므로)
				StringBuilder temp = new StringBuilder(sb);
				end = k;
				subset(i+1, limit, temp); // 괄호를 제거하지 않는 경우
				// 괄호를 바로 제거하지 않고 제거할 괄호를 X로 표시한다. 바로 제거하면 문자열의 길이가 변해 문제가 생길 수 있다.
				temp = new StringBuilder(sb.substring(0, start)).append("X").
						append(sb.substring(start+1, end)).append("X").append(sb.substring(end+1));
				subset(i+1, limit, temp);
			}

		}
	}
	
}
