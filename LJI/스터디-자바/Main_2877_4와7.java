import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main_2877_4와7 {

	static int K;
	//K-1를 2진수로 바꿔서 0->4 , 1->7로 바꾸면 될 듯? 
	//이떄
	//0 , 1, 01, 10, 000, 001, 010...
	//앞에 숫자를 하나 두면 ?
	//(1)0==1, (1)1==2, (1)00==3 , 즉 원하는 숫자 K+1를 이진수로 바꾸고 맨 앞만 버리면 될듯
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		K=scann.nextInt()+1;
		
		Stack<Integer> stack=new Stack<>();
		
		while(K>0) {
			int temp=K%2;
			stack.push(temp);
			K/=2;
			
		}
		
		StringBuilder sb=new StringBuilder();
		stack.pop();//맨앞 1 버리기
		while(!stack.isEmpty()) {
			int temp=stack.pop();
			if(temp==0)sb.append('4');
			else sb.append('7');
		}
		
		System.out.println(sb.toString());
	}

}
