package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Pair{
	Integer x;
	Integer y;
	public Pair(Integer x, Integer y) {
		super();
		this.x =x;
		this.y =y;
	}
	public Integer getX() {
		return x;
	}
	public Integer getY() {
		return y;
	}
}
public class bj_2580_스도쿠 {
	static int[][] map;
	static ArrayList<Pair> zero; //빈칸 좌표 담을 리스트
	public static void main(String[] args) throws IOException {
		map = new int[9][9]; //스도쿠 숫자담는 배열
		zero = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<9;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j]==0) {
					zero.add(new Pair(i,j));//0인경우 빈칸좌표담는 리스트에 추가
				}
			}
		}//input
		dfs(0);
	}//main
	private static void dfs(int cnt) {
		if(cnt==zero.size()) { //빈칸을 모두 채움
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.exit(0);//찾은경우 더 찾을필요 없으므로 시스템 강제종료
		}
		int x = zero.get(cnt).getX();//빈칸좌표중 앞에서부터 하나씩 x,y좌표 가져옴
		int y = zero.get(cnt).getY(); 
		for(int num=1;num<10;num++) { // 빈칸에 1부터 9까지 넣어보며 가능한지 check
			if(rowcheck(x,num) && colcheck(y,num) && check(x,y,num)) {
				map[x][y] = num;
				dfs(cnt+1);
				map[x][y] = 0;
			}
		}		
	}
	private static boolean rowcheck(int x, int num) { //행check
		for(int i=0;i<9;i++) {
			if (map[x][i]==num) { //x번쨰 행에 num과 같은 숫자가 있는경우 return false
				return false;
			}
		}
		return true; //없으면 return true
	}
	private static boolean colcheck(int y, int num) {//열check
		for(int i=0;i<9;i++) {
			if(map[i][y] == num) {
				return false;
			}
		}
		return true;
	}
	private static boolean check(int x, int y, int num) { //3*3 박스 check
		x = (x/3)*3; //index가 0,1,2 인 경우 0으로 3,4,5인 경우 3으로 만드는방법
		y = (y/3)*3;
		for(int i=x;i<x+3;i++) {
			for(int j=y;j<y+3;j++) {
				if (map[i][j] == num) return false; //3*3박스에 num과 같은수가 있으면 return false
			}
		}
		return true; //없으면 return true
	}
}//class
