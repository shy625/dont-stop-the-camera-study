import java.util.Scanner;

public class Main_18428_감시피하기 {
 
	static int N;
	static char[][] map;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static String answer="NO";
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		map=new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.next().charAt(0);
			}
		}
		
		SetObj(0,0);
		System.out.println(answer);
	}
	
	//장애물 설치하는 함수
	private static void SetObj(int cnt, int num) {//장애물 갯수 , 좌표 위치
		if(cnt==3) {
			//선생들이 학생 감시해보는 함수
			CheckSafe();
			return;
		}
		
		//장애물 추가
		for (int i = num; i < N*N; i++) {
			int r=i/N;
			int c=i%N;
			if(map[r][c]=='X') {
				map[r][c]='O';
				SetObj(cnt+1, i+1);
				map[r][c]='X';
				if(answer.equals("YES"))return;
			}
		}
	}

	//도중에 학생 한명이라도 걸리면 return
	private static void CheckSafe() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='T') {
					//학생이 한명이라도 걸렸다면 종료
					if(CheckStu(i,j))return;
				}
			}
		}
		
		answer="YES";
	}

	private static boolean CheckStu(int sR, int sC) {
		for (int d = 0; d < 4; d++) {
			int nr=sR;
			int nc=sC;
			while(true) {
				nr+=dr[d];
				nc+=dc[d];
				
				if(!check(nr,nc)) break;
				
				if(map[nr][nc]=='O')break;
				if(map[nr][nc]=='S')return true;
			}
		}
		return false;
	}

	private static boolean check(int r, int c) {
		return 0<=r&&r<N&&0<=c&&c<N;
	}

}
