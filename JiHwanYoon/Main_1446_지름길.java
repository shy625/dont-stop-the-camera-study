

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1446_지름길 {
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		// 입력으로 주어지는 지름길들
		ArrayList<int[]> pathsList = new ArrayList<>();
		// 전체 길에서 중요한 지점들(출발점, 도착점, 지름길 출발 및 도착점)을 저장 
		ArrayList<Integer> NodeList = new ArrayList<>();
		NodeList.add(0);
		NodeList.add(D);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			// 실제로 지름길이 되는 경우만 저장
			if (e - s > d && e <= D) {
				pathsList.add(new int[] {s, e, d});
				if (!NodeList.contains(s)) NodeList.add(s);
				if (!NodeList.contains(e)) NodeList.add(e);
			}
		}
		// 지점들을 오름차순으로 정렬
		Collections.sort(NodeList);
		int V = NodeList.size();
		// 일반적인 길과 지름길을 저장하는 그래프
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
		// 일반적인 길을 저장
		for (int i = 0; i < V; i++) {
			ArrayList<int[]> cur = graph.get(i);
			for (int j = i+1; j < V; j++) {
				cur.add(new int[] {j, NodeList.get(j) - NodeList.get(i)});
			}
		}
		// 지름길 저장
		for (int[] path : pathsList) {
			int i = NodeList.indexOf(path[0]);
			int j = NodeList.indexOf(path[1]);
			graph.get(i).add(new int[] {j, path[2]});
		}
		// dijkstra's algorithm을 이용해 도착점까지 가는 최소 시간을 구한다.
		int[] dijk = new int[V];
		Arrays.fill(dijk, Integer.MAX_VALUE);
		dijk[0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
		pq.offer(new int[] {0, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			for (int[] v : graph.get(u[0])) {
				if (dijk[v[0]] > dijk[u[0]] + v[1]) {
					dijk[v[0]] = dijk[u[0]] + v[1];
					pq.offer(new int[] {v[0], dijk[v[0]]});
				}
			}
		}
		System.out.println(dijk[V-1]);
	}
	
}
