

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_18231_파괴된_도시 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] destroyed;
	static boolean[] checked;
	static ArrayList<Integer> res;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		int K = Integer.parseInt(br.readLine());
		destroyed = new boolean[N+1]; // 각 지역이 현재 파괴되었는지를 나타내는 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			destroyed[Integer.parseInt(st.nextToken())] = true;
		}
		checked = new boolean[N+1]; // 각 지역이 해당 지역 혹은 인접 지역의 폭탄으로 인해 파괴된 게 확인되었는지를 나타내는 배열
		res = new ArrayList<>(); // 폭탄이 터졌을 가능성이 있는 지역을 저장하는 ArrayList
		// 파괴된 지역에 대해 폭탄을 떨어뜨렸을 때 해당 지역과 인근 지역이 모두 파괴됐는지 확인
		for (int i = 1; i <= N; i++) {
			if (destroyed[i]) check(i);
		}
		// 한 군데라도 폭탄에 의해 파괴된 지역임이 검증되지 않으면 -1 출력
		for (int i = 1; i <= N; i++) {
			if (destroyed[i] && !checked[i]) {
				System.out.println(-1);
				return;
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(res.size()).append("\n");
		for (int i = 0; i < res.size(); i++) {
			sb.append(res.get(i)).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// u 지역에 폭탄이 떨어졌는지 확인하는 함수
	private static void check(int u) {
		for (int v : graph.get(u)) {
			if (!destroyed[v]) return;
		}
		checked[u] = true;
		for (int v : graph.get(u)) {
			checked[v] = true;
		}
		res.add(u);		
	}

}
