import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_3055_탈출 {

	static int R,C;
	static char [][] map;
	static boolean [][] go;//고슴도치가 이미 가봤던 곳 기록해서 제외
	//비버 굴 위치
	static int DR,DC;
	//고슴도치 첫 위치
	static int SR,SC;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		R=scann.nextInt();
		C=scann.nextInt();
		map=new char[R][C];
		go=new boolean[R][C];
		DR=0;DC=0;SR=0;SC=0;
		for (int i = 0; i < R; i++) {
			char [] ch=scann.next().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j]=ch[j];
				if(map[i][j]=='S') {
					map[i][j]='.';
					SR=i;SC=j;
				}else if(map[i][j]=='D') {
					DR=i;DC=j;
				}
			}
		}
		
		//
		int time=0;
		Queue<int[]> que=new LinkedList<int[]>();//고슴도치가 갈 수 있는 위치에 순서대로 [r,c,time]
		
		que.offer(new int[] {SR,SC,0});
		go[SR][SC]=true;
		
		//
		while(!que.isEmpty()) {
			floodFill();//물 번지기
			int size=que.size();
			time++;
			for (int i = 0; i < size; i++) {//고슴도치가 한턴동안 이동할 곳
				int [] cur=que.poll();
				int r=cur[0];
				int c=cur[1];
				int t=cur[2];
				//고슴도치 도착
				if(r==DR&&c==DC) {
					System.out.println(t);
					return;
				}
				
				//도착 못한 경우 다음 갈 곳 정함
				//이때 다음 갈곳 사방에 물이 있으면 안된다
				for (int d = 0; d < 4; d++) {
					int nr=r+dr[d];
					int nc=c+dc[d];
					if(!check(nr,nc))continue;
					//갈 곳이 빈 곳이 아니거나 이미 갔던 곳이면 넘기기
					if(!(map[nr][nc]=='.'||map[nr][nc]=='D')||go[nr][nc])continue;
					
					
					
					que.offer(new int[] {nr,nc,time});
					go[nr][nc]=true;
				}
				
			}
		}
		
		System.out.println("KAKTUS");
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<R&&c>=0&&c<C;
	}
	private static void floodFill() {
		boolean [][] v=new boolean[R][C];//물 번진거 중복 사용 방지
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]=='*' && !v[i][j]) {
					for (int d = 0; d < 4; d++) {
						int nr=i+dr[d];
						int nc=j+dc[d];
						if(!check(nr, nc))continue;
						if(map[nr][nc]=='.') {
							v[nr][nc]=true;
							map[nr][nc]='*';
						}
					}
				}
			}
		}
	}

}
