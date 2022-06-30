import java.util.Arrays;
import java.util.Scanner;

public class Main_1285_동전뒤집기 {

	// H=0 ,T=1
	// 행을 뒤집거나 안뒤집거나 두가지 경우에 대해 열에서 T의 갯수를 세 더 많으면 뒤집어라
	static int N;
	
	
	static int map[][];
	static int copyMap[][];
	static int min;
	static int half;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		half=N/2;
		map=new int[N][N];
		copyMap=new int[N][N];
		for (int i = 0; i < N; i++) {
			char [] ch=scann.next().toCharArray();
			for (int j = 0; j < N; j++) {
				if(ch[j]=='T') map[i][j]=1;
				else map[i][j]=0;
			}
		}

		//구하기
		min=Integer.MAX_VALUE;
		
		makeBit(0,0);//각 행을 뒤집을지 않뒤집을지 정하는 비트를 만드는 함수
		
		System.out.println(min);
	}
	
	
	private static void makeBit(int cnt,int bit) {
		if(cnt==N) {
			int total=0;
			//행을 비트에 따라 뒤집기
			//bit에서 1인 곳은 뒤집고 0인 곳은 그대로 냅두자
			
			//맵 복사 하면서 1이면 뒤집기
			for (int i = 0; i < N; i++) {
				//copyMap[i]=Arrays.copyOf(map[i], N); //복사하니까 메모리 초과난다
				for (int j = 0; j < N; j++) {
					copyMap[i][j]=map[i][j];
				}
				
				if((bit & 1<<i) == 1<<i) {//행 뒤집기
					for (int j = 0; j < N; j++) {
						copyMap[i][j]= copyMap[i][j] ^ 1;
					}
				}
			}
			
			//열 뒤집기 //열마다 T개수 세서 T가 half보다 많으면 뒤집은 갯수 세기
			for (int i = 0; i < N; i++) {
				int cntT=0;
				for (int j = 0; j < N; j++) {
					if(copyMap[j][i]==1)cntT++;
				}
				
				if(cntT>half) {
					total += N-cntT;
				}else
					total += cntT;
			}
			
			min= Math.min(min, total);
			
			
			return;
		}
		
		makeBit(cnt+1, bit+(1<<cnt));//비트 추가
		makeBit(cnt+1, bit);
		
	}


	private static int count() {
		int cnt=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==1)cnt++;
			}
		}
		return cnt;
	}
	
}
