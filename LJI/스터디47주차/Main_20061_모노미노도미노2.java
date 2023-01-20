import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061_모노미노도미노2 {

	//사실상 각 보드판 따로 둬도 될 것 같다?
	static int N;
	static boolean [][] blue;
	static boolean [][] green;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		answer=0;
		blue=new boolean[4][6];
		green=new boolean[6][4];
		StringTokenizer st;
		for (int T = 0; T < N ; T++) {
			st=new StringTokenizer(br.readLine());
			//txy 입력 받기
			int t=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			//블록 쌓기
				//블루//행 고정
			SetBlueBlock(t,x);
				//그린//열 고정
			SetGreenBlock(t,y);
			
			
			//블록 처리
				//블루
			CheckBlueBlock();
				//그린
			CheckGreenBlock();
		}
		
		System.out.println(answer);
		//칸의 개수 출력
		int cnt=CntBlock();
		System.out.println(cnt);
	}
	private static int CntBlock() {
		int cnt=0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i+2][j])cnt++;
				if(blue[i][j+2])cnt++;
			}
		}
		
		return cnt;
	}
	private static void CheckGreenBlock() {
		//점수 행 계산
		int x=5;//5부터 시작해서 역순으로 검사//2열 까지
		while(x>=2) {
			if(checkX(x)) {//true면 점수 열이라는 소리
				answer++;
				removeX(x);
			}else {//점수열이 아니다
				x--;
			}
		}
		
		//0,1행의 있는지 계산
		int removeLine=0;
		for (int i = 0; i < 4; i++) {
			if(green[1][i]) {
				removeLine=1;
				if(green[0][i]) {
					removeLine=2;
					break;
				}
			}
		}
		
		if(removeLine==1) {
			removeX(5);
		}else if(removeLine==2) {
			removeX(4);
			removeX(5);
		}
	}
	private static void removeX(int x) {//x열 지우고 위에 열들 끌어오기
		for (int i = 0; i < 4; i++) {
			for (int j = x; j > 0; j--) {
				green[j][i]=green[j-1][i];
				
			}
			green[0][i]=false;
		}
	}
	private static boolean checkX(int x) {
		for (int i = 0; i < 4; i++) {
			if(!green[x][i])return false;
		}
		return true;
	}
	private static void CheckBlueBlock() {
		// 점수 열 계산
		int y=5;//5부터 시작해서 역순으로 검사//2열 까지
		while(y>=2) {
			if(checkY(y)) {//true면 점수 열이라는 소리
				answer++;
				removeY(y);
			}else {//점수열이 아니다
				y--;
			}
		}
		
		//0,1열의 있는지 계산
		int removeLine=0;
		for (int i = 0; i < 4; i++) {
			if(blue[i][1]) {
				removeLine=1;
				if(blue[i][0]) {
					removeLine=2;
					break;
				}
			}
		}
		
		if(removeLine==1) {
			removeY(5);
		}else if(removeLine==2) {
			removeY(4);
			removeY(5);
		}
	}
	private static void removeY(int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = y; j > 0; j--) {
				blue[i][j]=blue[i][j-1];
				
			}
			blue[i][0]=false;
		}
	}
	private static boolean checkY(int y) {
		for (int i = 0; i < 4; i++) {
			if(!blue[i][y])return false;
		}
		return true;
	}
	
	private static void SetGreenBlock(int t, int y) {
		//t==2일 때 오른쪽까지 검사
		int x=0;
		while(true) {
			if(x+1>5) break;//밖으로 나가는 경우
			
			if(t==2) {
				if(!green[x+1][y] && !green[x+1][y+1]) x++;
				else break;
			}else {
				if(!green[x+1][y]) x++;
				else break;
			}
		}
		
		//배치
		if(t==1) {
			green[x][y]=true;
		}else if(t==2) {
			green[x][y]=true;
			green[x][y+1]=true;
		}else {
			green[x][y]=true;
			green[x-1][y]=true;
		}
	}
	private static void SetBlueBlock(int t, int x) {
		//t==3일 때 아래쪽까지 검사
		int y=0;
		
		while(true) {
			if(y+1>5) break;//밖으로 나가는 경우
			
			if(t==3) {
				if(!blue[x][y+1] && !blue[x+1][y+1]) y++;
				else break;
			}else {
				if(!blue[x][y+1]) y++;
				else break;
			}
		}
		
		//배치
		if(t==1) {
			blue[x][y]=true;
		}else if(t==2) {
			blue[x][y]=true;
			blue[x][y-1]=true;
		}else {
			blue[x][y]=true;
			blue[x+1][y]=true;
		}

	}

}
