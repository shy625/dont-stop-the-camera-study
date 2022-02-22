import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토_백준 {

	static int N,M;
	static int [][] map;
	static int cnt;//처음부터 토마토 다 익은 경우 대비
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		cnt=0;//안익은 토마토 갯수 세기
		Queue<int[]> que=new LinkedList<int[]>();//익은 토마토 배열 담음
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp=Integer.parseInt(st.nextToken());
				map[i][j]=temp;
				if(temp==1) {//익은 토마토
					que.offer(new int[] {i,j});
				}
				if(temp==0)cnt++;
			}
		}
		int day=0;//날짜 세기
		while(!que.isEmpty()) {//que가 비었다면 더 이상 익힐 수가 없다
			if(cnt==0)break;//cnt==0이면 토마토가 다 익었다
			int size=que.size();
			for (int i = 0; i < size; i++) {//이 사이클이 하루동안의 일
				int [] rc=que.poll();
				for (int d = 0; d < 4; d++) {
					int nr=rc[0]+dr[d];
					int nc=rc[1]+dc[d];
					if(!check(nr,nc)) continue;
					if(map[nr][nc]==0) {
						map[nr][nc]=1;//0인 토마토는 익어서 1로 변환
						que.offer(new int[] {nr,nc});
						cnt--;
					}
				}
			}
			day++;
		}
		System.out.println(cnt==0?day:-1);//모든 토마토가 익었으면 day출력 아니면 -1
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}

}
