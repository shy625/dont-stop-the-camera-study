import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {
	
	public static class Node {
		int idx; // 노드 번호
		int parent; // 부모의 노드 번호
		ArrayList<Node> node; // 연결되어 있느 노드
		
		public Node(int idx) {
			super();
			this.idx = idx;
			this.parent = 0;
			this.node = new ArrayList<>();
		}
		
	}
	
	static int N;
	static Node [] nodes;
	static boolean [] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nodes = new Node[N+1]; // 트리를 담을 배열
		v = new boolean[N+1]; // 방문 여부를 확인할 배열
		
		for (int i = 1; i <= N; i++) { // 초기화
			nodes[i] = new Node(i);
		}
		
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].node.add(nodes[b]);  // a와 b가 연결되어있음
			nodes[b].node.add(nodes[a]);  // b와 a가 연결되어있음
		}
		
		bfs();

		for (int i = 2; i < N+1; i++) {
			System.out.println(nodes[i].parent); // 2번째 노드부터 부모 노드를 출력한다.
		}
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(nodes[1]); // 1번째 노드를 큐에 넣는다.
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			for(Node nn : n.node) { // 자식 노드가 없을 때 까지 다음 노드를 확인함
				if(!v[nn.idx]) { // 방문하지 않았다면(이전에 나온 적이 없다면)
					v[nn.idx] = true; // 방문처리
					nn.parent = n.idx; // nn의 부모는 n이 된다.
					q.add(nn); // nn을 큐에 추가함
				}
			}
		}
		
	}

}
