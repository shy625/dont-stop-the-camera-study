import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1956_프린터_큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 각 테스트별 입력
			String[] strArr = br.readLine().split(" ");
			int N = Integer.parseInt(strArr[0]);
			int M = Integer.parseInt(strArr[1]);
			// 가장 높은 우선순위
			int max_priority = -1;
			// 각 문서들의 우선순위
			int[] priorities = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				priorities[i] = Integer.parseInt(st.nextToken());
				max_priority = Math.max(max_priority, priorities[i]);
			}
			// 현재 보고 있는 문서 인덱스
			int idx = 0;
			// 문서를 출력할 경우 몇 번째로 출력되었는지를 나타내는 변수
			int order = 0;
			while (true) {
				// 현재 보고 있는 문서가 가장 높은 우선 순위를 가지면
				if (priorities[idx] == max_priority) {
					// 해당 문서의 우선순위를 음수로 낮춰 더 이상 고려하지 않는다.
					priorities[idx] = -1;
					order += 1;
					// 출력하고자 하는 문서가 원하는 문서면 while 문을 빠져 나온다.
					if (idx == M) break;
					// 가장 높은 우선순위 갱신
					max_priority = -1;
					for (int priority: priorities) {
						max_priority = Math.max(max_priority, priority);
					}
				}
				// FIFO 방식으로 문서 탐색
				idx = (idx+1)%N;
			}
			System.out.println(order);
		}
	}

}
