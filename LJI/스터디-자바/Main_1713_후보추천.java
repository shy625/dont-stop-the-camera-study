import java.util.Scanner;

public class Main_1713_후보추천 {

	static class Stu{
		int num;//학생 번호
		int cnt;//추천 횟수
		int turn;//지속 시간
		public Stu(int num, int cnt, int turn) {
			super();
			this.num = num;
			this.cnt = cnt;
			this.turn = turn;
		}
		
	}
	
	static Stu[] line;
	static int N,T;
	static boolean [] v;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		T=scann.nextInt();
		
		line=new Stu[N+1];
		v=new boolean[101];
		for (int t = 0; t < T; t++) {
			int now=scann.nextInt();
			
			//빠질 학생 정보 기록
			int minCnt=10000;
			int minTurn=10000;
			int minNum=0;
			boolean done=false; 
			for (int i = 1; i <= N; i++) {
				//빈칸이면 바로 넣기
				if(line[i]==null) {
					line[i]=new Stu(now,1,t);
					v[now]=true;
					done=true;
					break;
				}
				
				if(line[i].num==now) {
					line[i].cnt++;
					done=true;
					break;
				}
				
				if(minCnt>line[i].cnt) {
					minCnt=line[i].cnt;
					minTurn=line[i].turn;
					minNum=i;
				}
				
				if(minCnt==line[i].cnt) {
					if(minTurn>line[i].turn) {
						minTurn=line[i].turn;
						minNum=i;
					}
				}
			}
			if(done)continue;
			
			v[line[minNum].num]=false;
			line[minNum]=new Stu(now,1,t);
			v[now]=true;
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i <=100; i++) {
			if(v[i])
				sb.append(i).append(' ');
		}
		
		System.out.println(sb.toString());
	}

}
