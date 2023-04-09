import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21738_얼음깨기펭귄 {

	//최소 유지 조건인 2개의 지지대 얼음을 골라서 거기서 펭귄까지의 거리를 총 얼음 개수에서 빼주면 된다
	//추가로 하나의 2개의 지지대에서 거리기에 +1을 해주어 펭귄이 있는 발판이 중복 제거되는 것을 방지하자
	//위에 방법은 시초
	//펭귄으로부터 각 거리를 재서 해야할 것 같다
	static int N,S,P;
	static ArrayList<ArrayList<Integer>> iceList;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		S=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		
		iceList=new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			iceList.add(new ArrayList<>());
		}
		//연결 입력 받기//N-1개 주어짐
		for (int i = 1; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			iceList.get(a).add(b);
			iceList.get(b).add(a);
		}
		
		//거리 측정
		ans=0;
		Find();
		
		System.out.println(ans);
		
	}
	private static void Find() {
		int dis=0;//현재 얼음과 펭귄과의 거리
		int cnt=0;//발견된 지지대의 개수 셀 것//2개 되면 끝내자
		int total=0;//두 지지대와 펭귄 사이의 거리
		boolean [] v=new boolean[N+1];
		
		//
		Queue<Integer> que=new LinkedList<Integer>();
		que.offer(P);
		while(!que.isEmpty()) {
			dis++;
			int n=que.size();
			//같은 거리 bfs 
			for (int i = 0; i <n ; i++) {
				//
				int cur=que.poll();
				int len=iceList.get(cur).size();
				for (int j = 0; j <len; j++) {
					
					
					if(!v[iceList.get(cur).get(j)]) {
						v[iceList.get(cur).get(j)]=true;
						que.offer(iceList.get(cur).get(j));
						
						//지지대와 만났다면
						if(iceList.get(cur).get(j)<=S) {
							cnt++;
							//System.out.println(iceList.get(cur).get(j));
							total+=dis;
							if(cnt==2) {
								ans=N-(total+1);
								return;
							}
						}
					}
				}
			}
		}
		
	}
	

}
