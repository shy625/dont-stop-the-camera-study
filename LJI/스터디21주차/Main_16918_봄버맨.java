import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16918_봄버맨 {

	static int R,C,N;
	static int [][] map;//폭탄을 시간으로 설치 1이되면 폭발
	static int timer;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		map=new int[R][C];
		
		for (int i = 0; i < R; i++) {
			char[] ch=br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if(ch[j]=='.') map[i][j]=0;//빈 곳
				else map[i][j]=4;//폭탄 설치
			}
		}
		
		//타이머 세팅
		timer=0;//0초
		
		//1초:아무것도 안하기
		timer++;
		timePass();
		if(timer==N) {
			showMap();
			return;
		}
		//2초부터 3,4 반복 map에서 0이되는 곳은 4로 채우고 1이되는 곳은 폭발
		while(timer!=N) {
			timer++;
			timePass();
			setBoom();
			boom();
		}
		
		showMap();
	}
	
	//자기 자리 포함
	static int dr[]= {0,-1,0,1,0};
	static int dc[]= {0,0,1,0,-1};
	//폭탄 폭발
	private static void boom() {
		boolean v[][]=new boolean [R][C];//이미 터진 곳은 또 갈 필요 없기에 표시해놓자
		
		Queue<int[]> que=new LinkedList<int[]>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]==1) que.offer(new int[]{i,j});//이번턴에 터져야되는 폭탄 리스트 que에 넣기
			}
		}
		
		while(!que.isEmpty()) {
			int[] cur=que.poll();//1 폭탄 존재 위치
			int r=cur[0];
			int c=cur[1];
			
			for (int i = 0; i < 5; i++) {
				int nr=r+dr[i];
				int nc=c+dc[i];
				
				if(!check(nr,nc)) continue;
				if(v[nr][nc]) continue;
				
				v[nr][nc]=true;
				map[nr][nc]=0;
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 &&r<R && c>=0 &&c<C;
	}

	//빈 곳에 폭탄 설치
	private static void setBoom() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]==0) map[i][j]=4;
			}
		}
	}

	//맵을 폭탄과 빈칸 형태로 출력하는 메소드
	private static void showMap() {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0) sb.append('O');
				else sb.append('.');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
	//폭탄 타이머 감소 시키는 메소드
	private static void timePass() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0) map[i][j]--;
			}
		}
	}
}
