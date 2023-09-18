import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_S2_2716_원숭이매달기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < N; t++) {
			// 괄호 쌍을 맞추기 위해 스택을 사용
			Stack<Character> stack = new Stack<>();
			String str = br.readLine();
			// 현재 덩굴의 깊이를 나타낼 변수
			int level = 0;
			// 덩굴의 최대 깊이를 나타낼 변수
			int max = 0;
			
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				
				// 여는 괄호가 나오면 스택에 저장하고 덩굴의 깊이를 +1해준다. 최대 깊이도 업데이트 해준다.
				if(c == '[') {
					stack.add(c);
					level++;
					max = Math.max(max, level);
				// 닫는 괄호가 나오면 스택에서 여는 괄호를 꺼내 균형을 맞추고, 덩굴의 깊이를 -1해준다.
				} else {
					if(!stack.isEmpty()) {
						stack.pop();
						level--;
					}
				}
			}
			
			// 최대 깊이가 n일 때, 원숭이는 최소 2^n마리가 필요하다.
			sb.append(((int)Math.pow(2, max))+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
		
	}

}
