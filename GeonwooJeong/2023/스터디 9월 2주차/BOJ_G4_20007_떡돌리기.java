import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class P implements Comparable<P> {
	int to;
	int dist;
	
	public P(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(P o) {
		return Integer.compare(this.dist, o.dist);
	}
	
}

public class BOJ_G4_20007_떡돌리기 {
	static int N, X, Y, ans = 1;
	static ArrayList<ArrayList<P>> adj;
	static int [] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		dist = new int[N];
		// 거리 배열을 INF로 채워준다.
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 인접리스트 초기화
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(new P(b, d));
			adj.get(b).add(new P(a, d));
		}
		
		dijkstra();
		
		// 거리가 가까운 집부터 정렬해준다.
		Arrays.sort(dist);
		
		cal();
		
		System.out.println(ans);
		
	}

	private static void cal() {
		// 하루동안 이동한 거리를 저장할 변수
		int sum = 0;
		for (int i = 0; i < N; i++) {
			// i번째 집까지 왕복 거리가 X보다 크다면 방문할 수 없으므로 ans는 -1이 된다.
			if(dist[i]*2 > X) {
				ans = -1;
				break;
			}
			
			// i번째 집까지의 왕복 거리를 더해준다.
			sum += dist[i] * 2;
			
			// 만약 이동한 거리가 X보다 크다면, i번째 집은 자고 일어나서 내일 방문해야 한다.
			if(sum > X) {
				sum = dist[i] * 2;
				ans++;
			}
		}
		
	}

	private static void dijkstra() {
		boolean [] v = new boolean[N];
		PriorityQueue<P> pq = new PriorityQueue<>();
		dist[Y] = 0;
		// 성현이의 집부터 시작한다.
		pq.add(new P(Y, 0));
		
		while(!pq.isEmpty()) {
			P now = pq.poll();
			
			// 이미 방문한 집이면 넘긴다.
			if(v[now.to]) continue;
			
			// 다익스트라 알고리즘
			for (P next : adj.get(now.to)) {
				if(!v[next.to] && dist[next.to] > dist[now.to] + next.dist) {
					dist[next.to] = dist[now.to] + next.dist;
					pq.add(new P(next.to, dist[next.to]));
				}
			}
			
			// 방문 처리
			v[now.to] = true;
			
		}
		
	}

}
