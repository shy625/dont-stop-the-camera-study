

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의_표현 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N+1]; // 유니온-파인드 알고리즘 적용 시 각 노드가 속한 트리의 루트 노드
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		StringBuilder sb = new StringBuilder();
		// 유니온-파인드 알고리즘 적용
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int operate = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (operate == 0) union(a, b);
			else sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// u의 루트 노드를 찾는 함수
	static int find(int u) {
		if (parents[u] == u) return u;
		else return parents[u] = find(parents[u]);
	}
	// u와 v가 속한 트리를 합치는 함수
	static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
