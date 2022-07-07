

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1039_교환 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		// 입력으로 받은 숫자를 정수로 변환하기 전에 정수의 자릿수를 알아낸다.
		int M = s.length();
		int N = Integer.parseInt(s);
		int K = Integer.parseInt(st.nextToken());
		// BFS를 이용해 연산을 수행한다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited;
		q.offer(N);
		for (int k = 1; k <= K; k++) {
			int qLen = q.size();
			// 방문 배열은 1번 연산할 동안 같은 숫자를 여러 번 방문하는 것을 막기 위해서 만들기 때문에,
			// 기존의 숫자를 가지고 새 연산을 수행할 때는 방문 배열을 초기화해준다.
			// 123 -> 132 -> 123과 같은 연산이 가능하기 때문이다.
			// 1,000,000 이상의 숫자는 절대 연산을 통해 나올 일이 없으므로, 배열의 길이의 최대값은 1,000,001이다.
			visited = new boolean[Math.min((int)(Math.pow(10, M)), 1_000_000)+1];
			while (qLen > 0) {
				int u = q.poll();
				// i번째 숫자와 j번째 숫자를 교환
				for (int i = 0; i < M; i++) {
					int pow_i = (int)Math.pow(10, i); // 10^i
					int num_i = (u/pow_i)%10; // i번째 자리수
					for (int j = i+1; j < M; j++) {
						int pow_j = (int)Math.pow(10, j); // 10^j
						int num_j = (u/pow_j)%10; // j번째 자리수
						// j번째 자리가 맨 앞이면서 i번째 자리수가 0인 경우 연산 불가
						if (j == M-1 && num_i == 0) continue;
						// 연산 수행
						int next = u-pow_i*num_i-pow_j*num_j+pow_i*num_j+pow_j*num_i;
						// 아직 같은 연산 동안 방문하지 않은 수라면 연산 결과를 큐에 넣는다.
						if (!visited[next]) {
							visited[next] = true;
							q.offer(next);
						}
					}
				}
				qLen--;
			}
			// 연산을 통해 만들 수 있는 수가 존재하지 않을 때
			if (q.size() == 0) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		// K번 연산을 통해 만들 수 있는 수의 최대값을 구한다.
		int max = -1;
		while (!q.isEmpty()) {
			max = Math.max(max, q.poll());
		}
		System.out.println(max);
	}

}
