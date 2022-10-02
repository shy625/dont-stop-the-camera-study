import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int v;
	int dist;
	
	public Edge(int v, int dist) {
		this.v = v;
		this.dist = dist;
	}

	public int compareTo(Edge o) {
		if(this.dist < o.dist) {
			return -1;
		} else if(this.dist > o.dist) {
			return 1;
		} else {
			return this.v - o.v;
		}
	}
	
}

public class BOJ_G4_22865_가장먼곳 {
	
	static int N;
	static int[] arr = new int[3];
	// A, B, C의 최단거리를 저장할 배열들
	static int [] dist1;
	static int [] dist2;
	static int [] dist3;
	static List<Edge>[] list = new ArrayList[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			list[start].add(new Edge(end, dist));
			list[end].add(new Edge(start, dist));
		}
		
		dist1 = dijkstra(arr[0]);
		dist2 = dijkstra(arr[1]);
		dist3 = dijkstra(arr[2]);
		
		int max = -1;
		int maxIdx = -1;
		
		// 1번부터 N번 땅 까지 A, B, C으로 부터의 거리를 구한다.
		for (int i = 1; i <= N; i++) {
			if(i == arr[0] || i == arr[1] || i == arr[2]) continue;
			int A = dist1[i];
			int B = dist2[i];
			int C = dist3[i];
			
			// A, B, C으로 부터 거리 중 제일 짧은 거리를 구한다.
			int min = Math.min(Math.min(A, B), C);
			
			// 제일 짧은 거리가 지금까지의 땅 중 가장 멀 경우 해당 땅으로 갱신하다.
			if(max < min) {
				max = min;
				maxIdx = i;
			}
		}
		
		System.out.println(maxIdx);

	}
	
	private static int [] dijkstra(int start) {
		int dist[] = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curV = cur.v;
			int curDist = cur.dist;
			if(dist[curV] < curDist) {
				continue;
			}
			
			for (int i = 0; i < list[curV].size(); i++) {
				int next = list[curV].get(i).v;
				int nextDist = list[curV].get(i).dist;
				if(dist[next] > curDist + nextDist) {
					dist[next] = curDist + nextDist;
					pq.add(new Edge(next, curDist+nextDist));
				}
			}
		}
		
		return dist;
		
	}

}
