import java.util.ArrayList;
import java.util.Scanner;

public class Main_14620_꽃길 {

	//각 점마다 꽃이 피었을 때 가격을 list에 저장하고 리스트에서 3곳을 뽑았을 때 최솟값을 계산 
	// list 저장할 때 불가능한 위치면 -1값 넣자(공짜 땅 구분 위함)
	//단 , 꽃이 겹치는 경우를 조심해야할 것 
	//꽃 중심에서 맨해튼 거리가 2보다 작으면 무조건 겹칠 것
	static int N;
	static int [][] map;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static ArrayList<Integer> list;
	//list 위치 참조는 index/N=r index%N=c
	static int min;
	static int size;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		map=new int[N][N];
		size=N*N;
		list=new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		
		//각 점마다 렌탈 값 얻기 
		//외곽은 꽃잎이 나가서 죽기때문에 -1 값 넣어주자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//외곽 처리
				if(!(i!=0&&i!=N-1&&j!=0&&j!=N-1)) {
					list.add(-1);
					continue;
				}
				
				//내부 계산
				int sum=map[i][j];
				for (int d = 0; d < 4; d++) {
					int nr=i+dr[d];
					int nc=j+dc[d];
					sum+=map[nr][nc];
				}
				list.add(sum);
			}
		}
		
		
		min=Integer.MAX_VALUE;
		nCr(0,0,0,new int[3]);
		

		System.out.println(min);
	}
	
	
	private static void nCr(int cnt, int start, int sum, int[] select) {
		//최종 3개의 더한 값이 더 작으면 갱신
		if(cnt==3) {
			min=Math.min(min, sum);
			return;
		}
		
		for (int i = start; i < size ; i++) {
			//외곽 거르기
			if(list.get(i)==-1) continue;
			//좌표 얻기
			int r=i/N;
			int c=i%N;
			
			//전의 선택된 위치와 맨해튼 거리로 문제 없는지 파악
			boolean can=true;
			for (int j = 0; j < cnt; j++) {
				int pre=select[j];
				int preR=pre/N;
				int preC=pre%N;
				
				//맨해튼 거리가 2보다 짧으면 패스
				if(Math.abs(preR-r)+Math.abs(preC-c)<=2) {
					can=false;
					break;
				}
			}
			if(!can) continue;
			
			int nowSum=sum+list.get(i);
			if(nowSum>min) continue;
			//현재 위치 고르기
			select[cnt]=i;
			nCr(cnt+1,i+1,nowSum,select);
		}
	}

}
