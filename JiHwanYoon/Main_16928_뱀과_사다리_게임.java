

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16928_뱀과_사다리_게임 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 뱀 또는 사다리가 위치한 지점들을 저장
		Map<Integer, Integer> points = new HashMap<>();
		for (int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			points.put(s, e);
		}
		// BFS 수행
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[101];
		q.offer(1);
		visited[1] = true;
		int cnt = 0;
		outer: while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				// 100번 칸에 도착하면 끝
				if (u == 100) break outer;
				// 주사위 던짐
				for (int d = 1; d <= 6; d++) {
					int next = u + d;
					if (next>=1 && next<=100 && !visited[next]) {
						// 도착한 지점이 뱀 또는 사다리가 있는 지점일 때 
						if (points.containsKey(next) && !visited[points.get(next)]) {
							visited[points.get(next)] = true;
							q.offer(points.get(next));
						// 일반 지점일 때
						} else if (!points.containsKey(next)) {
							visited[next] = true;
							q.offer(next);
						}
					}
				}
				qLen--;
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
