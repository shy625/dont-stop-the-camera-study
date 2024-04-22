import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G5_1240_노드사이의거리 {
	static class Node {
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static List<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Node(b, cost));
			list.get(b).add(new Node(a, cost));
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			int ans = bfs(start, end);
			
			sb.append(ans).append('\n');
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static int bfs(int start, int end) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(start);
		Queue<Node> q = new ArrayDeque<Node>();
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int idx = cur.to;
			int cost = cur.cost;
			
			for(Node next : list.get(idx)) {
				if(set.contains(next.to)) continue;
				
				if(end == next.to) {
					return cost + next.cost;
				}
				
				set.add(next.to);
				q.add(new Node(next.to, cost+next.cost));
				
			}
			
		}
		
		return 0;
	}

}
