import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20165_인내의도미노장인 {

	static int N, M, R;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] dominoH;
	static boolean[][] domino;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		dominoH = new int[N][M];
		domino = new boolean[N][M];// 도미노 엎어졌는지 기록

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				dominoH[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int point = 0;
		// round반복
		for (int r1 = 0; r1 < R; r1++) {
			// 공격
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			char dir = st.nextToken().charAt(0);
			point += Attack(r, c, dir);
			//System.out.println(r+" "+c+" "+dir);
			// 수비
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			domino[r][c]=false;
		}
		
		//출력
		StringBuilder sb=new StringBuilder();
		sb.append(point).append('\n');
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(domino[i][j]) sb.append('F').append(' ');
				else sb.append('S').append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

	private static int Attack(int startR, int startC, char dir) {
		int cnt = 0;
		//방향 배열 숫자로 변환
		int d = 0;
		switch (dir) {
		case 'E':
			d=1;
			break;
		case 'W':
			d=3;
			break;
		case 'S':
			d=2;
			break;
		case 'N':
			d=0;
			break;
		}
		
		int canGo=0;//앞으로 몇칸더 도미노가 덮을 수 있는지
		int r=startR;
		int c=startC;
		do {
			//현재 위치가 도미노가 서있는지 판단
			//서있다면 canGo와 도미노 높이 비교해서 더 높은값 갱신
			//canGo가 1 이상일 시 -1하고 현재 도미노 서있다면 cnt++;
			
			//도미노가 서있다면 무조건 한번 넘어뜨릴 수 있고 
			if(!domino[r][c]) {
				domino[r][c]=true;
				cnt++;
				canGo=Math.max(canGo, dominoH[r][c]);
			}
			
			//if(canGo==0) break;//갱신한 값이 0이면 더 할 필요 없다
			
			
			//위치 이동하고 canGo-1
			int nr=r+dr[d];
			int nc=c+dc[d];
			
			if(!check(nr,nc)) break;
			
			r=nr;
			c=nc;
			canGo--;
		}while(canGo>0);
		
		return cnt;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N &&c>=0 &&c<M;
	}

}
