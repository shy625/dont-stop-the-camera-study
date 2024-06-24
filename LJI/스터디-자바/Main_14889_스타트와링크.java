import java.util.Arrays;
import java.util.Scanner;

public class Main_14889_스타트와링크 {

	static int N,half;
	static int[][] map;
	static int min;
	static boolean [] v;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		N=scann.nextInt();
		half=N/2;
		v=new boolean[N];//체크된 인원과의 합 계산
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		
		min=Integer.MAX_VALUE;//팀간의 차이 계산
		ncr(0,0,0);//cnt,start,stat
		System.out.println(min);
	}
	private static void ncr(int cnt, int start, int stat) {
		if(cnt==half) {
			int otherStat=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(v[i] || v[j]) continue;
					otherStat+=map[i][j];
				}
			}
			int diff=Math.abs(stat-otherStat);
			min=Math.min(diff, min);
			return;
		}
		
		for (int i = start; i < N; i++) {
			int temp=0;//stat에 추가할 값 계산
			for (int j = 0; j < N; j++) {
				if(v[j]) {
					temp+=(map[i][j]+map[j][i]);
				}
			}
			v[i]=true;
			int stathap=temp+stat;
			ncr(cnt+1,i+1,stathap);
			v[i]=false;
		}
	}
}
