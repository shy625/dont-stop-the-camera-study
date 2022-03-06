import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
//크루스칼 써야할 듯
	static int V,E;
	
	static int [] parent;
	static class Line implements Comparable<Line>{
		int a;
		int b;//정점 두개
		int weight;//가중치
		public Line(int a, int b, int weight) {
			super();
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		@Override
		public int compareTo(Line o) {
			return this.weight-o.weight;//가중치 정렬
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		parent=new int[V];
		makeSet();
		PriorityQueue<Line> pq=new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int w=Integer.parseInt(st.nextToken());
			pq.offer(new Line(a,b,w));
		}
		
		int total=0;
		int cnt=0;
		while(!pq.isEmpty()) {
			Line temp=pq.poll();
			if(union(temp.a,temp.b)) {
				total+=temp.weight;
				if(++cnt== V-1)break;
			}
		}
		System.out.println(total);
	}

	static void makeSet() {
		for (int i = 0; i < V; i++) {
			parent[i]=i;
		}
	}
	
	static int find(int a) {
		if(a==parent[a]) return parent[a];
		return parent[a]=find(parent[a]);
	}

	static boolean union(int a, int b) {
		int rootA=find(a);
		int rootB=find(b);
		if(rootA==rootB) return false;
		
		parent[rootB]=rootA;
		return true;
	}
}
