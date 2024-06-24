
import java.util.Scanner;

public class Main_1890_점프 {

	static int N;
	static int[][] map;
	static int[] dr= {0,1};
	static int[] dc= {1,0};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		long [][] memo=new long[N][N];
		memo[0][0]=1;
		
		//오른쪽 아래로만 가므로 점프하는 곳에 이 루트로 가는 경우의 수를 더해준다 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==N-1&&j==N-1)break;
				int dis=map[i][j];
				for (int d = 0; d < 2; d++) {
					int nr=i+dr[d]*dis;
					int nc=j+dc[d]*dis;
					
					if(!check(nr, nc))continue;
					
					memo[nr][nc]+=memo[i][j];
				}
			}
		}
		
		System.out.println(memo[N-1][N-1]);
	}
	
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}

}
