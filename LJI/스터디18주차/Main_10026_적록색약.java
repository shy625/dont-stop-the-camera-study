import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_10026_적록색약 {

	static int N;
	static char[][] map;
	static int cnt=0, bcnt=0;
	static int []dr= {-1,0,1,0};
	static int []dc= {0,1,0,-1};
	static boolean [][] visited;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		map=new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i]=scann.next().toCharArray();
		}
		visited=new boolean[N][N];
		cnt=bfs();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j]=false;
				if(map[i][j]=='G') map[i][j]='R';
			}
		}
		bcnt=bfs();
		System.out.println(cnt+" "+bcnt);
	}
	private static int bfs() {
		int count=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				char ch=map[i][j];
				count++;
				Queue<int[]> que=new LinkedList<>();
				que.offer(new int[] {i,j});
				while(!que.isEmpty()) {
					int [] rc=que.poll();
					int r=rc[0];
					int c=rc[1];
					for (int d = 0; d < 4; d++) {
						int nr=r+dr[d];
						int nc=c+dc[d];
						if(!check(nr,nc)) continue;
						
						if(!visited[nr][nc]&&map[nr][nc]==ch) {
							visited[nr][nc]=true;
							que.offer(new int[] {nr,nc});
						}
					}
				}
			}
		}
		return count;
	}
	
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}
}
