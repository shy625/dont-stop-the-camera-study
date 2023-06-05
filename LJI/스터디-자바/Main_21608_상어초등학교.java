import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_21608_상어초등학교 {

	static int N;
	static int[][] map;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static Map<Integer, int[]> stuSeat;
	static int[] satis; //각 학생의 만족도를 담을 배열
	static ArrayList<ArrayList<Integer>> fList;//좋아하는 친구들을 담을 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);//맵을 -1로 초기화
		}
		int sNum=N*N;//학생수
		satis=new int[sNum];
		fList=new ArrayList<>();
		for (int i = 0; i < sNum; i++) {
			fList.add(new ArrayList<>());
		}
		
		stuSeat= new HashMap<Integer, int[]>();
		StringTokenizer st;
		for (int i = 0; i < sNum; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int stu=Integer.parseInt(st.nextToken())-1;
			for (int j = 0; j < 4; j++) {
				fList.get(stu).add(Integer.parseInt(st.nextToken())-1);
			}
			setSeat(stu);
		}
		
		
		int satisCnt=0;
		for (int i = 0; i < sNum; i++) {
			if(satis[i]==0) {
				
			}else if(satis[i]==1) {
				satisCnt+=1;
			}else if(satis[i]==2) {
				satisCnt+=10;
			}else if(satis[i]==3) {
				satisCnt+=100;
			}else if(satis[i]==4) {
				satisCnt+=1000;
			}
		}
		
		System.out.println(satisCnt);
	}
	
	private static void setSeat(int stu) {
		int r=-1,c=-1;
		int fNum=0;//주변에 좋아하는 친구의 수 //첫번째 조건
		int blankSpace=-1;//빈칸의 수 //두번째 조건
		
		for (int i = 0; i < 4; i++) {//먼저 좋아하는 친구 찾아본다
			if(!stuSeat.containsKey(fList.get(stu).get(i))) continue;//현재 친구가 아직 자리가 정해지지 않았다면 패스
			
			int [] rc=stuSeat.get(fList.get(stu).get(i));//자리가 정해진 친구의 좌표 가져오기
			for (int d = 0; d < 4; d++) {//친구가 있다
				int nr=rc[0]+dr[d];
				int nc=rc[1]+dc[d];
				if(!check(nr,nc)||(map[nr][nc]!=-1)) continue;//배열밖이면 안되고 빈자리여야한다
				//이제 nr,nc는 빈자리이다
				
				int nowBlank=0;
				int nowFriend=0;
				
				for (int d2 = 0; d2 < 4; d2++) {//빈자리 사방에 좋아하는 친구와 빈 자리 갯수 세기
					int nnr=nr+dr[d2];
					int nnc=nc+dc[d2];
					if(!check(nnr,nnc)) continue;
					
					if(map[nnr][nnc]==-1) {
						nowBlank++;
					}else if(map[nnr][nnc]==fList.get(stu).get(0)||
							map[nnr][nnc]==fList.get(stu).get(1)||
							map[nnr][nnc]==fList.get(stu).get(2)||
							map[nnr][nnc]==fList.get(stu).get(3)) {
						nowFriend++;
					}
				}
				
				if(fNum<nowFriend) {//현재 위치에 좋아하는 친구가 많다면 현재 위치로 교체
					fNum=nowFriend;
					blankSpace=nowBlank;
					r=nr;
					c=nc;
				}else if(fNum==nowFriend && blankSpace<nowBlank) {//좋아하는 친구 수는 같은데 빈자리가 더 많다면?
					blankSpace=nowBlank;
					r=nr;
					c=nc;
				}else if(fNum==nowFriend&& blankSpace==nowBlank) {//친구의수도 같고 빈자리의 갯수도 같다면 r과 c가 더 짧은 것
					if(r>nr) {
						r=nr;
						c=nc;
					}else if(r==nr&&c>nc) {
						c=nc;
					}
				}
			}
		}
		
		//좋아하는 친구가 없다면 빈자리 많은 자리 찾아야 한다
		if(fNum==0) {//자리를 찾지 못했다//map을 탐색하면서 가장 빈자리 많은 자리를 찾아야한다//blankSpace==-1일 것이다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]!=-1) continue;
					
					int nowBlank=0;
					for (int d = 0; d < 4; d++) {
						int nr=i+dr[d];
						int nc=j+dc[d];
						
						if(!check(nr,nc)) continue;
						if(map[nr][nc]==-1) nowBlank++;
					}
					if(blankSpace<nowBlank) {
						r=i;
						c=j;
						blankSpace=nowBlank;
					}
					if(blankSpace==4) break;//이미 최대 공간의 빈자리인 좌표를 얻었기에 끝내자
				}
				if(blankSpace==4) break;
			}
			
			//System.out.println(stu+" "+r+" "+c);
		}
		//좌표에 학생이랑 만족도 넣고 주변에 친구들한테도 +1
		//System.out.println(r+" "+c);
		map[r][c]=stu;
		stuSeat.put(stu, new int[] {r,c});
		satis[stu]+=fNum;
		
		for (int d = 0; d < 4; d++) {//인접한 친구
			int nr=r+dr[d];
			int nc=c+dc[d];
			
			if(!check(nr,nc)||map[nr][nc]==-1) continue;
			
			int friend=map[nr][nc];
			for (int i = 0; i <4; i++) {//친구의 좋아하는 사람 목록에 내가 있으면 친구의 만족도도 +1
				if(stu==fList.get(friend).get(i)) {
					satis[friend]++;
					break;
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0&&r<N&&c>=0&&c<N;
	}
}
