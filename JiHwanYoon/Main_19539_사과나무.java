

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19539_사과나무 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 핵심은, 2만큼 성장시키는 물뿌리개를 사용할 수 있는지를 알아내야 한다.
		// 물을 뿌리는 횟수가 X번일 때, 총 높이는 3*X이고, 1번 물뿌리개 사용 횟수와 2번 물뿌리개 사용 횟수가 각각 X번이 되어야 한다.
		// 따라서 높이의 총합이 3의 배수이면서, 2번 물뿌리개를 사용할 수 있는 횟수가 X번 이상이어야 한다.
		// 2번 물뿌리개를 사용할 수 있는 횟수는 각 나무마다 원하는 높이를 2로 나눈 몫을 통해 구할 수 있다.
		// X는 높이의 총합을 3으로 나눈 몫을 통해 구할 수 있다.
		int cnt = 0; // 2번 물뿌리개 사용 가능 횟수
		int sum = 0; // 원하는 나무의 높이의 총합
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			cnt += cur/2;
			sum += cur;
		}
		if (sum%3 == 0 && cnt >= sum/3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
