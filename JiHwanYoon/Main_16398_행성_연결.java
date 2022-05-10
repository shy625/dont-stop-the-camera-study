

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16398_행성_연결 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dense한 graph이므로 Prim's Algorithm을 적용한다.
		int[] prim = new int[N];
		Arrays.fill(prim, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		pq.offer(new int[] {0, 0});
		boolean[] visited = new boolean[N];
		int cnt = 0;
		// 플로우 관리 비용의 총합이 Integer.MAX_VALUE를 넘어설 수 있기 때문에 결과는 long 타입으로 설정한다.
		long cost = 0;
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			cnt++;
			cost += u[1];
			if (cnt == N) break;
			for (int i = 0; i < N; i++) {
				if (u[0] == i) continue;
				if (!visited[i] && prim[i] > graph[u[0]][i]) {
					prim[i] = graph[u[0]][i];
					pq.offer(new int[] {i, prim[i]});
				}
			}
		}
		System.out.println(cost);
	}

}
