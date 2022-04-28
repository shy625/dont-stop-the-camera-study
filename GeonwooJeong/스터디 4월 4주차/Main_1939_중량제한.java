import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1939_중량제한 {
	
	static class Node {
		int n;
		int weight;
		
		public Node(int n, int weight) {
			super();
			this.n = n;
			this.weight = weight;
		}
		
	}
	
	static int N, M, max;
	static ArrayList<ArrayList<Node>> adj;
	static Queue<Integer> q;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 섬의 개수
		N = Integer.parseInt(st.nextToken());
		// 다리의 개수
		M = Integer.parseInt(st.nextToken());
		// bfs에 사용하는 큐 초기화
		q = new LinkedList<>();
		// q에 사용할 visit 배열 초기화
		v = new boolean[N+1];
		// 인접리스트 초기화과정
		adj = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			adj.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// max 값 갱신
			max = Math.max(max, weight);
			
			// a, b 섬 서로 연결
			adj.get(a).add(new Node(b, weight));
			adj.get(b).add(new Node(a, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		System.out.println(binary(x, y));
		
	}

	private static long binary(int a, int b) {
		long left = 1;
		long right = max;
		long mid = 0;
		long ans = 0;
		
		while (left <= right) {
			mid = (left + right) / 2;
			// mid 값을 가지고 a부터 b까지 bfs를 탐색
			//(mid 값을 만족하면서 끝까지 가면 true, 아니면 false)
			// true일 경우 left = mid + 1로 갱신, false일 경우 right = mid - 1로 갱신
			// true일 경우 최대 weight(ans)를 갱신
			if(bfs(a, b, mid)) {
				ans = Math.max(ans, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	// a에서 b까지의 탐색을 하기 위한 bfs
	private static boolean bfs(int a, int b, long weight) {
		v = new boolean[N+1];
		q.offer(a);
		v[a] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == b) {
				return true;
			}
			
			for(Node next : adj.get(cur)) {
				if(!v[next.n] && next.weight >= weight) {
					q.offer(next.n);
					v[next.n] = true;
				}
			}
		}
		
		return false;
	}

}
