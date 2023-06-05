import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main_2800_괄호제거 {
	//stack에 있는 값이 true면 문자열 포함 false면 그냥 패스
	static char [] ch;
	static int N;
	static PriorityQueue<String> pq;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		ch=scann.next().toCharArray();
		N=ch.length;
		pq=new PriorityQueue<>();

		makeString(0,new StringBuilder(),new Stack<Boolean>());
		
		//기존 문장은 포함하지 않기에 하나는 poll()해주자
		pq.poll();
		
		
		//놓치고 있던거 !!!!
		//서로 다른 식이 므로 동일 위치에 괄호 여러개 있다면 중복이 나올 수 있으니 이것을 유의해야한다!
		//따라서 중복 제거 처리를 해주어야한다
		StringBuilder sb=new StringBuilder();
		String preStr="";
		while(!pq.isEmpty()) {
			String temp=pq.poll();
			if(temp.equals(preStr)) continue;//pq이기에 똑같은 문자열은 같이 있다 , 따라서 전 문자열과 비교만 하면 된다.
			
			sb.append(temp).append("\n");
			preStr=temp;
		}
		System.out.println(sb.toString());
	}
	private static void makeString(int cnt,StringBuilder sb, Stack<Boolean> st) {
		if(cnt==N) {//문자열 끝
			pq.offer(sb.toString());
			return;
		}
		
		if(ch[cnt]=='(') {
			StringBuilder sb2=new StringBuilder(sb.toString());
			Stack<Boolean> st2=(Stack<Boolean>) st.clone();
			
			//문자열에 ( 추가
			sb.append('(');
			st.push(true);//문자열에 포함되는 ( 는 true
			makeString(cnt+1, sb, st);
			
			//문자열에 ( 추가 X
			st2.push(false);
			makeString(cnt+1, sb2, st2);
			
		}else if(ch[cnt]==')') {
			if(st.pop()) {//문자열 포함
				sb.append(')');
			}
			makeString(cnt+1, sb, st);
		}else {//나머지 문자들은 그냥 추가
			sb.append(ch[cnt]);
			makeString(cnt+1, sb, st);
		}
	}
}
