import java.util.Arrays;
import java.util.Scanner;

public class Main_2344_거울 {

	//1->9 , 9->1은 같다//즉 연결되는 선이 이미 있다면 하지 않아도 될 것//맵도 가능할 듯?
	static int N,M;
	static int[][] map;
	//방향은 상우하좌
	static int[]dr= {-1,0,1,0};
	static int[]dc= {0,1,0,-1};
	static int [] answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		map=new int[N+2][M+2];
		for (int i = 0; i < N+2; i++) {
			Arrays.fill(map[i], -1);
		}
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		//계산 시작
		answer=new int[2*N+2*M+1];
		
		//왼쪽 벽// 1방향으로 쏨 //c=0
		for (int i = 1; i < N+1; i++) {
			int Num=i;//번호 구하기
			if(answer[Num]!=0)continue;
			Lazer(Num,i,0,1);
		}
		//아래 벽// 0
		for (int i = 1; i < M+1; i++) {
			int Num=N+i;//번호 구하기
			if(answer[Num]!=0)continue;
			Lazer(Num,N+1,i,0);
		}
		//오른쪽 벽//3
		for (int i = 1; i < N+1; i++) {
			int Num=N+M+(N-i+1);//번호 구하기
			if(answer[Num]!=0)continue;
			Lazer(Num,i,M+1,3);
		}
		//위쪽 벽//2
		for (int i = 1; i < M+1; i++) {
			int Num=2*N+M+(M-i+1);//번호 구하기
			if(answer[Num]!=0)continue;
			Lazer(Num,0,i,2);
		}
		
		//리스트 제작
		StringBuilder sb=new StringBuilder();
		for (int i = 1; i < answer.length; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb.toString());
	}
	private static void Lazer(int startNum, int startR, int startC, int d) {
		// TODO Auto-generated method stub
		int r=startR+dr[d];
		int c=startC+dc[d];
		//레이저 쏘기
		while(map[r][c]!=-1) {
			if(map[r][c]==0) {//현재 위치의 거울 없음 //계속 직진
				r=r+dr[d];
				c=c+dc[d];
			}else {//거울 존재
				//빛의 방향 회전
				if(d==0) d=1;
				else if(d==1) d=0;
				else if(d==2) d=3;
				else d=2;
				
				r+=dr[d];
				c+=dc[d];
			}
		}
		
		//r,c의 번호 구하기
		int endNum=0;
		
		//위쪽벽
		if(r==0) {
			endNum=2*N+M+(M-c+1);
		}else if(r==N+1) {//아래쪽 벽
			endNum=N+c;
		}else if(c==0) {//왼쪽 벽
			endNum=r;
		}else {//오른쪽 벽
			endNum=N+M+(N-r+1);
		}
		//번호 추가
		answer[startNum]=endNum;
		answer[endNum]=startNum;
	}
}
