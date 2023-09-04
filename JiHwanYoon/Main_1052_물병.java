

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1052_물병 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int n = 0; // 사야 하는 물병의 최소 개수
		// 비트마스킹을 활용한다.
		// N을 이진수로 표현한 값에서 1의 개수가 K개 이하가 될 떄까지 N에 1을 계속 더한다.
		while (true) {
			int cnt = 0;
			// N+n의 이진수 표현에서 1의 개수를 센다.
			for (int i = 0; (1 << i) <= (N+n); i++) {
				if (((1 << i) & (N+n)) != 0) cnt++;
			}
			// 1의 개수가 K개 이하면 n을 출력한다.
			if (cnt <= K) {
				System.out.println(n);
				break;
			}
			n++;
		}
	}

}
