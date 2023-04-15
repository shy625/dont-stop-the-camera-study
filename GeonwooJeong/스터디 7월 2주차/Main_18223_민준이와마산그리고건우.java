import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_18223_민준이와마산그리고건우 {
	static ArrayList<P>[] map;
	static int V;
	
	static class P implements Comparable<P> {
		int end;
		int cost;
		
		public P(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(P p) {
			return this.cost < p.cost ? -1 : 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			map[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[start].add(new P(end, cost));
			map[end].add(new P(start, cost));
		}
		
		// P부터 시작해서 각 지점의 cost를 계산하는 배열 geon
		int [] geon = dijkstra(P);
		// 1부터 시작해서 각 지점의 cost를 계산하는 배열 shortest
		int [] shortest = dijkstra(1);

		// 시작 -> 건우 -> 마산 과 시작->마산 의 거리가 같을 경우
		if(shortest[P]+geon[V] == shortest[V]) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}

	private static int[] dijkstra(int start) {
		int [] distance = new int[V+1];
		
		for (int i = 1; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<P> pq = new PriorityQueue<>();
		pq.add(new P(start, 0));
		distance[start] = 0;
		
		while(!pq.isEmpty()) {
			P cur = pq.poll();
			
			for (P next : map[cur.end]) {
				if(distance[next.end] > next.cost + cur.cost) {
					distance[next.end] = next.cost + cur.cost;
					pq.add(new P(next.end, next.cost+cur.cost));
				}
			}
		}
		
		return distance;
	}

}
