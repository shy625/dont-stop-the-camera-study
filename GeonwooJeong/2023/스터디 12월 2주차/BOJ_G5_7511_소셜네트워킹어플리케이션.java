import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_7511_소셜네트워킹어플리케이션 {
	static int [] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb.append("Scenario "+t+":\n");
			
			int N = Integer.parseInt(br.readLine());
			int K = Integer.parseInt(br.readLine());
			// 부모 노드를 저장하기 위한 배열
			parent = new int[N];
			
			// 처음 자신의 부모는 자신이다.
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a와 b를 연결하고 부모를 갱신해주는 작업을 해준다.
				union(a, b);
			}
			
			int M = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a의 부모와 b의 부모가 같다면 연결되어 있다.
				if(find(a) == find(b)) {
					sb.append(1+"\n");
				} else {
					sb.append(0+"\n");
				}
			}
			
			sb.append("\n");

		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
		
	}

	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		
		return find(parent[a]);
	}

}
