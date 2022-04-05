import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504_특정한최단경로 {
	static int N, E; 
	static ArrayList<ArrayList<Node>> adj; 
	static int[] distance; 
	static boolean[] check;
	static int INF = 200000000;
	
	static class Node implements Comparable<Node>{
		int end; 
		int weight; 
		
		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		public int compareTo(Node n) {
			return weight - n.weight; 
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		distance = new int [N+1];
		check = new boolean[N+1];
		
		Arrays.fill(distance, INF);
		
		for(int i = 0 ; i <= N ; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj.get(start).add(new Node(end, weight));
			adj.get(end).add(new Node(start, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int v1v2 = 0;
		int v2v1 = 0; 
		
		v1v2 += dijkstra(1,v1);
		v1v2 += dijkstra(v1,v2);
		v1v2 += dijkstra(v2, N);
		
		v2v1 += dijkstra(1,v2);
		v2v1 += dijkstra(v2,v1);
		v2v1 += dijkstra(v1, N);
		
		
		int result = (v1v2 >= INF && v2v1 >= INF) ? -1 : Math.min(v1v2,  v2v1);
		System.out.println(result);
	
	}
	
	public static int dijkstra(int start, int end) {
		Arrays.fill(distance, INF);
		Arrays.fill(check, false);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
		pq.offer(new Node(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if(!check[cur]) {
				check[cur] = true; 
				for(Node node : adj.get(cur)) {
					if (!check[node.end] && distance[node.end]> distance[cur] + node.weight) {
						distance[node.end] = distance[cur] + node.weight;
						pq.add(new Node(node.end, distance[node.end]));
					}
				}
			}
		}
		return distance[end];
	}
}
