import java.util.Scanner;
import java.util.Stack;
public class Main_1874 {

	static int n;
	public static void main(String[] args) {
		StringBuilder sb=new StringBuilder();
		Stack<Integer> stack=new Stack<>();
		Scanner scann=new Scanner(System.in);
		n=scann.nextInt();
		int now=0;
		int cnt=1;
		int temp=0;
		for(int i=1;i<=n;i++) {
			now=scann.nextInt();//찾아야할 것
			while(true) {//현재 요소 해결할 떄 까지 반복
				if(now>=cnt) {//스택에 계속 푸시해야함//언제까지? now 원소까지 팝 할때까지
					stack.push(cnt++);
					sb.append("+").append("\n");
				}else {//cnt가 now보다 크다?//pop해야함//그러나 전에 더 작은수를 출력했으면 이미 스택이 비었기에 출력 불가
					if(stack.isEmpty()) {//비었다면 더 cnt가 now보다 높고 스택은 비어서 더 이상 출력 불가
						System.out.println("NO");
						return;
					}else {
						temp=stack.pop();
						sb.append("-").append("\n");;
						if(temp==now) break;
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}
