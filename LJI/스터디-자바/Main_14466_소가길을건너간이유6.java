import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_14466_소가길을건너간이유6 {
	//길이 있다면 bfs로 연결되지 않은 블럭으로 치자
	static int N,K,R;
	static ArrayList<ArrayList<Integer>> roads;
	static int[][] map;
	static boolean[][] cow;
	static int [] dr=new int[] {-1,0,1,0};
	static int [] dc=new int[] {0,1,0,-1};
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		K=scann.nextInt();
		R=scann.nextInt();
		
		map=new int [N][N];
		cow=new boolean[N][N];
		roads=new ArrayList<>();
		for (int i = 0; i < N*N; i++) {
			roads.add(new ArrayList<>());
		}
		
		//길 계산
		for (int i = 0; i < R; i++) {
			int r=scann.nextInt()-1;
			int c=scann.nextInt()-1;
			int r2=scann.nextInt()-1;
			int c2=scann.nextInt()-1;
			
			int p1=r*N+c;
			int p2=r2*N+c2;
			
			roads.get(p1).add(p2);
			roads.get(p2).add(p1);
		}
		
		//소 배치
		for (int i = 0; i < K; i++) {
			int r=scann.nextInt()-1;
			int c=scann.nextInt()-1;
			cow[r][c]=true;
		}
		
		//bfs로 구역 나누기
		int cnt=0;//구역 계산할 용도
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]==0) {//bfs 가능 구역
					cnt++;
					bfs(i,j,cnt);
				}
			}
		}
		
		//구역별 소 갯수 세기
		int [] cows=new int[cnt+1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cow[i][j]) {
					cows[map[i][j]]++;
				}
			}
		}
		
		//정답 쌍 계산
		int answer=0;
		for (int i = 1; i < cnt; i++) {
			for (int j = i+1; j <= cnt; j++) {
				answer += cows[i]*cows[j];
			}
		}
		
		System.out.println(answer);
	}
	
	private static void bfs(int startR, int startC, int cnt) {
		Queue<int[]> que=new LinkedList<int[]>();
		map[startR][startC]=cnt;
		que.offer(new int[] {startR,startC});
		
		while(!que.isEmpty()) {
			int [] rc=que.poll();
			int num=rc[0]*N+rc[1];
			
			for (int d = 0; d < 4; d++) {
				int nr=rc[0]+dr[d];
				int nc=rc[1]+dc[d];
				
				if(!check(nr,nc))continue;
				if(map[nr][nc]!=0)continue;
				
				//길이 있으면 가지 말자
				int num2=nr*N+nc;
				boolean canGo=true;
				for(Integer near:roads.get(num)){
					if(near==num2) {
						canGo=false;
						break;
					}
				}
				if(!canGo) continue;
				
				//갈 수 있는 경우
				map[nr][nc]=cnt;
				que.offer(new int[] {nr,nc});
			}
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}

}
