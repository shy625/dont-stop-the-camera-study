
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298_오큰수 {
	/**
	 * 전체적인 아이디어
	 * 1. 입력으로 받은 수를 스택에 넣어 오큰수를 기다리게 합니다.
	 * 2. 단, 저장하기 전에 자신이 자기 앞에 있는 수들의 오큰수가 될 수 있으므로, 스택에서 오큰수를 기다리고 있는, 
	 * 자기보다 작은 수들을 차례로 꺼내면서 해당 수들에 대한 오큰수를 지정해 줍니다.
	 * 3. 그러다가 자기보다 크거나 같은 수를 만나면 그 수와 그 수 이전에 대한 수에 대해서는 자신이 오큰수가 될 수 없으므로, 2번 작업을 중지합니다.
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 오큰수를 기다리는 수와 인덱스를 {인덱스, 수} 배열 형태로 저장하는 스택
		Stack<int[]> stack = new Stack<>();
		// 결과값 저장하는 배열
		int[] res = new int[N+1];
		// 초기값으로 오큰수 미지정 시 값인 -1로 초기화합니다.
		Arrays.fill(res, -1);
		for (int i = 1; i < N+1; i++) {
			// i번째 수 입력
			int n = Integer.parseInt(st.nextToken());
			// 입력으로 받은 수가 오큰수가 될 수들을 스택에서 꺼내줍니다.
			if (!stack.isEmpty() && stack.peek()[1] < n) {
				while (!stack.isEmpty() && stack.peek()[1] < n) {
					// 스택에서 꺼낸 수들의 오큰수를 지정합니다.
					int[] popped = stack.pop();
					res[popped[0]] = n;
				}
			}
			// 오큰수 지정이 끝나면 자기 자신도 오큰수 지정을 위해 스택에 들어갑니다.
			stack.push(new int[] {i, n});
		}
		// 출력 : N이 최대 100만이 되기 때문에 단순 출력을 이용하면 시간 초과가 발생합니다...
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N+1; i++) {
			sb.append(res[i]+" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
