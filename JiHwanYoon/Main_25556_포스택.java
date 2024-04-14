

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25556_포스택 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stacks = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// greedy algorithm을 활용
		// 네 개의 스택 중 가장 위에 있는 값이 현재 넣으려는 값보다 작은 스택에 집어넣는다.
		for (int i = 0; i < N; i++) {
			int A = Integer.parseInt(st.nextToken());
			boolean pos = false;
			for (int j = 0; j < 4; j++) {
				if (stacks[j] < A) {
					stacks[j] = A;
					pos = true;
					break;
				}
			}
			// 네 개의 스택 모두 숫자를 집어넣을 수 없는 경우
			if (!pos) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

}
