import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1874_스택수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // NO일 경우 +- 출력을 안해서 SB 사용함
		Stack<Integer> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		int index = 0; // 입력받은 숫자와 비교하기 위한 변수(이전에 빼낸 값)
		boolean flag = false;
		
		for (int i = 1; i <= N ; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num>index) {
				while(!(index==num)) { // index와 입력받은 num이 같아질 때 까지 push 해줌
					index++;
					stack.push(index);
					sb.append('+').append('\n');
				}
			} else if (stack.peek() != num) { // num이 index보다 작고, top이 아닐 때 NO 출력
				System.out.println("NO");
				return; // 더이상 코드를 진행하지 않아도 되므로 return;
			}
			
			stack.pop(); // 다음 입력으로 넘어가기 전에 pop을 하고 -출력을 append 해줌
			sb.append('-').append('\n');
		}
		
		System.out.print(sb.toString());

	}

}
