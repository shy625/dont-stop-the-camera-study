import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21924_도시건설 {

	//union find 씁시다
	static int N,M;
	static long maxCost;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		//도로 정보 받기
		//도로 건설 비용 낮은 순 정렬
		PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->Integer.compare(x[2], y[2]));
		maxCost=0;
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			pq.offer(new int[] {a,b,c});
			maxCost+=c;
		}
		
		//union-find
		
		//초기화
		parents=new int[N+1];
		for (int i = 0; i <=N; i++) {
			parents[i]=i;
		}
		
		//
		long mstCost=0;
		int cnt=1;//연결된 도시 갯수 세기
		while(!pq.isEmpty()) {
			if(cnt==N) break;
			int [] cur=pq.poll();
			int a=cur[0];
			int b=cur[1];
			int c=cur[2];
			
			if(!union(a,b)) continue;
			
			mstCost+=c;
			cnt++;
		}
		
		System.out.println(cnt!=N?-1:maxCost-mstCost);
	}

	static int find(int a) {
		if(parents[a]==a) return a;
		else return parents[a]= find(parents[a]);
	}
	
	static boolean union(int a,int b) {
		int pA=find(a);
		int pB=find(b);
		
		if(pA==pB) return false;
		else parents[pA]=pB;

		return true;
	}
}
