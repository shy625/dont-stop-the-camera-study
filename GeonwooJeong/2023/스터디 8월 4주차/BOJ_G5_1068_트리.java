import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_1068_트리 {
	static class Node {
		int idx;
		ArrayList<Node> next = new ArrayList<>();
		
		public Node(int idx) {
			this.idx = idx;
		}
	}
	
	static int N, X, ans;
	static Node [] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new Node[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = new Node(i);
		}
		
		int root = 0;
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			
			// -1이 입력으로 들어온 경우는 해당 노드가 루트노드이다.
			if (parent == -1) {
				root = i;
				continue;
			}
			
			// 이외의 경우에는 parent 노드에 i번째 노드를 연결해준다.
			arr[parent].next.add(arr[i]);
		}
		
		X = Integer.parseInt(br.readLine());
		
		// 루트 노드를 없애는게 아니라면, X의 자식 노드를 모두 없앤 뒤, 리프 노드의 개수를 세어준다.
		if(X != root) {
			delete(root);
			dfs(root);
		}
		
		System.out.println(ans);
		
	}

	private static void dfs(int idx) {
		// next의 사이즈가 0이라면 리프노드이다.
		if(arr[idx].next.size() == 0) {
			ans++;
			return;
		}
		
		// 노드의 가장 끝까지 탐색한다.
		for (Node next : arr[idx].next) {
			dfs(next.idx);
		}
		
	}

	private static void delete(int idx) {
		for(Node next : arr[idx].next) {
			// X 노드를 찾았다면, 해당 노드를 없앤다.
			if(next.idx == X) {
				arr[idx].next.remove(next);
				return;
			}
			delete(next.idx);
		}
	}

}
