import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1717_집합의표현 {
	static int [] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// union-find 알고리즘을 사용하기 위한 부모 배열
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			// 맨 처음에는 자신이 자신의 부모이다.
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int com = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 0이면 a와 b를 합친다.
			if(com == 0) {
				union(a, b);
			// 1이면 a와 b의 부모를 확인한다.
			} else if(com == 1) {
				int aa = find(a);
				int bb = find(b);
				if(aa == bb) sb.append("YES\n");
				else sb.append("NO\n");
			}
			
		}
		
		if(sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// a의 부모와 b의 부모가 같지 않으면 하나로 합쳐준다.
		if(a != b) parent[a] = b;
	}

	private static int find(int a) {
		// a의 부모가 a라면 그대로 리턴한다.
		if(parent[a] == a) return a;
		// 아니라면 a 부모의 부모를 찾는다. 동시에 갱신해주며 연산횟수를 줄인다.
		return parent[a] = find(parent[a]);
	}

}
