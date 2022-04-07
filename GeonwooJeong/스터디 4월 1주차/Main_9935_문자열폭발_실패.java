import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열폭발_실패 {

	public static void main(String[] args) throws IOException {
		// 문제를 잘못 보고 풀어서 실패 (폭발이 한 번만 일어나는 것으로 이해함)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			stack.add(str.charAt(i));
		}

		String str2 = br.readLine();
		char [] cs = str2.toCharArray();
		
		while (!stack.isEmpty()) {
			boolean flag = true;
			char c = stack.pop();
			for (int i = 0; i < cs.length; i++) {
				if (c == cs[i]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				sb.append(c);
			}
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.reverse().toString());
		}
		
	}

}
