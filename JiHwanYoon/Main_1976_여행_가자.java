

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1976_여행_가자 {
	static int[] parents;
	static int[] ranks;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// 각 정점의 부모 정점을 담는 배열
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		// 각 정점의 rank를 담는 배열
		ranks = new int[N+1];
		Arrays.fill(ranks, 1);
		// 주어지는 행렬을 인접 행렬로 간주하고 (i, j) 원소가 1인 경우 같은 집합으로 union해준다. 
		for (int i = 1; i <= N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(s[j-1]) == 1) {
					union(i, j);
				}
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 여행 경로의 첫 방문지에 대해 루트 정점을 조사한다.
		int root = find(Integer.parseInt(st.nextToken()));
		while (st.hasMoreTokens()) {
			// 각 방문지의 루트 정점이 root와 다르다는 것은 해당 방문지가 첫 방문지와 연결되어 있지 않다는 의미이므로 절대 여행 경로를 통해 방문할 수 없다.
			if (find(Integer.parseInt(st.nextToken())) != root) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");

	}
	// union find
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			if (ranks[u] > ranks[v]) {
				parents[v] = u;
			} else {
				parents[u] = v;
				if (ranks[u] == ranks[v]) {
					ranks[v]++;
				}
			}
		}
		
	}

}
