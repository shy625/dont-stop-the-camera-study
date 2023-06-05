import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17836_공주님을구해라 {

	static class Hero {
		int r;
		int c;
		boolean gram;
		int t;
		public Hero(int r, int c, boolean gram, int t) {
			super();
			this.r = r;
			this.c = c;
			this.gram = gram;
			this.t = t;
		}

	}

	static int dr[]= {-1,0,1,0};
	static int dc[]= {0,1,0,-1};
	
	static int N, M, T;

	static int map[][];
	static boolean c[][][];

	static Queue<Hero> que;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		T=scann.nextInt();
		
		map=new int[N][M];
		c=new boolean[N][M][2];//마지막은 그람 여부
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		//시작위치 초기화
		que=new LinkedList<>();
		que.offer(new Hero(0, 0, false, 0));
		c[0][0][0]=true;
		
		while(!que.isEmpty()) {
			int turn=que.size();
			for (int ti = 0; ti < turn; ti++) {
				Hero cur=que.poll();
				//System.out.println(cur.r+","+cur.c+" "+cur.gram+" t:"+cur.t);
				if(cur.r==N-1&&cur.c==M-1) {
					if(cur.t<=T) {
						System.out.println(cur.t);
						return;
					}else {
						System.out.println("Fail");
						return;
					}
				}
				
				if(map[cur.r][cur.c]==2) {
					cur.gram=true;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr=cur.r+dr[d];
					int nc=cur.c+dc[d];
					if(!check(nr,nc)) continue;
					
					if(cur.gram||map[nr][nc]!=1) {//검이 있거나 가려는 곳이 0이면
						if(cur.gram) {
							if(!c[nr][nc][1]) {
								c[nr][nc][1]=true;
								que.offer(new Hero(nr, nc, cur.gram, cur.t+1));
							}
						}else {
							if(!c[nr][nc][0]) {
								c[nr][nc][0]=true;
								que.offer(new Hero(nr, nc, cur.gram, cur.t+1));
							}
						}
					}
				}
			}
		}
		System.out.println("Fail");
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}

}
