import java.util.Arrays;
import java.util.Scanner;

public class Main_2580_스도쿠_백준 {

	static int[][] map;
	static int N;
	static int[][] pointer;
	public static void main(String[] args) {
		Scanner scann= new Scanner(System.in);
		map=new int[9][9];
		N=0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int temp=scann.nextInt();
				map[i][j]=temp;
				if(temp==0) N++; //0 위치 갯수 알기
			}
		}
		pointer=new int[N][2];
		int cnt=0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j]==0) {
					pointer[cnt][0]=i;
					pointer[cnt][1]=j;
					cnt++;
				}
			}
		}

		sudoku(0);
	}
	private static boolean sudoku(int cnt) {
		if(cnt==N) {
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i <9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			return true;
		}
		int r=pointer[cnt][0];
		int c=pointer[cnt][1];
		for (int i = 1; i <=9; i++) {
			map[r][c]=i;
			if(check(r,c)) {
				if(sudoku(cnt+1)) return true;//check 되면 다음 진행
			}
			map[r][c]=0;//생각엔 차피 1~9중 하나가 무조건 들어가고 반복문에서 i를 넣기에 0으로 안바꿔도 될 것 같았는데 잘은 모르겠지만 0으로 되돌리질 않으면 작동을 하지 않는다
			//아마 신비한 재귀 떄문인것 같다...
		}
		return false;
	}
	
	private static boolean check(int r, int c) {
		boolean [] col=new boolean[10]; //1~9까지
		boolean [] row=new boolean[10];
		boolean [] square=new boolean[10];
		//가로 체크
		for (int i = 0; i < 9; i++) {
			if(map[r][i]==0) continue;//0일경우는 계산 제외
			if(row[map[r][i]]) return false;
			row[map[r][i]]=true;
		}
		//세로 체크
		for (int i = 0; i < 9; i++) {
			if(map[i][c]==0) continue;//0일경우는 계산 제외
			if(col[map[i][c]]) return false;
			col[map[i][c]]=true;
		}
		//네모 구역 체크
		int sr=(r/3)*3;//시작할 위치 sr*3에서 3칸씩 탐색하면 된다
		int sc=(c/3)*3;
		for (int i = sr; i < sr+3; i++) {
			for (int j = sc; j < sc+3; j++) {
				if(map[i][j]==0) continue;//0일경우는 계산 제외
				if(square[map[i][j]]) return false;
				square[map[i][j]]=true;
			}
		}
		
		return true;
	}

}
