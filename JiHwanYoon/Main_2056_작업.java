

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2056_작업 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] cnts = new int[N+1]; // 선행 작업의 개수
		int[] times = new int[N+1]; // 각 작업에 필요한 시간
		int[] waits = new int[N+1]; // 각 작업을 시작하기 위해 대기해야 하는 최소시간
		// 선행 작업 연관 관계를 그래프로 표현
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			cnts[i] = Integer.parseInt(st.nextToken());
			for (int k = 0; k < cnts[i]; k++) {
				graph.get(Integer.parseInt(st.nextToken())).add(i);
			}
		}
		// 그래프에 대한 너비 우선 탐색을 통해 모든 작업을 마치는데 필요한 최소시간을 구한다.
		Queue<int[]> q = new LinkedList<>();
		// 사전 작업이 필요없는 작업부터 진행
		for (int i = 1; i <= N; i++) {
			if (cnts[i] == 0) q.offer(new int[] {i, times[i]});
		}
		int max = 0; // 모든 작업을 완료하는데 필요한 최소시간
		while (!q.isEmpty()) {
			int[] u = q.poll();
			max = Math.max(max, u[1]);
			for (int v : graph.get(u[0])) {
				// v에 대한 선행작업 하나 완료
				cnts[v]--; 
				waits[v] = Math.max(waits[v], u[1]);
				// v에 대한 모든 선행작업이 완료되는 경우
				if (cnts[v] == 0) {
					q.offer(new int[] {v, waits[v]+times[v]});
				}
			}
		}
		System.out.println(max);
	}

}
