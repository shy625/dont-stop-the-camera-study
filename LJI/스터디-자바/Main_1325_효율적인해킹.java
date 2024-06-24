import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1325_효율적인해킹 {

	static int N,M;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		max=0;
		
		ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
		for (int i = 0; i <=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i <M; i++) {
			st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			graph.get(B).add(A);//A가 B를 신뢰한다 == B->A 의 그래프 형태
		}
		
		//bfs 사용해보자
		//먼저 각 요소마다 bfs로 연결된 컴퓨터 찾아 갯수 저장,
		int cntArr[]=new int[N+1];//연결된 컴퓨터 갯수 저장할 배열
		for (int i = 1; i <=N; i++) {
			boolean v[]=new boolean[N+1];
			
			Queue<Integer> que=new LinkedList<>();
			int cnt=0;
			que.offer(i);
			v[i]=true;
			
			while (!que.isEmpty()) {
				int cur=que.poll();
				cnt++;
				
				for(int next:graph.get(cur)) {
					if(v[next]) continue;
					
					v[next]=true;
					que.offer(next);
				}
			}
			
			cntArr[i]=cnt;
			max=Integer.max(max, cnt);
		}
		
		
		//정답 출력 하기
		ArrayList<Integer> answerList=new ArrayList<>();
		for (int i = 1; i <=N; i++) {
			if(cntArr[i]==max) {
				answerList.add(i);
			}
		}
		Collections.sort(answerList);
		StringBuilder sb=new StringBuilder();
		for (int now:answerList) {
			sb.append(now).append(' ');
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
