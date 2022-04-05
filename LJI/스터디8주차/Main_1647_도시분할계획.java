import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1647_도시분할계획 {

	
	
	static int N,M;
	static int[] parent;
	//크루스칼을 써서 유지비 최솟값 정렬 하고 집들의 조상이 두개가 됐을 때 유지비 출력
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Line> pq=new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			
			pq.offer(new Line(a,b,c));
		}

		//크루스칼
		set();
		
		int group=N;
		int total=0;
		while(group!=2) {
			Line l=pq.poll();
			if(union(l.a,l.b)) {
				total+=l.c;
				group--;
			}
		}
		
		System.out.println(total);	
	}

	
	
	private static boolean union(int a, int b) {
		int aP=find(a);//여기서부터 이어합시다
		int bP=find(b);
		
		if(aP==bP) {
			return false;
		}
		parent[bP]=aP;
		return true;
	}



	private static int find(int a) {
		if(a==parent[a]) return a;
		return parent[a]=find(parent[a]);
	}



	private static void set() {
		parent=new int[N];
		for (int i = 0; i < N; i++) {
			parent[i]=i;
		}
	}



	public static class Line implements Comparable<Line>{
		int a;
		int b;
		int c;
		
		public Line(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Line o) {
			// TODO Auto-generated method stub
			return this.c-o.c;
		}
		
		
	}
}
