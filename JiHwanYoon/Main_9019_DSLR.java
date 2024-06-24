

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019_DSLR {
	
	// 레지스터 값과 해당 값에 도달하기 위해 내린 명령을 저장하는 클래스
	static class Register {
		int n;
		StringBuilder sb;
		public Register(int n, StringBuilder sb) {
			this.n = n;
			this.sb = sb;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder res = new StringBuilder();
		outer: for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// A에서 B까지의 '최단 거리'를 탐색하기 위해 BFS 활용
			Queue<Register> q = new LinkedList<>();
			boolean[] visited = new boolean[10000];
			visited[A] = true;
			q.offer(new Register(A, new StringBuilder()));
			while (!q.isEmpty()) {
				Register u = q.poll();
				int cur = u.n;
				StringBuilder cur_sb = u.sb;
				// B에 도달하면 BFS를 끝낸다.
				if (u.n == B) {
					res.append(u.sb).append("\n");
					continue outer;
				}
				// D 명령
				if (!visited[(cur*2)%10000]) {
					visited[(cur*2)%10000] = true;
					StringBuilder temp = new StringBuilder(cur_sb).append('D');
					q.offer(new Register((cur*2)%10000, temp));
				}
				// S 명령
				if (!visited[(cur+9999)%10000]) {
					visited[(cur+9999)%10000] = true;
					StringBuilder temp = new StringBuilder(cur_sb).append('S');
					q.offer(new Register((cur+9999)%10000, temp));
				}
				// L 명령
				int l = (cur*10)%10000 + cur/1000;
				if (!visited[l]) {
					visited[l] = true;
					StringBuilder temp = new StringBuilder(cur_sb).append('L');
					q.offer(new Register(l, temp));
				}
				// R 명령
				int r = cur/10 + (cur%10)*1000;
				if (!visited[r]) {
					visited[r] = true;
					StringBuilder temp = new StringBuilder(cur_sb).append('R');
					q.offer(new Register(r, temp));
				}
			}
		}
		if (res.length() > 0) res.setLength(res.length() - 1);
		System.out.println(res.toString());
	}

}
