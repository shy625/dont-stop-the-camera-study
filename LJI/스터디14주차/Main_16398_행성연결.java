import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16398_행성연결 {

	static int N;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->Integer.compare(x[2], y[2]));
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp=Integer.parseInt(st.nextToken());
				if(i==j) continue;
				
				pq.offer(new int[] {i,j,temp});
			}
		}
		
		
		parents=new int[N];
		for (int i = 0; i < N; i++) {
			parents[i]=i;
		}
		
		long total=0;
		int cnt=0;
		while(!pq.isEmpty()) {
			int[] cur= pq.poll();
			
			if(union(cur[0], cur[1])) {
				total+=(long) cur[2];
				cnt++;
			}
			
			if(cnt==N) break;
		}
		
		System.out.println(total);
	}

	static int find(int a) {
		if(parents[a]==a) return a;
		else return parents[a]=find(parents[a]);
	}
	
	static boolean union(int a,int b) {
		int rootA=find(a);
		int rootB=find(b);
		
		if(rootA==rootB) return false;
		
		parents[rootB]=rootA;
		return true;
	}
}
