

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_17098_세_친구 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			graph.get(B).add(A);
		}
		int min = Integer.MAX_VALUE; // 세 친구의 친구 수의 합의 최솟값
		for (int i = 1; i <= N; i++) { // A
			if (graph.get(i).size() < 2) continue; // 세 친구 관계가 성립하려면 A와 친구 관계인 사람이 2명은 있어야 한다. 
			for (int j : graph.get(i)) { // B
				if (graph.get(j).size() < 2) continue; // 세 친구 관계가 성립하려면 B와 친구 관계인 사람이 2명은 있어야 한다. 
				for (int k : graph.get(j)) { // C
					if (graph.get(k).size() < 2 || !graph.get(i).contains(k)) continue; // 세 친구 관계가 성립하려면 C와 친구 관계인 사람이 2명은 있어야 한다.
					// 친구 수의 합 계산 시 A-B, B-C, C-A 친구 관계는 제외(양방향이므로 총 6개 제외)
					min = Math.min(min, graph.get(i).size() + graph.get(j).size() + graph.get(k).size() - 6);
				}
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
