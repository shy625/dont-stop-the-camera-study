import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_23031_에이쁠주세요 {

	static class Place {
		// 스위치 유무
		boolean s;
		boolean on;
		// 불빛 유무
		boolean l;
		public Place( boolean s,boolean on, boolean l) {
			super();
			this.s = s;
			this.on=on;
			this.l = l;
		}

	}
	static class Zombie{
		int x;
		int y;
		int dir;
		public Zombie(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		
	}
	
	static ArrayList<Zombie> zList;
	static int zCnt;
	// 아리 위치는 따로 구한다
	static int x, y;
	static int dir;

	static int N;
	static String A;
	static Place[][] map;

	static boolean stun;// 기절유무
	// 방향 위부터 시계방향으로 0,1,2,3 //그 뒤는 대각선(불빛에 사용)
	static int[] dr = { -1, 0, 1, 0, -1, 1, 1, -1 };
	static int[] dc = { 0, 1, 0, -1, 1, 1, -1, -1 };

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//아리는 무조건 0,0칸 아래방향
		x=0;y=0;
		dir=2;
		
		//좀비 리스트
		zList=new ArrayList<>();
		zCnt=0;
		//정보 받기
		N=Integer.parseInt(br.readLine());
		map=new Place[N][N];
		
		A=br.readLine();
		
		
		for (int i = 0; i <N; i++) {
			String temp=br.readLine();
			for (int j = 0; j < N; j++) {
				boolean switchExist=false;
				
				char info=temp.charAt(j);
				
				if(info=='O') {
					
				}else if(info=='S') {
					switchExist=true;
				}else if(info=='Z') {
					zList.add(new Zombie(i, j, 2));
					zCnt++;
				}
				
				map[i][j]=new Place(switchExist,false,false);
			}
		}
		
		//턴 작업 시작
		int strLen=A.length();
		for (int c = 0; c < strLen; c++) {
			char nowMove=A.charAt(c);
			
			//문자 따라 이동
			if(nowMove=='F') {
				int nr=x+dr[dir];
				int nc=y+dc[dir];
				
				if(check(nr,nc)) {
					x=nr;
					y=nc;
				}
			}else if(nowMove=='R') {
				dir=(dir+1)%4;
			}else if(nowMove=='L') {
				dir=(4+dir-1)%4;
			}
			
			//스위치 여부 확인, 있다면 불켜기
			if(map[x][y].s) {//스위치가 존재
				if(!map[x][y].on) {//스위치 킨 적이 없다?
					map[x][y].on=true;
					for (int d = 0; d < 8; d++) {
						int nr=x+dr[d];
						int nc=y+dc[d];
						if(check(nr, nc)) {
							map[nr][nc].l=true;
						}
					}
					map[x][y].l=true;
				}
			}
			//좀비들 이동
			for (Zombie zombie:zList) {
				int prer=zombie.x;
				int prec=zombie.y;
				int nr=zombie.x+dr[zombie.dir];
				int nc=zombie.y+dc[zombie.dir];
				if(check(nr, nc)) {
					zombie.x=nr;
					zombie.y=nc;
				}else {
					if(zombie.dir==2) {
						zombie.dir=0;
					}else {
						zombie.dir=2;
					}
				}
				
				if((prer==x && prec==y&& (!map[prer][prec].l)) ||( zombie.x==x && zombie.y==y && (!map[zombie.x][zombie.y].l))) {
					stun=true;
					break;
				}
			}
			
			//기절 여부 확인
			if(stun) {
				break;
			}
//			if(map[x][y].zom && (!map[x][y].l)) {
//				stun=true;
//				break;
//			}
		}
		//
		if(stun) {
			System.out.println("Aaaaaah!");
		}else {
			System.out.println("Phew...");
		}
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}

}
