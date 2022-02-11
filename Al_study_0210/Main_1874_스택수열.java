import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1874_스택수열 {
	static int N;
	static boolean p = true;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int input = 1;
		
		for (int i = 0 ; i < N ; i ++) {
			int target = Integer.parseInt(br.readLine());
			if (stack.empty() || stack.peek() < target) {
				while (input <= target) {
					stack.push(input++);
					sb.append("+").append("\n");
				}
				stack.pop();
				sb.append("-").append("\n");
			}else if (stack.peek() == target) {
				stack.pop();
				sb.append("-").append("\n");
			}else {
				sb.setLength(0);
				sb.append("NO");
				break;
			}
		}
		
		System.out.println(sb.toString());
		
	}
}
