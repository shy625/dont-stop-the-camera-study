import java.util.ArrayList;
import java.util.Scanner;

public class Main_1174_줄어드는수 {

	//음이 아닌 정수 0~n
	//첫번째로 작은 줄어드는 수 =0; //한자리 수도 포함 //같은 수도 줄어드는 수에 포함 안된다
	//최대숫자 9876543210
	static int N;
	static int num[]= {9,8,7,6,5,4,3,2,1,0};//내림차 정리
	static ArrayList<Long> list=new ArrayList<>();
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		makeNum(0,0);
		list.sort(null);
		if(N>list.size())
			System.out.println(-1);
		else
			System.out.println(list.get(N-1));
	}
	private static void makeNum(long sum, int cnt) {
		if(!list.contains(sum))
			list.add(sum);
		if(cnt>=10) 
			return;
		makeNum((sum*10)+num[cnt], cnt+1);
		makeNum(sum,cnt+1);
		
	}
	


}
