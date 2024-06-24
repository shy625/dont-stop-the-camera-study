import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10282_해킹 {

	static int T,n,d,c;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T=Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			c=Integer.parseInt(st.nextToken())-1;
			
			ArrayList<ArrayList<int[]>> graph=new ArrayList<>(); //도착지점과 초 저장
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i = 0; i < d; i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				int s=Integer.parseInt(st.nextToken());
				
				//b가 감염되면 a가 감염되기에 b->a의 그래프이다
				graph.get(b).add(new int[] {a,s}); //b->a 가는데 s초 걸린다
			}
			
			int [] dijk=new int[n]; //c에서 각 노드로 가는데 걸리는 시간
			Arrays.fill(dijk, Integer.MAX_VALUE);
			dijk[c]=0;
			
			PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->Integer.compare(x[1], y[1]));
			
			//c번호 넣기
			pq.offer(new int[] {c,0});//시작, 거리
			
			int cnt=0;//감염 수
			boolean[] v=new boolean[n];
			//다익스트라로 c에서 가장 오래걸리는 컴퓨터 위치까지가 총 걸리는 시간
			while(!pq.isEmpty()) {
				int [] cur=pq.poll();
				
				if(!v[cur[0]]) {//동일한건 세면 안된다
					v[cur[0]]=true;
					cnt++;
				}
				if(cnt==n) break;//모든 컴퓨터 감염
				
				int size=graph.get(cur[0]).size();//현재와 연결된 곳 찾기
				
				for (int i = 0; i < size; i++) {
					int [] now=graph.get(cur[0]).get(i);
					
					if(dijk[now[0]]>dijk[cur[0]]+now[1]) {
						dijk[now[0]]=dijk[cur[0]]+now[1];
						pq.offer(new int[] {now[0], dijk[now[0]]});
					}
				}
			}
			int max=0;
			for (int i = 0; i < n; i++) {
				if(dijk[i] !=Integer.MAX_VALUE) {
					max=Math.max(max, dijk[i]);
				}
			}
			
			System.out.println(cnt+" "+max);
		}
	}
}
