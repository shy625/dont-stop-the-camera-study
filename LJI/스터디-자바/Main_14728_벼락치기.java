import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main_14728_벼락치기 {

	static int N,T;
	static class Chapter implements Comparable<Chapter>{
		int K;
		int S;
		public Chapter(int k, int s) {
			super();
			K = k;
			S = s;
		}
		//걸리는 시간 순 정렬
		@Override
		public int compareTo(Chapter o) {
			return Integer.compare(this.K, o.K)==0?Integer.compare(o.S,this.S):Integer.compare(this.K, o.K);
		}
		
	}
	
	static int [][]dp;
	static ArrayList<Chapter> chapters;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		T=scann.nextInt();
		
		dp=new int[N+1][T+1];
		chapters=new ArrayList<>();
		
		//단원 추가 및 정렬
		for (int i = 0; i < N; i++) {
			int k=scann.nextInt();
			int s=scann.nextInt();
			chapters.add(new Chapter(k, s));
		}
		Collections.sort(chapters);
		
		//시작
		for (int i = 1; i <=N; i++) {//N가지의 chapter를 추가해본다
			Chapter now=chapters.get(i-1);//현재 위치의 챕터 가져오기
			
			for (int j = 1; j <=T; j++) {
				dp[i][j]=dp[i-1][j];
				
				//이번 챕터를 추가한 시간과 비교
				int leftTime=j-now.K;
				if(leftTime>=0) {
					dp[i][j]=Math.max(dp[i][j], dp[i-1][leftTime]+now.S);
				}
			}
		}
		
		System.out.println(dp[N][T]);
	}

}
