import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕_백준 {

	static int R,C,T;
	static int [][]map;
	
	static int [] dr= {-1,0,1,0};
	static int [] dc= {0,1,0,-1};
	static int [] cleaner;//클리너
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		cleaner=new int[2];//클리너 r위치만 알면 된다
		map=new int[R][C];
		int cnt=0;//cleaner 위치 기록
		for (int i = 0; i < R; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) cleaner[cnt++]=i;
			}
		}
		
		for (int i = 0; i < T; i++) {
			spread();
			swip();
		}
		
		int total=2;//공기 청정기 값 -2
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				total+=map[i][j];
			}
		}

		System.out.println(total);
	}
	static int [] upDr= {0,-1,0,1};
	static int [] upDc= {1,0,-1,0};
	
	static int [] downDr= {0,1,0,-1};
	static int [] downDc= {1,0,-1,0};
	static void swip() {//que던 스택이던 차례로 넣고 돌리자
		int r,c;//시작 위치
		int preVal=0;
		int d=0;
		//위쪽 바람
		r=cleaner[0];
		c=1;
		preVal=0;
		d=0;
		while(map[r][c]!=-1) {
			int nr=r+upDr[d];
			int nc=c+upDc[d];
			if(!check(nr, nc)) {
				d= (d+1)%4;
				continue;
			}
			
			int temp=map[r][c];
			map[r][c]=preVal;
			preVal=temp;
			
			r=nr;
			c=nc;
		}
		//아래쪽 바람
		r=cleaner[1];
		c=1;
		preVal=0;
		d=0;
		while(map[r][c]!=-1) {
			int nr=r+downDr[d];
			int nc=c+downDc[d];
			if(!check(nr, nc)) {
				d= (d+1)%4;
				continue;
			}
			
			int temp=map[r][c];
			map[r][c]=preVal;
			preVal=temp;
			
			r=nr;
			c=nc;
		}
	}
	static void spread() {
		Queue<int[]> pointer=new LinkedList<>();//r,c,weight;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]>0) {//먼지가 퍼질 수 있다
					pointer.offer(new int[] {i,j,map[i][j]});
				}
			}
		}
		//
		int [][] newMap=new int[R][C];
		newMap[cleaner[0]][0]=-1;
		newMap[cleaner[1]][0]=-1;//공기청정기 위치 파악
		
		while(!pointer.isEmpty()) {
			int [] now=pointer.poll();
			int cnt=0;//몇방향 퍼지는지 계산
			int r=now[0];
			int c=now[1];
			int side=now[2]/5;//퍼지는 곳에 먼지
			for (int d = 0; d < 4; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr,nc)) continue;
				if(newMap[nr][nc]==-1) continue;
				cnt++;
				newMap[nr][nc]+=side;
			}
			newMap[r][c]+=(now[2]-(cnt*side));
		}
		map=newMap;
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<R&&c>=0&&c<C;
	}
}
