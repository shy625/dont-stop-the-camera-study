import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14699_관악산등산 {
	
	static int N, M;
	static int []dp;
	static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] height=new int[N+1];
		dp=new int[N+1];
		//높이 입력
		st=new StringTokenizer(br.readLine());
		for (int i = 1; i <=N; i++) {
			height[i]=Integer.parseInt(st.nextToken());
		}
		
		//그래프 초기화
		list=new ArrayList<>();
		for (int i = 0; i <=N; i++) {
			list.add(new ArrayList<>());
		}
		
		boolean [][]c=new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			//중복 길 체크
			if(c[a][b]) continue;
			c[a][b]=true;
			c[b][a]=true;
			
			//낮은 곳에서 높은 곳으로 연결 리스트
			if(height[a]>height[b]) {//b->a
				list.get(b).add(a);
			}else if(height[b]>height[a]) {//a->b
				list.get(a).add(b);
			}
		}
		
		//길 dfs로 계산
		for (int i = 1; i <=N; i++) {
			dfs(i);
		}
		
		//출력
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dp[i]).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	private static int dfs(int n) {
		int cnt=1;//현재 쉼터 1
		if(dp[n]!=0) return dp[n];//이미 값이 있는 곳이면 걍 값만 받아오자
		
		int size=list.get(n).size();
		for (int i = 0; i < size; i++) {
			int next=list.get(n).get(i);
			cnt=Integer.max(cnt, dfs(next)+1);//그 전 쉼터와 현재 쉼터 더한 갯수와 현재 최댓값 비교
		}
		
		dp[n]=cnt;
		return cnt;
	}

}
