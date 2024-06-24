

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7511_소셜_네트워킹_어플리케이션 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());
			// union-find 알고리즘을 활용한다.
			parents = new int[n];
			for (int i = 1; i < n; i++) {
				parents[i] = i;
			}
			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if (find(u) != find(v)) union(u, v); // 친구 관계 등록
			}
			int m = Integer.parseInt(br.readLine());
			sb.append("Scenario "+t+":\n");
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if (find(u) == find(v)) sb.append(1); // 친구 관계 그래프 상에서 경로가 존재하는 경우
				else sb.append(0); // 그렇지 않은 경우
				sb.append("\n");
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-2);
		System.out.println(sb.toString());
	}
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
