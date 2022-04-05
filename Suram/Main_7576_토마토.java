import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
	static int M;
	static int N; 
	static int[][] tomato;
	static ArrayList<P> in = new ArrayList<>();
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int days = 0;
	static Queue<P> queue = new LinkedList<>();
	
	static class P {
		int r;
		int c;
		
		public P (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if(tomato[i][j] == 1) {
					queue.offer(new P(i,j));
				}
			}
		} // 입력 처리
		
		bfs(); // bfs를 통해 진행한다
		findDays();
		System.out.println(days!=-1? days-1 : -1);
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			P cur = queue.poll();
			for(int d = 0 ; d < 4 ; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(!check(nr,nc)) continue; 
				if(tomato[nr][nc] == 0) {
					queue.offer(new P(nr,nc));
					tomato[nr][nc] = tomato[cur.r][cur.c]+1; 
					//현재 토마토보다 하루 더 걸리는 것을 표현하기 위해 1 더 큰 수를 저장해 준다. 
				}
			}
		}
	}
	
	public static boolean check(int r, int c) {
		return 0<= r && r < N && 0 <= c && c < M ;
	}
	
	public static void findDays() {
		int result = 0;
		for (int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < M ; j++) {
				if(result < tomato[i][j]) {
					result=tomato[i][j];
					// 가장 오래 걸린 토마토의 날짜를 찾아 준다.
				}
				if (tomato[i][j] == 0) {
					days= -1;
					return;
				}
			}
		}
		days=result;
	}
}
