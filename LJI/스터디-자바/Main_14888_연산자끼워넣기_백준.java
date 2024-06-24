import java.util.Scanner;

public class Main_14888_연산자끼워넣기_백준 {

	static int Max,Min;
	static int N;//숫자 갯수
	static int[] num;
	static int[] oper;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		N=scann.nextInt();
		num=new int[N];
		for (int i = 0; i < N; i++) {
			num[i]=scann.nextInt();
		}//숫자 다 받음
		oper=new int[4];
		for (int i = 0; i < 4; i++) {
			oper[i]=scann.nextInt();
		}//연산자 갯수 받음
		Max=Integer.MIN_VALUE;
		Min=Integer.MAX_VALUE;
		operation(0,num[0]);
		
		System.out.println(Max);
		System.out.println(Min);
	}
	private static void operation(int cnt,int calNum) {//카운트와 앞에서 계산된 숫자
		if(cnt==N-1) {
			Max=Math.max(calNum, Max);
			Min=Math.min(calNum, Min);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(oper[i]>0) {//연산자가 남아있다면?//꺼내서 계산하라
				int temp=0;
				if(i==0) {//더하기
					temp=calNum+num[cnt+1];
				}else if(i==1) {//빼기
					temp=calNum-num[cnt+1];
				}
				else if(i==2) {//곱셈
					temp=calNum*num[cnt+1];
				}
				else if(i==3) {//나눗셈
					if(calNum<0) temp= -(Math.abs(calNum)/num[cnt+1]);
					else temp=calNum/num[cnt+1];
				}
				oper[i]--;
				operation(cnt+1, temp);
				oper[i]++;
			}
		}
	}

}
