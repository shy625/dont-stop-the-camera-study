import java.util.Arrays;
import java.util.Scanner;

public class Main_17135_캐슬디펜스 {
	
	static int N,M,D;
	static int [][] map;
	static int maxEli;//최대 몇마리 제거했는지 비교
	static int [] arc;//궁수 좌표
	static int enemy;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		D=scann.nextInt();
		
		map=new int[N][M];
		
		enemy=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
				if(map[i][j]==1) enemy++;
			}
		}
		maxEli=-1;
		arc=new int[3];
		ncr(0,0);
		System.out.println(maxEli);
	}
	private static void ncr(int cnt,int start) {
		if(cnt==3) {
			int [][] copyMap=new int[N][M];
			for (int i = 0; i < N; i++) {
				copyMap[i]=map[i].clone();
			}
			play(enemy,copyMap);//궁수 좌표 정해졌으니 게임 시작
			return;
		}
		
		for (int i = start; i < M; i++) {
			arc[cnt]=i;
			ncr(cnt+1,i+1);
		}
	}
	private static void play(int enemy, int[][] map) {//복사된 enemy와 map사용해서 원본 손상 없게하라
		//궁수의 좌표는 N,arc[i]
		int eli=0;//화살로 제거한 적의 수
		while(enemy!=0) {//적의 수가 0이 될 때 까지 반복
			//궁수들이 각각 거리 계산해서 쏠 적 정하기
			int [][] enemyRC=new int [3][2];//각각 쏠 적의 xy 좌표 기록
			for (int i = 0; i < 3; i++) {
				Arrays.fill(enemyRC[i], -1);//배열값 -1로 초기화
			}
			int [] enemyDis=new int[] {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};//적과의 거리
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==0)continue;
					//적이 존재한다
					for (int a = 0; a < 3; a++) {//아쳐들과의 거리 측정
						int dis=Math.abs(N-i)+Math.abs(arc[a]-j);
						if(dis>D) continue;//사거리 밖이다 패스
						
						if(dis==enemyDis[a]) {//만약 더 왼쪽이면 바꿔야한다
							if(enemyRC[a][1]>j) {//더 왼쪽인 경우엔 좌표를 바꿔야한다
								enemyRC[a][0]=i;
								enemyRC[a][1]=j;
							}
						}else if(enemyDis[a]>=dis) {//노리는 적의 좌표를 바꿔야한다
							enemyDis[a]=dis;
							enemyRC[a][0]=i;
							enemyRC[a][1]=j;
						}
					}
				}
			}
			//3명의 궁수가 적을 쏘기 //enemyXY에 -1이면 쏠적이 없다.
			for (int i = 0; i < 3; i++) {
				if(enemyRC[i][0]==-1) continue;//쏠 적이 없는 상태
				
				int r=enemyRC[i][0];
				int c=enemyRC[i][1];
				if(map[r][c]==1) {//그전에 다른 궁수가 쏘지 않은 상태
					enemy--;
					eli++;
					map[r][c]=0;//자리의 적 제거
				}
			}
			
			//적들의 이동 //전부 밑으로 한칸씩 이동
			for (int j = 0; j < M; j++) {//행 별로 실행한다
				int preVal=0;//전값 기록
				for (int i = 0; i < N; i++) {
					int temp=map[i][j];
					map[i][j]=preVal;
					preVal=temp;
				}
				if(preVal==1) enemy--;//적이 성에 박고 죽었다
			}
		}
		
		//eli(적을 제거한) 수와 maxEli 비교
		maxEli=Math.max(maxEli, eli);
	}
}

