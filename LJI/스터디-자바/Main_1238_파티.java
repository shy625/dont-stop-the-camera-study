import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1238_파티 {

	//범위 1~N 따라서 인덱스에서 -1작업 필요
	//다익스트라 N-->X + X-->N = X마을에 파티하러 왔다 가는 시간
	static int N,M,X;
	static class Node implements Comparable<Node>{
		int n;//연결 노드
		int d;//거리
		public Node(int n, int d){
			super();
			this.n = n;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			return this.d-o.d;
		}
	}
	
	static ArrayList<ArrayList<Node>> graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken()); //사람 수==마을 수
		M=Integer.parseInt(st.nextToken()); //간선 수
		X=Integer.parseInt(st.nextToken())-1;// 가고자 하는 마을 //1부터 시작하기에 -1작업
		
		graph=new ArrayList<>();
		for (int i = 0; i < N; i++) {//N개의 노드 생성
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int node=Integer.parseInt(st.nextToken())-1;
			int nextN=Integer.parseInt(st.nextToken())-1;
			int dis=Integer.parseInt(st.nextToken());
			graph.get(node).add(new Node(nextN,dis));
		}
		//읽기 끝
		int Max=-1;//거리 최대인 사람 구하기
		
		for (int n = 0; n < N; n++) {//n개의 마을 반복
			if(n==X) continue;//자기 자신 마을일땐 딱히 필요없다
			
			int go=dijkstra(n,X);
			int back=dijkstra(X,n);
			
			Max=Math.max(go+back, Max);
		}
		
		System.out.println(Max);
	}

	private static int dijkstra(int start, int end) {
		int [] distance=new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		boolean [] visit=new boolean[N];
		PriorityQueue<Node> pq =new PriorityQueue<>();
		
		distance[start]=0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			
			if(visit[now.n]) continue;
			if(now.n == end) {
				return distance[now.n];//start마을에서 end마을까지의 최단 거리
			}
			visit[now.n]=true;
			
			for(Node next: graph.get(now.n)) {
				if(!visit[next.n] && distance[next.n] > distance[now.n]+next.d) {
					distance[next.n] = distance[now.n]+next.d;
					pq.offer(new Node(next.n, distance[next.n]));
				}
			}
		}
		return distance[end];
	}
}
