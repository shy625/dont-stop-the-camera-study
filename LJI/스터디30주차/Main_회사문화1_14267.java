import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_회사문화1_14267 {
//그래프 문제 , dfs로 부하에게 계속 같은 칭찬을 더해주면 될 것 같다
// 칭찬은 무조건 부하에게 넘어오는 구조이기에 처음에 칭찬들을 받고 부하쪽에 초기값으로 넣어놓고 그 값을 이동시키면 1(사장)에서 시장되는 한번에 dfs로 전부 구하는게 가능할 것
	static int n,m;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] answerList;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		graph=new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		answerList=new int[n+1];
		
		st=new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int bossNo=Integer.parseInt(st.nextToken());
			if(i==1)continue;//사장은 상사가 없다
			
			graph.get(bossNo).add(i);//상사에게 부하직원 번호 적어놓기
		}
		
		
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());
			int no=Integer.parseInt(st.nextToken());//직원 번호
			int w=Integer.parseInt(st.nextToken());//칭찬 받은 정도
			answerList[no]+=w;
		}
		
		//사장부터 시작
		praise(1,0);//칭찬 받는 당사자, 칭찬의 양
		
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i <=n; i++) {
			sb.append(answerList[i]).append(' ');
		}
		System.out.println(sb.toString());
	}
	
	private static void praise(int no, int amount) {
		answerList[no]+=amount;
		for (int nextNo:graph.get(no)) {//그 직원의 직속 부하들에게 현재 상사가 받은 칭찬의 양만큼 전달
			praise(nextNo, answerList[no]);
		}
	}

}
