import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812_크게만들기_시간초과 {

	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>();
		char[] ch = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			stack.add(ch[i] - '0');
		}

		// 마지막부터 꺼내서 k
		int size = N - K;
		ArrayList<Integer> answer = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {// 일단 뒤에서부터 사이즈만큼 넣어놓기
			answer.add(0, stack.pop());
		}

		int index = 0;
		for (int i = 0; i < K; i++) {// K번 반복
			int now = stack.pop();

			if (now >= answer.get(0)) {// 교체가 일어난다.
				if (index != size - 1) {// 끝까지 정렬 완료
					for (int j = index; j < size - 1; j++) {
						if (index == size - 2) {
							index++;
							break;
						}
						if (answer.get(j) < answer.get(j + 1)) {
							index = j;
							break;
						}
					}
				}

				answer.remove(index);
				answer.add(0, now);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(answer.get(i));
		}
		System.out.println(sb.toString());
	}

}
