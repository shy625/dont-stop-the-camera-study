import java.util.Scanner;

public class Main_15684_사다리조작 {

	static int N,M,H; //N:세로선,col | H:가로선,row
	static int [][]map;//사이 가로선이 연결된 것을 확인할 배열
	static int min,total;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		M=scann.nextInt();
		H=scann.nextInt();
		
		map=new int[H+1][N];//0자리 안씀
		
		for (int i = 0; i < M; i++) {
			int a=scann.nextInt();
			int b=scann.nextInt();
			map[a][b]=1;
		}
		total=N*(H+1);
		min=4;
		//사다리 깔기
		setLadder(0,N+1,0);//(1,1)부터 탐색 시작해야하기에 start는 map의 col+1부터 시작
		
		System.out.println(min==4?-1:min);
	}
	private static boolean setLadder(int cnt, int start, int ladderCnt) {//ladderCnt=0개로 통과하면 더 할 필요 없으므로true리턴
		if(cnt==3) {
			//조작 가능 여부 확인
			if(!check()) return false;
			
			//System.out.println(ladderCnt);
			if(ladderCnt==0) {//더 할 필요 없다 종료
				min=0;
				return true;
			}else {
				min=Math.min(min, ladderCnt);
				return false;
			}
		}
		
		//사다리 안깔기
		if(setLadder(cnt+1, start+1, ladderCnt)) return true;//현재 위치 못가는 곳으로 설정
		//사다리 깔기
		for (int i = start; i < total; i++) {
			int r=i/N;
			int c=i%N;
			
			if(c==0||r==0) continue;//못가는곳//왼쪽 끝
			if(map[r][c]==1) continue;//이미 설치됐으면 패스
			
			map[r][c]=1;
			if(setLadder(cnt+1, i+1, ladderCnt+1))return true;
			map[r][c]=0;
		}
		
		return false;
	}
	
	private static boolean check() {//i번째 세로줄이 i번째로 가면 true리턴
		for (int i = 1; i <= N; i++) {
			int r=1;
			int c=i;
			//사다리 타기 go
			
			while(r!=H+1) {
				//좌 우 탐색
				if(c-1!=0&& map[r][c-1]==1) {//좌측이 존재하고 갈 수 있다면?
					c--;
					r++;
					continue;
				}
				
				if(c<N && map[r][c]==1) {//우측이 존재하고 갈 수 있다면?
					c++;
					r++;
					continue;
				}
				
				//좌우 없으면 내려가기
				r++;
			}
			//검증
			if(c!=i) return false;
		}
		
		return true;
	}
}
