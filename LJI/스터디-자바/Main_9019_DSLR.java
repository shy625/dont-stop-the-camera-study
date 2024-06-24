import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019_DSLR {

	static int N,T;
	static int min;
	static int A,B;
	static char dslr[]= {'D','S','L','R'};
	//방문한 숫자 체크하는 것도 필수! 아니면 시초난다
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T=Integer.parseInt(br.readLine());
		
		for (int t = 1; t <=T; t++) {
			st=new StringTokenizer(br.readLine());
			A=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			
			//BFS
			Queue<Node> que=new LinkedList<>();
			que.offer(new Node(A,new StringBuilder()));
			boolean v []=new boolean[10000];
			v[A]=true;
			while(!que.isEmpty()) {
				Node cur=que.poll();
				int curNum=cur.num;
				
				if(curNum==B) {
					System.out.println(cur.sb.toString());
					break;
				}
				
				StringBuilder temp;
				
				//DSLR
				for (int i = 0; i < 4; i++) {
					int tempNum=DSLR(curNum,dslr[i]);
					if(v[tempNum]) continue;
					
					v[tempNum]=true;
					temp=new StringBuilder(cur.sb.toString());
					temp.append(dslr[i]);
					que.offer(new Node(tempNum, temp));
				}
				
				
			}
		}
	}
	

	private static int DSLR(int num, char oper) {
		int result=0;
		if(oper=='D') {
			if(num==0) result=0;
			else result= (num*2)%10000;
		}else if(oper=='S') {
			if(num==0) result=9999;
			else result=num-1;
		}else if(oper=='L') {
			if(num==0) result=0;
			else result=(num%1000)*10+num/1000;
		}else if(oper=='R') {
			if(num==0) result=0;
			else result=(num/10)+(num%10)*1000;
		}
		return result;
	}


	static class Node{
		int num;
		StringBuilder sb;
		public Node(int num, StringBuilder sb) {
			super();
			this.num = num;
			this.sb = sb;
		}
		
	}
}
