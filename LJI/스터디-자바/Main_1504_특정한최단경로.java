import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504_특정한최단경로 {

	//1->a->b->N or 1->b->a->N 순으로 다익스트라 두 거리 비교
	static int N,E;
	static class Node implements Comparable<Node>{
		int to;
		int d;//거리
		public Node(int to, int d) {
			super();
			this.to = to;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			return this.d-o.d;
		}
	}
	static ArrayList<ArrayList<Node>> list;
	static int [] distance;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		list=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		
		distance=new int[N];
		v=new boolean[N];
		for (int i = 0; i < E; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		int v1=0,v2=0;
		st=new StringTokenizer(br.readLine()," ");
		v1=Integer.parseInt(st.nextToken())-1;
		v2=Integer.parseInt(st.nextToken())-1;
		
		long totalV1=0;
		totalV1+=dijk(0,v1);
		totalV1+=dijk(v1,v2);
		totalV1+=dijk(v2,N-1);
		
		long totalV2=0;
		totalV2+=dijk(0,v2);
		totalV2+=dijk(v2,v1);
		totalV2+=dijk(v1,N-1);
		
		long total=Math.min(totalV1, totalV2);
		if(total>=Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(total);
	}
	
	private static int dijk(int start, int end) {//다익스트라
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(v, false);
		
		distance[start]=0;
		PriorityQueue<Node> pq=new PriorityQueue<>();
		pq.offer(new Node(start,distance[start]));
		
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			
			if(v[now.to]) continue;
			
			v[now.to]=true;
			if(now.to==end) break;//끝내도 좋다
			
			for(Node node:list.get(now.to)) {
				int next=node.to;
				if(!v[next] && distance[next]>distance[now.to]+node.d ) {
					 distance[next]=distance[now.to]+node.d;
					 pq.offer(new Node(next,distance[next]));
				}
			}
		}
		return distance[end];
	}
}
