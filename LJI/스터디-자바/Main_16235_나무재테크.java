import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_16235_나무재테크 {

	//k년 동안 봄 여름 가을 겨울을 반복한다
	//나무 배열은 pq로 나이 어린 순으로 관리
	//봄여름가을겨울은 함수로 빼보자
	static class Tree implements Comparable<Tree>{
		int r,c;
		int age;
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {//나이 어린 나무부터만 관리해주면 될 것 같다
			return Integer.compare(this.age, o.age);
		}
		
	}
	
	static int N,M,K;
	static int A[][];//매년 겨울에 들어갈 양분
	static int map[][];//실제 현재의 양분양 저장//맨 처음은 5로 초기화
	static LinkedList<Tree> treeList;//현재 나무 목록 관리
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//입력 다 받기
		
		//N,M,K 입력
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		//맵 정보 초기화 밑 양분 맵 받기
		//맵크기 +1해서 정보랑 맞춰주자
		A=new int[N+1][N+1];
		map=new int[N+1][N+1];
		for (int i = 1; i <=N; i++) {
			Arrays.fill(map[i], 5);
		}
		for (int i = 1; i <= N; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 1; j <=N; j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//현재 존재하는 나무들 입력받기
		treeList=new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			treeList.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(treeList);
		
		//K년 시뮬
		ArrayList<Tree> newTreeList=new ArrayList<>();
		int deadMap[][]=new int[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			//int size=treeList.size();
			newTreeList.clear();
			
			for(Iterator<Tree> it = treeList.iterator(); it.hasNext();) {
				Tree cur=it.next();
				int r=cur.r;
				int c=cur.c;
				int age=cur.age;
				
				//봄
				if(age<=map[r][c]) {//나이먹기 가능
					map[r][c]-=cur.age++;
					
					if(cur.age%5==0) {//번식//가을
						for (int d = 0; d < 8; d++) {
							int nr=r+dr[d];
							int nc=c+dc[d];
							if(!check(nr,nc)) continue;
							
							newTreeList.add(new Tree(nr, nc, 1));
						}
					}
				}else {//나이먹기 불가
					deadMap[r][c]+=age/2;
					it.remove();
					//size--;
				}
				
			}
			
			treeList.addAll(0,newTreeList);
			
			//여름,겨울
			for (int r =1; r <=N; r++) {
				for (int c = 1; c <=N; c++) {
					map[r][c] += A[r][c]+deadMap[r][c];
					deadMap[r][c]=0;
				}
			}
		}
		
		//나무 갯수 출력
		System.out.println(treeList.size());
		
	}
	
	//양분 더하기
	private static void winter() {
		for (int i =1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				map[i][j]+=A[i][j];
			}
		}
	}

	//팔방 탐색
	static int dr[]= {-1,-1,0,1,1,1,0,-1};
	static int dc[]= {0,1,1,1,0,-1,-1,-1};
	private static void fall() {
		ArrayList<int[]> newTreeList=new ArrayList<>();
		//번식 가능한 나무가 있을 때 좌표 받기
		for (Tree cur: treeList) {
			if(cur.age%5==0) {//번식 가능
				newTreeList.add(new int[] {cur.r,cur.c});
			}
		}
		
		//좌표를 가지고 나무 번식
		for (int[] cur:newTreeList) {
			int r=cur[0];
			int c=cur[1];
			for (int d = 0; d < 8; d++) {
				int nr=r+dr[d];
				int nc=c+dc[d];
				if(!check(nr,nc)) continue;
				
				treeList.add(0, new Tree(nr, nc, 1));
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=1&&r<=N&&c>=1&&c<=N;
	}
	
	private static void summer(int[][] deadMap) {
		for (int i = 1; i <=N; i++) {
			for (int j = 1; j <=N; j++) {
				map[i][j]+=deadMap[i][j];
			}
		}
	}
	
	private static int[][] spring() {
		int[][] deadMap=new int[N+1][N+1];
		
		int index=0;
		int size=treeList.size();
		for (int i = 0; i < size; i++) {
			Tree cur=treeList.get(index);
			if(cur.age > map[cur.r][cur.c]) {//양분을 못먹고 죽을 나무
				deadMap[cur.r][cur.c]+=cur.age/2;
				treeList.remove(index--);
			}else {//양분을 먹을 수 있다
				map[cur.r][cur.c]-= cur.age++;//나무의 age 만큼 깎고 나무 나이+1
			}
			index++;
		}
		return deadMap;
	}

}
