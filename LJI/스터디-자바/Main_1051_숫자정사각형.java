import java.util.Scanner;

public class Main_1051_숫자정사각형 {

	static int N,M;
	static int answer;
	static int [][] map;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		
		map=new int[N][M];
		answer=1;//숫자하나가 최소한의 사각형의 넓이를 성립시킨다
		
		for (int i = 0; i < N; i++) {
			String str=scann.next();
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(str.substring(j,j+1));
			}
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < M-1; j++) {
				
				int num=map[i][j];//현재 좌표 수
				for (int k = j+1; k < M; k++) {
					if(!check(i,k))continue;
					if(map[i][k]!=num) continue;
					
					int dis=k-j;//한변의 길이
					
					boolean square=true;
					
					//왼쪽 아래 점
					int nr=i+dis;
					int nc=j;
					if(!check(nr,nc))continue;
					if(map[nr][nc]!=num)continue;
					//오른쪽 아래점
					nr=i+dis;
					nc=j+dis;
					if(!check(nr,nc))continue;
					if(map[nr][nc]!=num)continue;
					
					//정사각형 완성
					answer=Math.max(answer, dis+1);//2칸을 3길이로 보기에 +1
				}
			}
		}
		System.out.println(answer*answer);
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}

}
