
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2606_바이러스 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// 그래프의 간선을 저장하기 위한 ArrayList로, graph.get(i)에 j가 있다는 것은 i -> j라는 간선이 있다는 의미
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		// 각 인덱스에 대해 ArrayList를 미리 만들기
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			// 입력
			String[] arr = br.readLine().split(" ");
			int start = Integer.parseInt(arr[0]);
			int end = Integer.parseInt(arr[1]);
			// 주어지는 그래프는 undirected graph이므로 start -> end와 end -> start 간선을 모두 추가
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		// BFS 사전 준비
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		// 바이러스가 걸린 컴퓨터 수
		int cnt = 0;
		// BFS
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					cnt++;
					q.offer(v);
				}
			}
		}
		System.out.println(cnt);
	}
}
