

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471_게리맨더링 {
	static int N;
	static int[] populations; // 각 선거구의 인구 수
	static ArrayList<ArrayList<Integer>> graph; // 백준시 내 선거구에 대한 그래프
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		populations = new int[N+1];
		graph = new ArrayList<>();
		graph.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		// 두 구역의 인구차의 최솟값
		int min = Integer.MAX_VALUE;
		// 비트마스킹을 통해 두 구역으로 나눈다.(한 구역에 속하면 1, 다른 구역에 속하면 0)
		for (int i = 1; i < (1 << N) - 1; i++) {
			// 만약 두 구역이 각각 연결되었으면(check(i)가 true) 두 구역의 인구 차를 구한다.(diff(i))  
			if (check(i)) min = Math.min(min, diff(i));
		}
		// min 값이 Integer.MAX_VALUE라는 것은 두 구역을 적절히 나눌 수 없음을 의미한다.
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// 두 구역이 각각 연결되어 있는지를 확인한다.
	private static boolean check(int i) {
		boolean[] area = new boolean[N+1]; // 각 선거구가 어느 구역에 속하는지를 기록(한 구역에 속하면 true, 다른 구역에 속하면 false)
		int start_1 = 0; // 한 구역에서 bfs를 시작할 선거구
		int start_2 = 0; // 다른 구역에서 bfs를 시작할 선거구
		// 인자로 받은 비트마스킹을 boolean 배열로 바꾼다.
		for (int j = 1; j <= N; j++) {
			if ((i & (1 << (j-1))) != 0) {
				area[j] = true;
				start_1 = j;
			} else {
				start_2 = j;
			}
		}
		// 각 구역이 모두 연결되었는지 확인한다.
		return bfs(start_1, true, area) && bfs(start_2, false, area);
	}
	// 각 구역이 연결되었는지 확인하기 위한 BFS
	private static boolean bfs(int start, boolean curArea, boolean[] area) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				// curArea와 area[v]가 같은 boolean 값을 가져야 방문할 수 있다.
				if (!visited[v] && !(curArea ^ area[v])) {
					visited[v] = true;
					q.offer(v);
				}
			}
		}
		// 현재 구역에 속한 모든 선거구를 방문했는지를 확인한다.
		for (int k = 1; k <= N; k++) {
			if (!(curArea ^ area[k]) && !visited[k]) return false;
		}
		return true;
	}
	// 두 구역의 인구수 차이를 계산
	private static int diff(int i) {
		int sum1 = 0, sum2 = 0;
		for (int k = 1; k <= N; k++) {
			if ((i & (1 << (k-1))) != 0) sum1 += populations[k];
			else sum2 += populations[k];
		}
		return Math.abs(sum1 - sum2);
	}

}
