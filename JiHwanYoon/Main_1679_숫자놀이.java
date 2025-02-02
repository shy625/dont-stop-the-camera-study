

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1679_숫자놀이 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int K = Integer.parseInt(br.readLine());
		// 너비 우선 탐색을 활용해 서로 다른 N개의 숫자를 K번 활용해 만들 수 있는 모든 숫자들을 구한다.
		// 이때, 최대 1000을 50번 활용할 수 있으므로 만들 수 있는 숫자의 최댓값은 50,000임을 이용해 방문 배열 생성
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[50_001];
		// 초기화
		for (int i = 0; i < N; i++) {
			q.offer(nums[i]);
			visited[nums[i]] = true;
		}
		int k = 1; // 현재 숫자를 이용한 횟수
		while (k < K) {
			int qLen = q.size();
			while (qLen > 0) {
				int n = q.poll();
				for (int i = 0; i < N; i++) {
					int next = n + nums[i];
					// 이전에 만든 이력이 있는 경우, 즉 더 적은 횟수를 이용해 해당 숫자를 만든 경우 더 고려하지 않는다.
					// 효율성을 높이기 위함
					if (visited[next]) continue;
					visited[next] = true;
					q.offer(next);
				}
				qLen--;
			}
			k++;
		}
		// 방문 배열 내 숫자를 순회해 처음으로 방문하지 않은 숫자가 나온 경우
		// 해당 숫자가 짝수면 홀순이, 홀수면 짝순이 승리한다.
		for (int n = 1; n <= 50_000; n++) {
			if (!visited[n]) {
				if (n%2 == 0) {
					System.out.println("holsoon win at " + n);
				} else {
					System.out.println("jjaksoon win at " + n);
				}
				System.exit(0);
			}			
		}
	}

}
