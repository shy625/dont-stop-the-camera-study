import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main_12786_INHASUIT {

	//전칸의 구멍에서 T의 횟수를 계속 갱신해나가면 될 것 같다
	static int N,K,M;
	static int[][] hole;//
	static boolean[][] v;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		K=scann.nextInt();
		
		hole=new int[N+1][21];
		v=new boolean[N+1][21];
		
		//구멍 정보 입력 받기
		for (int i = 0; i <= N; i++) {
			//구멍의 t 회수 K+1로 초기화 해놓기
			Arrays.fill(hole[i], K+1);
			
			//시작 위치 초기화
			if(i==0) {
				v[0][1]=true;
				hole[0][1]=0;//처음 T회수는 0이다
				continue;
			}
			
			//나무 정보 받기
			int num=scann.nextInt();// 홀의 개수
			for (int j = 1; j <= num; j++) {
				int holeNum=scann.nextInt();
				
				v[i][holeNum]=true;
			}
			
		}
		
//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(v[i]));
//		}
		//0(시작위치) 부터 OABCT 탐색 시작
		for (int i = 0; i < N; i++) {//N줄은 더 갈 필요 없음
			for (int j = 1; j <= 20; j++) {//0 칸은 사실상 없음
				if(v[i][j]) {//구멍이 있는 곳 
					//System.out.println("find!");
					int h=0;
					//O //제자리 이동
					h=j;
					if(v[i+1][h]) {
						if(hole[i+1][h]>hole[i][j]) hole[i+1][h]=hole[i][j];
					}
					//A //한칸 위 이동
					h=j+1;
					if(h<=20 && v[i+1][h]) {
						if(hole[i+1][h]>hole[i][j]) hole[i+1][h]=hole[i][j];
					}
					//B //현재 위치만큼 위로
					h=j*2;
					if(h>20)h=20;
					if(v[i+1][h]) {
						if(hole[i+1][h]>hole[i][j]) hole[i+1][h]=hole[i][j];
					}
					//C //한칸 아래 이동
					h=j-1;
					if(h>0 && v[i+1][h]) {
						if(hole[i+1][h]>hole[i][j]) hole[i+1][h]=hole[i][j];
					}
					//T
					for (int k = 1; k <=20; k++) {
						if(v[i+1][k]) {//다음 칸에 구멍 있다면 t로 이동 가능//t로 이동하기에 회수 1 증가
							if(hole[i+1][k]>hole[i][j]+1) hole[i+1][k]=hole[i][j]+1;
						}
					}
				}
			}
		}
		
		//마지막 줄에서 T의 개수 제일 적은거 찾기
		int answer=K+1;
		for (int i = 1; i <=20; i++) {
			
			if(answer>hole[N][i])answer=hole[N][i];
		}
		
		if(answer==K+1)answer=-1;
		
		System.out.println(answer);
	}

}
