import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17396_백도어 {
	static int N, M;
	static int [] visible;
	static ArrayList<Node>[] list;
	static long[] dist;
	
	static class Node implements Comparable<Node> {
		int p;
		long cost;
		
		public Node(int p, long cost) {
			this.p = p;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			if(this.cost < o.cost) return -1;
			else return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visible = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		// visible 0 : 이동가능, visible 1 : 이동불가
		for (int i = 0; i < N; i++) {
			visible[i] = Integer.parseInt(st.nextToken());
		}
		// N-1(넥서스가 있는 곳)은 항상 갈 수 있다.
		visible[N-1] = 0;
		
		// list 초기화
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// dist 초기화
		dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[0] = 0;
		
		// 노드 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, cost));
			list[to].add(new Node(from, cost));
		}
		
		dijkstra();
		
		if(dist[N-1] == Long.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(dist[N-1]);
		}

	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean [] v = new boolean[N];
		pq.offer(new Node(0, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(v[cur.p]) continue;
			v[cur.p] = true;
			
			int length = list[cur.p].size();
			
			for (int i = 0; i < length; i++) {
				Node next = list[cur.p].get(i);
				
				// 시야가 있으면 갈 수 없다.
				if(visible[next.p] == 1) continue;
				
				// dist 갱신 작업
				if(dist[next.p] > dist[cur.p]+next.cost) {
					dist[next.p] = dist[cur.p]+next.cost;
					pq.offer(new Node(next.p, dist[next.p]));
				}
			}
		}
	}
}
