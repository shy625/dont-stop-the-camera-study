import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main_5545_최고의피자 {

	static int N,A,B,C;
	static Integer[] topping;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		
		A=scann.nextInt();
		B=scann.nextInt();
		C=scann.nextInt();
		topping=new Integer[N];
		for (int i = 0; i < N; i++) {
			topping[i]=scann.nextInt();
		}
		
		Arrays.sort(topping, Collections.reverseOrder());
		
		int cal=C/A;
		//토핑을 칼로리 높은 것부터 추가하면서 가격이 높아질때만 지속하자
		int nowCost=A;//도우 기본
		int nowCal=C;
		for (int i = 0; i < N; i++) {
			nowCost += B;
			nowCal += topping[i];
			
			int newCal=nowCal/nowCost;
			if(cal> newCal) {
				break;
			}else {
				cal=newCal;
			}
		}
		System.out.println(cal);
	}


}
