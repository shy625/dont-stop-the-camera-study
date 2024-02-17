

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20955_민서의_응급_수술 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 분리집합을 활용
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		int cnt1 = 0; // 트리를 만들기 위해 끊지 않아도 되는 간선의 개수
		int cnt2 = 0; // 트리를 만들기 위해 끊어야 하는 간선의 개수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 이 경우 (u, v) 간선은 끊지 않아도 된다.
			if (find(u) != find(v)) {
				union(u, v);
				cnt1++;
			// 이 경우 (u, v) 간선을 끊어야 트리를 만들 수 있다.(현재는 사이클을 형성)
			} else {
				cnt2++;
			}
		}
		// cnt2개의 간선을 끊고, (N-1-cnt1)개의 간선을 이어야 트리를 만들 수 있다.
		System.out.println(N-1-cnt1+cnt2);
	}
	// u의 루트 노드를 찾는 함수
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// u와 v를 같은 부분트리로 연결하는 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
