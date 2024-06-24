

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2412_암벽_등반 {
	static class Location {
		int x, y;
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		// 각 높이마다 존재하는 홈의 x좌표들을 모아놓는 map
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.putIfAbsent(y, new ArrayList<>());
			map.get(y).add(x);
		}
		// 오름차순 정렬
		for (int y : map.keySet()) {
			Collections.sort(map.get(y));
		}
		// BFS 수행을 통해 최소 이동 시간을 구한다.
		Queue<Location> q = new LinkedList<>();
		q.offer(new Location(0, 0));
		int time = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Location u = q.poll();
				// 정상에 도달한 경우
				if (u.y == T) {
					System.out.println(time);
					return;
				}
				for (int y = Math.max(u.y - 2, 0); y <= Math.min(u.y + 2, T); y++) {
					if (!map.containsKey(y)) continue;
					for (int i = 0; i < map.get(y).size(); i++) {
						int x = map.get(y).get(i);
						if (u.x + 2 < x) break;
						else if (u.x - 2 > x) continue;
						map.get(y).remove(i);
						q.offer(new Location(x, y));
						i--;
					}
				}
				qLen--;
			}
			time++;
		}
		System.out.println(-1);
	}

}
