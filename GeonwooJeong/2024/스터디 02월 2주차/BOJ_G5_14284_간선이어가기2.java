import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_14284_간선이어가기2 {
	static class Node implements Comparable<Node> {
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	static List<ArrayList<Node>> list;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<ArrayList<Node>>();
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Node(b, cost));
			list.get(b).add(new Node(a, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int ans = dijkstra(start, end);
		
		System.out.println(ans);
	}

	private static int dijkstra(int start, int end) {
		boolean [] v = new boolean[N+1];
		int [] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.to;
			
			v[cur] = true;
			
			for(Node node : list.get(cur)) {
				if(!v[node.to] && dist[cur] + node.cost < dist[node.to]) {
					dist[node.to] = dist[cur] + node.cost;
					pq.add(new Node(node.to, dist[node.to]));
				}
			}
		}
		
		return dist[end];
	}

}
