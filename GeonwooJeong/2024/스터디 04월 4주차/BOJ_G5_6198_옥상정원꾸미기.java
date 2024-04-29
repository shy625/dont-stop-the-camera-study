import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_G5_6198_옥상정원꾸미기 {
	static class Building {
		int idx;
		int height;

		public Building(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}

	}
	static long ans;
	static Stack<Building> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stack = new Stack<Building>();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());

			check(i, n);

			stack.add(new Building(i, n));
		}
		
		check(N, Integer.MAX_VALUE);

		System.out.println(ans);

	}

	private static void check(int nowIdx, int nowHeight) {
		while (!stack.isEmpty()) {
			int top = stack.peek().height;
			if (top <= nowHeight) {
				Building pop = stack.pop();
				ans += nowIdx - pop.idx - 1;
			} else {
				break;
			}
		}
		
	}

}
