import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_15809_전국시대 {
	static int [] arr, parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// i번째 나라의 병사 수를 저장할 배열
		arr = new int[N+1];
		// i번째 나라의 부모 나라를 저장할 배열
		parent = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			// arr[i]에 i번째 국가의 병사 수를 넣는다.
			arr[i] = Integer.parseInt(br.readLine());
			// i번째 나라의 부모는 i이다. (초기조건 설정)
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			
			// 동맹 할 경우 두 나라를 union 해준다.
			if(O == 1) {
				union(P, Q);
			// 전쟁 할 경우
			} else if(O == 2) {
				P = find(P);
				Q = find(Q);
				
				// P나라 병사가 더 많을 경우, Q나라를 P나라에 포함시키고 P나라의 병사 수를 감소시킨다.
				if(arr[P] > arr[Q]) {
					parent[Q] = P;
					arr[P] -= arr[Q];
				// Q나라 병사가 더 많을 경우, P나라를 Q나라에 포함시키고 Q나라의 병사 수를 감소시킨다.
				} else if(arr[P] < arr[Q]) {
					parent[P] = Q;
					arr[Q] -= arr[P];
				// 두 나라 병사 수가 같을 경우, 두 나라 모두 멸망시킨다.
				} else if(arr[P] == arr[Q]) {
					parent[P] = 0;
					parent[Q] = 0;
					arr[P] = 0;
					arr[Q] = 0;
				}
				
			}
			
		}
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			// parent[i] == i인 경우 부모 노드이므로, 이 때의 병사 수를 list에 넣어준다.
			if(parent[i] == i) {
				list.add(arr[i]);
			}
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		for (int n : list) {
			sb.append(n+" ");
		}
		
		// 모든 나라가 멸망했을 수 있으므로 해당 조건을 넣어줘야 한다.
		if(sb.length() > 0) {
			sb.setLength(sb.length()-1);
		}
		
		System.out.println(list.size());
		System.out.println(sb.toString());
		
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		
		// 이미 동맹한 나라가 아니라면 u나라에 v나라에 포함시키고, 병사 수도 더해준다.
		if(u != v) {
			parent[u] = v;
			arr[v] += arr[u];
		}
	}

	private static int find(int u) {
		if(parent[u] == u) return u;
		
		return parent[u] = find(parent[u]);
	}

}
