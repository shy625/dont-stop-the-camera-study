

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1612_가지고_노는_1 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// N이 2의 배수이거나 5의 배수이면 일의 자리가 1인 숫자를 절대 만들 수 없다.
		// 그렇지 않으면, 1로만 이루어진 숫자 중 N의 배수가 되는 것을 항상 찾을 수 있는데,
		// 이를 찾기 위해 단순히 1을 계속 늘려나가면서 확인하는 게 아니라, 1을 늘렸을 때 구한 나머지에 10을 곱한 뒤 1을 더하는 식으로
		// 진행하면서 언제 N의 배수가 되는지 찾는다.
		if (N%2 == 0 || N%5 == 0) {
			System.out.println(-1);
		} else {
			int cur = 0;
			int i = 1;
			while (true) {
				cur = (cur*10+1)%N;
				if (cur%N == 0) {
					System.out.println(i);
					break;
				}
				i++;
			}
		}
	}

}
