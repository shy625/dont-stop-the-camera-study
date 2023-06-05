import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16932_모양만들기 {

	static int N,M;
	static int [][] map;
	static Map<Integer, Integer> areas;//각 모양마다 크기 기록할 hashMap 
	static int maxArea;
	static int []dr= {-1,0,1,0};
	static int []dc= {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		for (int i = 0; i <N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		////BFS로 각 모양마다 넘버링하고 넓이구하기
		areas=new HashMap<Integer, Integer>();
		int num=2;//0은 빈 칸 1은 모양이므로 2부터 시작
		maxArea=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==1) {//bfs로 넓이 계산하면서 num값으로 변경
					bfs(i,j,num++);
				}
			}
		}
		
		
		//0이 있는곳에서 사방을 찾아서 서로 다른 모양일시 더해주고 최대크기 갱신 시도
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) {
					makeShape(i,j);
				}
			}
		}
		
		//
		System.out.println(maxArea);
	}
	
	private static void makeShape(int r, int c) {
		int [] nearArea=new int[4];
		
		for (int d = 0; d <4; d++) {
			
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(!check(nr,nc)) continue;
			
			//같은 모양이면 굳이 포함시키지 말자
			boolean isSame=false;
			for (int i = 0; i < d; i++) {
				if(nearArea[i]==map[nr][nc]) {
					isSame=true;
					break;
				}
			}
			
			if(isSame)continue;
			nearArea[d]=map[nr][nc];
		}
		
		int area=1;
		for (int i = 0; i < 4; i++) {
			if(nearArea[i]!=0)
				area+=areas.get(nearArea[i]);
		}
		
		maxArea=Math.max(maxArea, area);
	}
	private static void bfs(int startR, int startC, int num) {
		Queue<int[]> que=new LinkedList<int[]>();
		
		int area=1;
		map[startR][startC]=num;
		que.offer(new int[] {startR,startC});
		
		while(!que.isEmpty()) {
			int[] cur=que.poll();
			int r=cur[0];
			int c=cur[1];
			
			//현재 위치에서 사방 탐색하여 1이 있다면 que에 넣기
			for (int d = 0; d <4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr,nc)) continue;
				
				if(map[nr][nc]==1) {
					map[nr][nc]=num;
					area++;
					que.offer(new int[] {nr,nc});
				}
			}
		}
		
		maxArea=Math.max(maxArea, area);
		areas.put(num, area);
	}
	private static boolean check(int r, int c) {
		return r>=0 &&r<N&&c>=0&&c<M;
	}
}
