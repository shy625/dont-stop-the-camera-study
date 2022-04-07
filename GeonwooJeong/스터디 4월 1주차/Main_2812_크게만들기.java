import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_크게만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 숫자를 한 개씩 사용하기 위한 배열
		char [] cs = br.readLine().toCharArray();
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			// char 타입을 int 타입으로 변환
			int n = cs[i] - '0';
			
			// i번째 숫자 n이 스택 안의 숫자보다 더 크고, 지울 수 있는 갯수가 남아있고,
			// 스택이 비어있지 않다면 해당하는 숫자를 스택에서 꺼내면서 지운 갯수를 카운팅한다.
			while(stack.peek() < n && cnt < K && !stack.isEmpty()) {
				stack.pop();
				cnt++;
			}
			// 지울 수 있는 갯수를 모두 사용했거나 새로 들어온 수가 더 작다면 그냥 add 해준다.
			stack.add(n);
		}
		
		// 스택은 LIFO 구조이므로 뒤집어 주기 위해 StringBuilder를 사용함
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		// sb를 뒤집어서 출력
		System.out.println(sb.reverse().toString());
	}

}
