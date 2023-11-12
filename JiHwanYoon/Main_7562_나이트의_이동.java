

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7562_나이트의_이동 {
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int l = Integer.parseInt(br.readLine());
			int[] start = new int[2];
			int[] end = new int[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			// BFS를 통해 최단 거리를 계산
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[l][l];
			q.offer(start);
			visited[start[0]][start[1]] = true;
			int time = 0; // start에서 출발해 end까지 도달하기 위한 최소 이동 횟수
			outer: while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int[] u = q.poll();
					if (u[0] == end[0] && u[1] == end[1]) break outer;
					for (int d = 0; d < 8; d++) {
						int nr = u[0] + dr[d];
						int nc = u[1] + dc[d];
						if (0<=nr && nr<l && 0<=nc && nc<l && !visited[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					}
					qLen--;
				}
				time++;
			}
			sb.append(time).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
