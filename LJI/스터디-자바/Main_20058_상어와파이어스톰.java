import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20058_상어와파이어스톰 {

	static int map[][];
	static int N,Q;
	static int line;
	static Queue<Integer> que; 
	static int sum;
	static int bigIce;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		
		line=(int) Math.pow(2, N);
		map=new int[line][line];
		for (int i = 0; i < line; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < line; j++) {
				int temp=Integer.parseInt(st.nextToken());
				map[i][j]=temp;
			}
		}
		
		//마법 순서 받기
		que=new LinkedList<Integer>();
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int temp=Integer.parseInt(st.nextToken());
			que.offer(temp);
		}
		
		//마법 실행
		for (int i = 0; i < Q; i++) {
			int L=que.poll();
			fireStorm(L);
			
		}
		//최종 결과 계산
		sum=0;
		bigIce=0;
		cntIce();
		System.out.println(sum);
		System.out.println(bigIce);
	}
	
	
	private static void cntIce() {
		boolean v[][]=new boolean[line][line];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < line; j++) {
				if(!v[i][j]&&map[i][j]>0) {//bfs
					Queue<int[]> q=new LinkedList<>();
					q.offer(new int[] {i,j});
					v[i][j]=true;
					int thisIce=0;
					while(!q.isEmpty()) {
						int [] cur=q.poll();
						int r=cur[0];
						int c=cur[1];
						int ice=map[r][c];
						sum+=ice;
						thisIce++;
						
						for (int d = 0; d < 4; d++) {
							int nr=r+dr[d];
							int nc=c+dc[d];
							if(!check(nr,nc))continue;
							if(v[nr][nc]||map[nr][nc]==0)continue;
							
							v[nr][nc]=true;
							q.offer(new int[] {nr,nc});
						}
					}
					bigIce=Math.max(bigIce, thisIce);
					
				}
			}
		}
		
	}


	private static void fireStorm(int L) {
		//L에 따른 격자 회전
		if(L!=0) {//0일땐 회전 불필요
			int size=(int) Math.pow(2, L);//격자 사이즈
			int spinSize=(int) Math.pow(2, N-L);//한 회전이 가져갈 사이즈
			
			for (int i = 0; i < spinSize; i++) {
				for (int j = 0; j < spinSize; j++) {
					int startR=i*size;
					int startC=j*size;
					Storm(startR,startC,size);
				}
			}
			
		}
		
		//얼음 녹이기
		boolean [][] melt=new boolean[line][line];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < line; j++) {
				int cnt=0;
				for (int d = 0; d < 4; d++) {
					int nr=i+dr[d];
					int nc=j+dc[d];
					if(!check(nr,nc))continue;
					if(map[nr][nc]>0)cnt++;
				}
				if(cnt<3)melt[i][j]=true;
			}
		}
		
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < line; j++) {
				if(melt[i][j]&&map[i][j]>0) {
					map[i][j]--;
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<line&&c>=0&&c<line;
	}

	static int dr[]= {0,1,0,-1};
	static int dc[]= {1,0,-1,0};
	private static void Storm(int startR, int startC, int size) {//startR,startC 부터 size 크기의 격자가 90도 회전
		//각칸마다 size만큼씩 오른쪽 아래 왼쪽 위로 이동?
		Queue<Integer> num=new LinkedList<>();
		for (int i = startR; i < startR+size; i++) {
			for (int j = startC; j < startC+size; j++) {
				num.offer(map[i][j]);
			}
		}
		
		//세로방향 끝부터 시작
		for (int j = startC+size-1; j >= startC; j--) {
			for (int i = startR; i < startR+size; i++) {
				map[i][j]=num.poll();
			}
		}
		
	}

}
