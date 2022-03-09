import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_16234_인구이동 {

	static int N,L,R;
	static int map[][];
	static int group[][];
	static boolean visit[][];
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		L=scann.nextInt();
		R=scann.nextInt();

		map=new int[N][N];		
		group=new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		boolean isMove=true;
		int cnt=0;
		while(true) {//하루씩 체크
			isMove=false;//그룹이 하나라도 생성되면
			
			for (int i = 0; i < N; i++) {//먼저 -1로 그룹은 초기화
				Arrays.fill(group[i], -1);
			}
			ArrayList<Integer> people=new ArrayList<>();//그룹별로 사람 수 기록해놓기 위함
			people.add(0);//0번 그룹 비우기
			
			int groupNum=1;
			
			for (int i = 0; i <N; i++) {
				for (int j = 0; j < N; j++) {
					if(group[i][j]!=-1) continue;//이미 그룹이 정해졌다면 패스
					
					int sum=0;
					int groupCnt=0;//연합한 갯수 세기
					
					Queue<int []> que=new LinkedList<>();
					que.offer(new int[] {i,j});
					group[i][j]=groupNum;
					while(!que.isEmpty()) {
						int [] rc=que.poll();
						int r=rc[0];
						int c=rc[1];
						sum+=map[r][c];
						groupCnt++;
						
						for (int d = 0; d < 4; d++) {
							int nr=r+dr[d];
							int nc=c+dc[d];
							if(!check(nr,nc)) continue;
							
							int diff=Math.abs(map[r][c]-map[nr][nc]);
							if(diff>=L&&diff<=R) {//연합 가능 국가
								if(!isMove) isMove=true;
								group[nr][nc]=groupNum;
								que.offer(new int[] {nr,nc});
							}
						}
						if(i==r&&c==j&&que.isEmpty()) {
							group[i][j]=0;//연합을 못한 국가
							break;
						}
					}
					if(group[i][j]==0) continue;
					groupNum++;
					people.add(sum/groupCnt);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(group[i][j]!=0) {
						map[i][j]=people.get(group[i][j]);
					}
				}
			}
			if(!isMove) break;
			cnt++;
		}
		System.out.println(cnt);
	}
	
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N&&group[r][c]==-1;//그룹이 정해지지 않은 국가
	}
}
