import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소 {

	static int N,M;
	static int[][] map;
	static ArrayList<int[]> virus;
	static int safeArea;
	static int size;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		safeArea=0;
		size=N*M;//벽 세울떄 반복문에 사용
		virus=new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp=Integer.parseInt(st.nextToken());
				map[i][j]=temp;
				
				if(temp==0) {//
					safeArea++;
				}else if(temp==2) {
					virus.add(new int[] {i,j});
				}
			}
		}
		answer=0;
		safeArea-=3;//벽 3개 세웠기 때문에 3개 뺴주어야한다
		go(0,0);//cnt,start
		
		
		System.out.println(answer);
	}
	
	private static void go(int cnt, int start) {
		if(cnt==3) {
			int thisSafeArea=bfs();
			answer=Math.max(answer, thisSafeArea);
			return;
		}
		
		for (int i = start; i < size; i++) {
			int r=i/M;
			int c=i%M;
			if(map[r][c]==0) {
				map[r][c]=1;
				go(cnt+1, i+1);
				map[r][c]=0;
			}
		}
		
	}

	static int []dr= {-1,0,1,0};
	static int []dc= {0,1,0,-1};
	
	private static int bfs() {
		boolean [][] v=new boolean[N][M];
		int cnt=safeArea;
		Queue<int []> que=new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			int r=virus.get(i)[0];
			int c=virus.get(i)[1];
			que.offer(new int[] {r,c});
			v[r][c]=true;
		}
		
		while(!que.isEmpty()) {
			int[] cur=que.poll();
			int r=cur[0];
			int c=cur[1];
			//map[r][c]=2;
			if(map[r][c]==0) cnt--;//원래 빈방이었으면 안전구역-1;
			
			for (int d = 0; d < 4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr,nc))continue;
				
				if(!v[nr][nc] && map[nr][nc]==0) {
					v[nr][nc]=true;
					que.offer(new int[] {nr,nc});
				}
			}
		}
		
	/*	int cnt=0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) cnt++;
				else if(map[i][j]==2) map[i][j]=0;
			}
		}
		
		for (int i = 0; i < virus.size(); i++) {
			int r=virus.get(i)[0];
			int c=virus.get(i)[1];
			map[r][c]=2;
		}*/
		
		return cnt;
	}

	private static boolean check(int r, int c) {
		// TODO Auto-generated method stub
		return r>=0&&r<N&&c>=0&&c<M;
	}
}
