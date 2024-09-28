

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17394_핑거_스냅 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1부터 100,000 사이의 소수를 파악한다.
		boolean[] isPrime = new boolean[100_001];
		Arrays.fill(isPrime, true);
		for (int i = 2; i <= Math.sqrt(100_001); i++) {
			for (int j = 2; i*j <= 100_000; j++) {
				isPrime[i*j] = false;
			}
		}
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean[] visited = new boolean[1_000_000+3];
		outer: for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// BFS를 통해 N이 A와 B 사이의 소수가 되기 위한 최소 핑거 스냅의 횟수를 구한다.
			Queue<Integer> q = new LinkedList<>();
			Arrays.fill(visited, false);
			q.offer(N);
			visited[N] = true;
			int cnt = 0;
			while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int n = q.poll();
					// n이 A와 B 사이의 소수가 된 경우
					if (A <= n && n <= Math.min(B, 100_000) && isPrime[n]) {
						sb.append(cnt).append("\n");
						continue outer;
					}
					if (!visited[n/2]) {
						visited[n/2] = true;
						q.offer(n/2);
					}
					if (!visited[n/3]) {
						visited[n/3] = true;
						q.offer(n/3);
					}
					if (n < 1_000_000+2 && !visited[n+1]) {
						visited[n+1] = true;
						q.offer(n+1);
					}
					if (n > 0 && !visited[n-1]) {
						visited[n-1] = true;
						q.offer(n-1);
					}
 					qLen--;
				}
				cnt++;
			}
			// 목표범위 내 소수로 만들 수 없는 경우
			sb.append(-1).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
