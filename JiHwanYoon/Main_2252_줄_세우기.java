

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_줄_세우기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 위상정렬을 이용한다.
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		// 각 지점을 방문하기 위해서 선행하여 방문해야 하는 지점의 개수
		int[] counts = new int[N+1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			counts[B]++;
		}
		// 출력을 위한 준비
		StringBuilder sb = new StringBuilder();
		String blank = " ";
		Queue<Integer> q = new LinkedList<>();
		// 선행으로 방문해야 하는 지점이 없는 지점을 우선 방문
		for (int i = 1; i <= N; i++) {
			if (counts[i] == 0) q.offer(i);
		}
		while (!q.isEmpty()) {
			int u = q.poll();
			sb.append(u).append(blank);
			for (int v : graph.get(u)) {
				// 선행으로 방문할 지점을 모두 방문한 경우
				if (--counts[v] == 0) {
					q.offer(v);
				}
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
