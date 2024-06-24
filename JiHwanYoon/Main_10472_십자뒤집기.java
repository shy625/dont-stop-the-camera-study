

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10472_십자뒤집기 {
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int t = 1; t <= P; t++) {
			// 만들고자 하는 보드를 이진수로 표현
			int end = 0;
			for (int i = 0; i < 3; i++) {
				String s = br.readLine();
				for (int j = 0; j < 3; j++) {
					if (s.charAt(j) == '*') end += 1 << (3*i + j);
				}
			}
			// 너비 우선 탐색을 진행한다.
			// 단, 보드의 상태를 이진수로 표현한다.
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[1 << 9];
			q.offer(0);
			visited[0] = true;
			int cnt = 0; // 최소 클릭 횟수
			while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int u = q.poll();
					// 만들고자 하는 보드를 완성시킨 경우
					if (u == end) {
						sb.append(cnt).append("\n");
						continue outer;
					}
					// 보드의 각 칸을 클릭
					for (int i = 0; i < 9; i++) {
						int r = i/3;
						int c = i%3;
						int v = u;
						// 각 칸과, 각 칸에 인접한 네 칸을 뒤집는다.
						for (int d = 0; d < 5; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (check(nr, nc)) {
								v ^= (1 << (nr*3 + nc));
							}
						}
						if (!visited[v]) {
							visited[v] = true;
							q.offer(v);
						}
					}
					qLen--;
				}
				cnt++;
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<3 && 0<=c && c<3;
	}

}
