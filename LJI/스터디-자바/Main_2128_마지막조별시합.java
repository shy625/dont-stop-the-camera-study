import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2128_마지막조별시합 {
	static int N,D,K;
	static int arr[];
	static int maxA;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new int[N];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int stuSolve=0;
			for (int j = 0; j <num; j++) {
				int solveNum=Integer.parseInt(st.nextToken())-1;
				stuSolve |= (1<<solveNum);
			}
			
			arr[i]=stuSolve;
		}
		
		maxA=0;
		find(0,0,0);
		System.out.println(maxA);
	}
	private static void find(int cnt, int n, int solve) {//풀 수 있는 문제들 기록
		if(cnt==15) {//문제 총 갯수가 15개
			int cntStu=0;
			//각 학생이 현재 풀기 가능한 문제들 안에 있는지 체크
			for (int i = 0; i < N; i++) {
				if((arr[i]|solve)> solve) continue;
				cntStu++;
			}
			maxA=Math.max(maxA, cntStu);
			return;
		}
		
		//현재 문제를 포함
		int newSolve=solve|1<<cnt;
		if(Integer.bitCount(newSolve)<=K) 
			find(cnt+1,n+1,newSolve);
		//현재 문제를 포함하지 않음
		find(cnt+1,n,solve);
	}

}
