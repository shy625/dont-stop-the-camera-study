import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_22352_항체인식 {

	//이전 맵과 이후 맵을 비교하면서 
	//달라진 곳이 없으면 동일 값으로 업데이트 -> yes
	//달라진 곳이 있으면 그 위치부터 bfs하면서 그 위치들 체크하고 비교해야할 것
	static int N,M;
	static boolean answer;
	static int [][] map1;
	static int [][] map2;
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map1=new int [N][M];
		map2=new int [N][M];
		answer=true;
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map1[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map2[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//먼저 map1과 2의 다른 곳 찾기 ,없다면 그냥 YES
		boolean [][] v=new boolean[N][M];
		int changeNum=0;
		boolean find=false;//다른 부분을 찾았다면 종료
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map1[i][j] != map2[i][j]) {
					changeNum=map2[i][j];//바뀐 값 입력
					BFS(i,j,v);
					find=true;
					break;
				}
			}
			if(find)break;
		}
		
		//map2에서 v가 true인 곳은 changeNum이어야 하고 아닌 곳은 map1과 같아야함
		find=false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(v[i][j]) {//항체 적용된 곳
					if(map2[i][j]!=changeNum) {
						answer=false;
						find=true;
						break;
					}
				}else {
					if(map2[i][j]!=map1[i][j]) {
						answer=false;
						find=true;
						break;
					}
				}
			}
			if(find)break;
		}
		
		if(answer)System.out.println("YES");
		else System.out.println("NO");
	}
	private static void BFS(int startR, int startC, boolean[][] v) {
		int num=map1[startR][startC];
		Queue<int[]> que=new LinkedList<int[]>();
		v[startR][startC]=true;
		que.offer(new int[] {startR,startC});
		while(!que.isEmpty()) {
			int [] rc=que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr=rc[0]+dr[d];
				int nc=rc[1]+dc[d];
				if(!check(nr,nc))continue;
				if(map1[nr][nc]!=num)continue;
				if(v[nr][nc])continue;
				
				v[nr][nc]=true;
				que.offer(new int[] {nr,nc});
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<M;
	}

}
