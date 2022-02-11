import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1874_스택_수열 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<>();
		// 출력 문자열을 저장한다.
		StringBuilder sb = new StringBuilder();
		// 스택에 넣은 마지막 숫자
		int m = 0;
		// 입력으로 받은 수열을 만들 수 있는지 여부를 나타낸다.
		boolean possible = true;
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			// 입력으로 받은 숫자가 스택에 넣은 마지막 숫자보다 크면 스택에 입력으로 받은 숫자까지 넣으면 된다.
			if (m < n) {
				while (m < n) {
					st.push(++m);
					sb.append("+\n");
				}
			// 입력으로 받은 숫자가 스택의 top에 있는 숫자와 불일치하면 수열을 만들 수 없다.
			} else if (n != st.peek()){
				possible = false;
				break;
			}
			// 위 과정을 거치면 스택의 top에는 입력으로 받은 숫자가 있게 된다.
			st.pop();
			sb.append("-\n");
		}
		if (possible) {
			System.out.println(sb.toString());
		} else {
			System.out.println("NO");
		}
	}

}
