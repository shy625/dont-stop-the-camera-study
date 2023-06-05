import java.util.Scanner;

public class Main_21772_가희와고구마먹방 {
	//더 이상 갈곳이 없으면 제자리? 끝낼까?
	//DFS
	static int R,C,T;
	static char [][] map;
	static boolean [][] moveCheck;
	static boolean [][] sp;//고구마 위치 체크용
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int answer;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		R= scann.nextInt();
		C= scann.nextInt();
		T= scann.nextInt();
		
		map=new char[R][C];
		moveCheck=new boolean[R][C];
		sp=new boolean[R][C];
		
		int startR=0;
		int startC=0;
		
		for (int i = 0; i < R; i++) {
			char [] c=scann.next().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j]=c[j];
				
				if(c[j]=='S') {
					sp[i][j]=true;
				}else if(c[j]=='G') {
					startR=i;
					startC=j;
				}
			}
		}
		
		answer=0;
		Move(startR,startC,0,0);//r,c,초,먹은 고구마 개수
		
		System.out.println(answer);
	}
	
	private static void Move(int r, int c, int t, int sweetPotato) {
		
		
		if(answer<sweetPotato) {
			answer=sweetPotato;
		}
		if(t==T)return;
		
		for (int d = 0; d < 4; d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			
			//갈 수 있다면? 이동 처리 후 재귀 호출
			if(!Check(nr,nc)||map[nr][nc]=='#')continue;
			
			//이동시키기
			
			int newSP=sweetPotato;
			boolean eat=false;
			if(map[nr][nc]=='S'&&sp[nr][nc]) {//감자가 있다면
				newSP++;
				eat=true;
				sp[nr][nc]=false;
			}
			
			Move(nr,nc,t+1,newSP);
			
			
			//안 간 걸로 초기화
			if(eat)sp[nr][nc]=true;
		}
	}

	private static boolean Check(int r, int c) {
		return r>=0&&r<R&&c>=0&&c<C;
	}

}
