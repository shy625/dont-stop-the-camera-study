import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16137_견우와직녀 {

	static int N,M;
	static int [][] map;
	static int [][][] time;//각 위치별 최소 시간 기록
	static boolean [][][] v;//위치별 도달 여부 체크
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		
		map=new int[N][N];
		time=new int[N][N][2];
		v=new boolean[N][N][2];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				Arrays.fill(time[i][j], 10000);
				map[i][j]=scann.nextInt();
				
			}
		}
		
		//오작교 설치 못하는 곳은 -1 넣어보자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if(map[i][j]==0) {
					boolean row=true;
					boolean col=true;
					
					for (int d = 0; d < 4; d++) {
						int nr=i+dr[d];
						int nc=j+dc[d];
						
						if(!check(nr,nc)) continue;
						
						if(d%2==0) {//세로
							if(map[nr][nc]!=1)
								col=false;
						}else {//가로
							if(map[nr][nc]!=1)
							row=false;
						}
					}
					if(!row && !col) map[i][j]= -1;
				}
			}
		}
		
		//실제 동작 구현
		
		//처음 위치 0,0 초기화
		time[0][0][0]=0;//0,0 의 위치에서 0번의 오작교 강제 설치 후 0초
		Queue<int[]> que=new LinkedList<int[]>();
		que.offer(new int[] {0,0,0,0});
		
		//시작
		while(!que.isEmpty()) {
			//현재 위치 꺼내기
			int[] cur=que.poll();
			int r=cur[0];
			int c=cur[1];
			int t=cur[2];
			int o=cur[3];//오작교 건설 회수
			
			//모든 경우에서 초가 기존보다 늘어났다면 갈 필요 없다
			//사방 이동
			//먼저 땅일때 이동
			//0인 절벽일 시 현재가 0번 건설했는지 확인 후 가능하다면 절벽 짓고 이동 //이때 시간초 나머지가 있다면(((t+1)/M)+1)*M 없다면 그대로 t+1 
			//1 초과 숫자일 시 해당 숫자 r로 위의 식 사용 //회수는 늘어나지 않음
			/////두번 연속 건너지 않음//현재가 1이 아니면 오작교 시도x
			for (int d = 0; d < 4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr, nc))continue;
				
				if(map[nr][nc]==1) {
					if(t+1<time[nr][nc][o]) {
						time[nr][nc][o]=t+1;
						que.offer(new int[] {nr,nc,t+1,o});
					}
				}else if(map[nr][nc]==0) {//오작교 설치가 가능한 절벽
					if(map[r][c]!=1)continue;
					
					if(o!=0) continue; //설치 불가
					
					int nt=0;
					if((t+1)%M==0) {//시간 딱코 
						nt=t+1;
					}else {
						nt=(((t+1)/M)+1)*M;
					}
					
					//nt 초에 건설이 완료됨
					if(nt<time[nr][nc][1]) {
						time[nr][nc][1]=nt;
						que.offer(new int[] {nr,nc,nt,1});
					}
				}else if(map[nr][nc]>1) {
					if(map[r][c]!=1)continue;
					
					int m=map[nr][nc];
					
					int nt=0;
					if((t+1)%m==0) {//시간 딱코 
						nt=t+1;
					}else {
						nt=(((t+1)/m)+1)*m;
					}
					
					//nt 초에 건설이 완료됨
					if(nt<time[nr][nc][o]) {
						time[nr][nc][o]=nt;
						que.offer(new int[] {nr,nc,nt,o});
					}
				}
			}
		}
		
		
		//time[N-1][N-1] 에서 [0]이나[1] 중 더 짧은 시간 출력
		System.out.println(Math.min(time[N-1][N-1][0], time[N-1][N-1][1]));
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}

}
