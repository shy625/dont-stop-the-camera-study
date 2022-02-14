import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_17298_오큰수_백준 {

	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		Stack<Integer> stack=new Stack<>();
		for (int i = 0; i < N; i++) {
			stack.push(Integer.parseInt(st.nextToken()));
		}
		int [] num=new int[N];
		Arrays.fill(num, -1);//정답을 출력할 배열은 못찾은 경우를 대비해 -1로 초기화
		//알고리즘
		//스택에 순서대로 쌓은 요소를 역순으로 탐색하면서 OkenStack이 빌때까지 OkenStack에 큰게 없으면 -1
		//OkenStack에 큰게 있다면 그 요소는 다시 집어넣고 탐색 계속
		Stack<Integer> okenStack=new Stack<>();
		for (int i = N-1; i >=0; i--) {
			int now=stack.pop();
			while(!okenStack.isEmpty()) {
				int oken=okenStack.pop();//이 요소가 now보다 크면 oken수가 된다//이 오큰수는 다시 활용될 수 있기에 push
				if(oken>now) {
					num[i]=oken;
					okenStack.push(oken);
					break;
				}
			}
			okenStack.push(now);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(num[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
