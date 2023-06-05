import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16509_장군 {

	//왕과의 충돌 검사 해야할지도?
	
	static int startR,startC;
	static int endR,endC;
	static int answer;
	static boolean v[][];
	static int []dr= {-3,-3,-2,2,3,3,2,-2};
	static int []dc= {-2,2,3,3,2,-2,-3,-3};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		startR=scann.nextInt();
		startC=scann.nextInt();
		endR=scann.nextInt();
		endC=scann.nextInt();
		v=new boolean[10][9];
		
		int turn=0;
		Queue<int[]> que=new LinkedList<int[]>();
		que.offer(new int[] {startR,startC});
		v[startR][startC]=true;
		
		while(!que.isEmpty()) {
			int size=que.size();
			for (int i = 0; i < size; i++) {
				int[] cur=que.poll();
				
				if(cur[0]==endR&&cur[1]==endC) {//끝내는 조건
					System.out.println(turn);
					return;
				}
				
				for (int d = 0; d < 8; d++) {
					int nr=cur[0]+dr[d];
					int nc=cur[1]+dc[d];
					
					if(!check(nr,nc))continue;
					if(v[nr][nc])continue;
					
					//왕이 가는 길에 있는 경우 체크
					if(d<2) {
						if(cur[0]-1==endR&&cur[1]==endC) continue;
						
						if(d==0) {
							if(nr+1==endR&&nc+1==endC)continue;
						}else {
							if(nr+1==endR&&nc-1==endC)continue;
						}
					}else if(d<4) {
						if(cur[0]==endR&&cur[1]+1==endC) continue;
						if(d==2) {
							if(nr+1==endR&&nc-1==endC)continue;
						}else {
							if(nr-1==endR&&nc-1==endC)continue;
						}
					}else if(d<6) {
						if(cur[0]+1==endR&&cur[1]==endC) continue;
						if(d==4) {
							if(nr-1==endR&&nc+1==endC)continue;
						}else {
							if(nr-1==endR&&nc-1==endC)continue;
						}
					}else if(d<8) {
						if(cur[0]==endR&&cur[1]-1==endC) continue;
						if(d==6) {
							if(nr-1==endR&&nc+1==endC)continue;
						}else {
							if(nr+1==endR&&nc+1==endC)continue;
						}
					}
					
					v[nr][nc]=true;
					que.offer(new int[] {nr,nc});
				}
			}
			turn++;
		}
		System.out.println("-1");
	}
	private static boolean check(int r, int c) {
		
		return r>=0&&r<10&&c>=0&&c<9;
	}

}
