

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_28069_김밥천국의_계단 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// BFS를 이용해 K번의 이동 안에 N에 도달할 수 있는지를 확인한다.
		// 0번에서 순간이동하면 제자리로 이동하기 때문에, K번의 이동 안에 N에 도달할 수 있다면
		// 나머지 이동은 순간이동으로 채우면 된다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(0);
		visited[0] = true;
		int cnt = 0;
		while (!q.isEmpty() && cnt++ <= K) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				if (u == N) {
					System.out.println("minigimbob");
					return;
				}
				if (u+1 <= N && !visited[u+1]) {
					visited[u+1] = true;
					q.offer(u+1);
				}
				if (u+u/2 <= N && !visited[u+u/2]) {
					visited[u+u/2] = true;
					q.offer(u+u/2);
				}
				qLen--;
			}
		}
		System.out.println("water");
	}

}
