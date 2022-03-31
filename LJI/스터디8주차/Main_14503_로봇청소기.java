import java.util.Scanner;

public class Main_14503_로봇청소기 {

	static int N,M;
	static int[][] map;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int r,c,d;
	static int cleanCnt;
	static int order;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		M=scann.nextInt();
		
		map=new int[N][M];
		r=scann.nextInt();
		c=scann.nextInt();
		d=scann.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		cleanCnt=0;
		
		order=1;//order 가 1이면 1부터 수행 2면 2부터 수행
		while(move()){
			
		}
		
		System.out.println(cleanCnt);
	}
	private static boolean move() {
		//1번
		if(map[r][c]==0&&order==1) {
			map[r][c]=2;
			cleanCnt++;
		}
		
		
		//2
		
		
		for (int i = 0; i < 4; i++) {
			int left=(d-1)%4;

			if(left==-1) left=3;
			
			int lr=r+dr[left];
			int lc=c+dc[left];
			//a
			if(check(lr,lc)&&map[lr][lc]==0) {//왼쪽으로 이동 가능
				d= left;
				r=lr;
				c=lc;
				order=1;
				return true;
			}
			//b
			d=left;
		}
		order=2;
		int back=(d+2)%4;//뒤
		int br=r+dr[back];
		int bc=c+dc[back];
		
		if(check(br,bc)&&map[br][bc]!=1) {//c
			r=br;
			c=bc;
			return true;
		}else//d
		{
			return false;
		}
	
	}
	private static boolean check(int r, int c) {
		return r>=0 &&r<N&&c>=0&&c<M;
	}

}
