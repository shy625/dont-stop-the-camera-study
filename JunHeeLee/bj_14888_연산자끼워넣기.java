package algo1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class bj_14888_연산자끼워넣기 {
	static int N;//수의갯수
	static int[] num;//수를 담을 배열
	static int[] op; //연산자수 담을 배열
	static int Max,Min;//최대,최솟값
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in); // N이 최대11이므로 scanner 사용
		N = scann.nextInt();
		num = new int[N];
		op = new int[4];//'+','-','*','/'
		Max = Integer.MIN_VALUE;
		Min = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			num[i] = scann.nextInt();
		}
		for(int i=0;i<4;i++) {
			op[i] = scann.nextInt();
		}
		dfs(1,num[0]);//처음 숫자부터 차례대로 이므로 cnt에는 1, 처음합은 처음숫자로해서 넣어준다
		System.out.println(Max);
		System.out.println(Min);
	}
	
	private static void dfs(int cnt, int sum) {
		if (cnt==N) {
			Max = Math.max(Max, sum);
			Min = Math.min(Min, sum);
			return;
		}
		int next_num = num[cnt];//연산자기호 다음에 올 숫자
		for(int i=0;i<4;i++) {
			if(op[i]>0) {
				if(i==0) {
					op[i] = op[i]-1;
					dfs(cnt+1,sum+next_num);
					op[i] = op[i]+1;
				}
				else if(i==1) {
					op[i] = op[i]-1;
					dfs(cnt+1,sum-next_num);
					op[i] = op[i]+1;
				}
				else if(i==2) {
					op[i] = op[i]-1;
					dfs(cnt+1,sum*next_num);
					op[i] = op[i]+1;
				}
				else{
					op[i] = op[i]-1;
					dfs(cnt+1,sum/next_num);
					op[i] = op[i]+1;
				}
			}
		}
		
	}
}
