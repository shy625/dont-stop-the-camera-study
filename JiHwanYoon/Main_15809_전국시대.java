

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_15809_전국시대 {
	static int N, M;
	static int[] parents;
	static int[] soldiers;
	static int cnt;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 분리 집합을 이용한다.
		parents = new int[N+1]; // 각 국가의 중심 국가
		soldiers = new int[N+1]; // 각 국가의 총 병력 수
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			soldiers[i] = Integer.parseInt(br.readLine());
		}
		cnt = N; // 남아 있는 국가 수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			if (O == 1) union(P, Q); // P와 Q가 동맹을 맺는다.
			else war(P, Q); // P와 Q가 전쟁을 한다.
		}
		// 남아 있는 국가들의 병력 수를 저장하는 ArrayList
		ArrayList<Integer> results = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			// 중심 국가인 경우에만 병력 수 저장
			if (parents[i] == i) results.add(soldiers[i]);
		}
		Collections.sort(results);
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int result : results) {
			sb.append(result).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// u 국가의 중심 국가를 찾는 함수
	static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// u 국가와 v 국가의 동맹을 맺는 함수
	static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			parents[u] = v;
			soldiers[v] += soldiers[u];
			cnt--;
		}
	}
	// u 국가와 v 국가의 전쟁을 일으키는 함수
	static void war(int u, int v) {
		u = find(u);
		v = find(v);
		if (soldiers[u] > soldiers[v]) {
			parents[v] = u;
			soldiers[u] -= soldiers[v];
		} else if (soldiers[v] > soldiers[u]) {
			parents[u] = v;
			soldiers[v] -= soldiers[u];
		} else {
			parents[u] = parents[v] = -1;
			soldiers[u] = soldiers[v] = 0;
			cnt--;
		}
		cnt--;
	}
}
