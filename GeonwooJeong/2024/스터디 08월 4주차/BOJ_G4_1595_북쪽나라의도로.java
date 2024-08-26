import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1595_북쪽나라의도로 {
	static class Node {
		int to;
		long dist;
		
		public Node(int to, long dist) {
			this.to = to;
			this.dist = dist;
		}
	}
	static List<ArrayList<Node>> list;
	static long max;
	static int maxIdx;
	static boolean [] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		list = new ArrayList<>();
		for (int i = 0; i <= 10000; i++) {
			list.add(new ArrayList<>());
		}
		v = new boolean[10001];
		
		while(true) {
			String str = br.readLine();
			if(str == null || str.length() == 0) break;
			
			StringTokenizer st = new StringTokenizer(str);
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Node(b, dist));
			list.get(b).add(new Node(a, dist));
		}
		
		// 한 점에서 시작해서 가장 멀리있는 점을 찾는다.
		// 찾은 점은 가장 먼 두 점중 하나의 점이 된다.
		dfs(1, 0);
		
		// 그 점에서 dfs를 사용하여 가장 멀리있는 점을 찾는다.
		// 찾은 점은 가장 먼 두 점중 나머지 하나의 점이 된다.
		dfs(maxIdx, 0);
		
		System.out.println(max);
		
	}

	private static void dfs(int idx, long dist) {
		v[idx] = true;
		
		if(max < dist) {
			maxIdx = idx;
			max = dist;
		}
		
		for(Node next : list.get(idx)) {
			if(v[next.to]) continue;
			dfs(next.to, dist+next.dist);
		}
		
		v[idx] = false;
		
	}

}
