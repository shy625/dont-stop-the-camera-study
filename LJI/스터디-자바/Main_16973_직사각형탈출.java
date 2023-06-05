import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16973_직사각형탈출 {
	//bfs로 찾는다
	//상하좌우로 이동할 때 새로 부딪힐 구역만 제대로 체크해주면 될 듯
	static int N,M,H,W;
	static int [][] map;
	static int sr,sc,fr,fc;
	static int ans;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		map=new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
			}
		}

		H=scann.nextInt();
		W=scann.nextInt();
		sr=scann.nextInt()-1;
		sc=scann.nextInt()-1;
		fr=scann.nextInt()-1;
		fc=scann.nextInt()-1;
		
		ans=0;
		boolean [][] v=new boolean[N][M];
		v[sr][sc]=true;
		Queue<int[]> que=new LinkedList<int[]>();
		que.offer(new int[] {sr,sc});
		while(!que.isEmpty()) {
			int len=que.size();
			for (int t = 0; t < len; t++) {
				int [] rc=que.poll();
				int r=rc[0];
				int c=rc[1];
				//System.out.println(r+" "+c);
				if(r==fr && c==fc) {//끝
					System.out.println(ans);
					return ;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr=r+dr[d];
					int nc=c+dc[d];
					
					if(!check(nr,nc))continue;
					if(v[nr][nc])continue;
					if(!check(nr,nc,d))continue;
					
					v[nr][nc]=true;
					que.offer(new int[] {nr,nc});
					//System.out.println(nr+" "+nc);
				}
			}
			ans++;
		}
		
		System.out.println("-1");
	}
	private static boolean check(int r, int c, int d) {
		//if(!(r>=0&&r<N&&c>=0&&c<M))return false;
		if(d==0) {//윗방향 이동
			//갈 수 있는지 체크
			int nr=r;
			int nc=c;
			if(!check(nr,nc)) return false;
			
			for (int i = 0; i < W; i++) {
				nc=c+i;
				if(map[nr][nc]==1)return false;
			}
		}else if(d==1) {//오른쪽
			int nr=r;
			int nc=c+W-1;
			if(!check(nr,nc)) return false;
			
			for (int i = 0; i < H; i++) {
				nr=r+i;
				if(map[nr][nc]==1)return false;
			}
		}else if(d==2) {//아래
			int nr=r+H-1;
			int nc=c;
			if(!check(nr,nc)) return false;
			
			for (int i = 0; i < W; i++) {
				nc=c+i;
				if(map[nr][nc]==1)return false;
			}
		}else if(d==3) {//왼쪽
			int nr=r;
			int nc=c;
			if(!check(nr,nc)) return false;
			
			for (int i = 0; i < H; i++) {
				nr=r+i;
				if(map[nr][nc]==1)return false;
			}
		}
		return true;
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}
}
