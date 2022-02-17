
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11725_트리의_부모_찾기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 트리의 구조를 명확히 할 수 없는 상황이라 그래프 사용
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 그래프에 간선 추가(undirected graph이므로 양방향으로 간선 추가)
		for (int i = 0; i < N-1; i++) {
			String[] arr = br.readLine().split(" ");
			int start = Integer.parseInt(arr[0]);
			int end = Integer.parseInt(arr[1]);
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		// BFS 사전 준비
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		// 각 노드의 부모 노드를 저장할 배열
		int[] parents = new int[N+1];
		// BFS : 루트 노드부터 순차적으로 자식 노드를 방문하면서 노드들의 부모 노드를 지정
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					q.offer(v);
					// 부모 노드 지정
					parents[v] = u;
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]+"\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
