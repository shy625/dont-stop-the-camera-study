

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_6198_옥상_정원_꾸미기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 스택을 활용
		// 건물이 왼쪽부터 하나씩 주어질 때마다, 해당 건물을 볼 수 있는 건물의 개수를 구하기 위해
		// 스택에서 주어진 건물의 높이보다 작거나 같은 건물의 높이를 빼내고,
		// 자기 자신의 건물 높이를 넣으면 (스택의 크기-1)개의 건물이 해당 건물을 볼 수 있다.
		Stack<Integer> stack = new Stack<>();
		long cnt = 0; // 결과값은 최대 (80000-1)*(80000-2)/2로 정수 범위를 넘을 수 있다.
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());
			while (!stack.isEmpty() && h >= stack.peek()) {
				stack.pop();
			}
			stack.push(h);
			cnt += stack.size()-1;
		}
		System.out.println(cnt);
	}

}
