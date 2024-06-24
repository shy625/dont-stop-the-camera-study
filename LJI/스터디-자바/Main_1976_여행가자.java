import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {

	//bfs만 써도 될 듯
	static int N,M;
	static boolean [] v;
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());

		list=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				int temp=Integer.parseInt(st.nextToken());
				if(temp==1) list.get(i).add(j);
			}
		}
		
		int [] tourList=new int[M];
		v=new boolean[N];//갈 수 있는 도시인지 체크
		st=new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < M; i++) {
			tourList[i]=Integer.parseInt(st.nextToken())-1;
		}
		
		bfs(tourList[0]);
		
		boolean canGo=true;
		for (int i = 1; i < M; i++) {//투어리스트에 0번쨰에서 bfs했기에 0은 무조건 포합된다
			if(!v[tourList[i]]) {
				canGo=false;
				break;
			}
		}
		System.out.println(canGo?"YES":"NO");
	}
	private static void bfs(int start) {
		
		Queue<Integer> que=new LinkedList<>();
		que.offer(start);
		v[start]=true;
		while(!que.isEmpty()) {
			int now=que.poll();
			for(int next:list.get(now)) {
				if(v[next]) continue;
				v[next]=true;
				que.offer(next);
			}
		}
	}
}
