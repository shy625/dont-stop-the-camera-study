

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10971_외판원_순회_2 {
	static int[][] graph;
	static int N;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 외판원 순회 경로 비용의 최솟값
		int min = Integer.MAX_VALUE;
		// 각 도시를 시작 지점으로 정한 뒤 외판원 순회를 시도한다. 
		for (int i = 0; i < N; i++) {
			// 각 도시를 방문했는지를 나타낸다.
			boolean[] visited = new boolean[N];
			visited[i] = true;
			min = Math.min(min, perm(0, i, i, 0, visited));
		}
		System.out.println(min);
	}
	// 순열을 이용해 외판원 순회를 구현한다.
	// start : 시작 도시, cur : 현재 방문한 도시, sum : 현재까지 순회하는데 든 비용의 합
	private static int perm(int cnt, int start, int cur, int sum, boolean[] v) {
		// 시작 도시를 제외한 나머지 도시를 모두 방문한 경우
		if (cnt == N-1) {
			// 다시 시작 도시로 돌아온다. 단, 길이 있는 경우에만 가능
			if (graph[cur][start] == 0) return Integer.MAX_VALUE;
			sum += graph[cur][start];
			return sum;
		}
		// 다음으로 방문할 도시에 따라 외판원 순회 비용이 달라지는데, 그 중 최솟값을 구하기 위해 min 변수 선언
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// 길이 없는 경우 또는 이미 방문한 도시인 경우 continue
			if (v[i] || graph[cur][i] == 0) continue;
			v[i] = true;
			min = Math.min(min, perm(cnt+1, start, i, sum+graph[cur][i], v));
			v[i] = false;
		}
		return min;
	}
}
