import java.util.LinkedList;
import java.util.Queue;

public class Solution_블록이동하기 {

	public static class Drone{
		int r;
		int c;
		boolean isR;
		public Drone(int r, int c, boolean isR) {
			super();
			this.r = r;
			this.c = c;
			this.isR = isR;
		}
		
	}
	//오른쪽으로 뻗어나가기 가능한가
	//아래로 뻗어나가기 가는한가
	//어디로 꺾어야하는가?
	//두칸 차지하는 곳에서 이동과 회전은 같은 조건
	static int n;
	public static int solution(int[][] board) {
        int answer = 0;
        int t=0;
        n=board.length;
        int N=n-1;
        //드론이 이미 방문했던 곳인지 판단여부
        boolean [][][] v=new boolean[n][n][2];
        //
        Queue<Drone> que=new LinkedList<Drone>();
        que.offer(new Drone(0,0,true));
        v[0][0][0]=true;
        while(!que.isEmpty()) {
        	int thisTime=que.size();
        	for (int i = 0; i < thisTime; i++) {
				Drone cur=que.poll();
				
				//가로드론
				if(cur.isR) {
					//도착 여부 판단
					if((cur.r==N) &&(cur.c==(N-1))) {
						answer=t;
						//System.out.println(cur.r+" "+cur.c);
						return answer;
					}
					
					//가로 드론이 갈 수 있는 곳 찾자
					//위쪽으로 이동이나 회전
					//맵 밖여부 확인
					int newR=cur.r-1;
					int newC=cur.c;
					if(check(newR,newC)) {
						//위쪽 둘다 0이다
						if(board[newR][newC]==0&&board[newR][newC+1]==0) {
							//왼쪽 축 회전
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
							//오른 축 회전
							if(!v[newR][newC+1][1]) {
								v[newR][newC+1][1]=true;
								que.offer(new Drone(newR,newC+1,false));
							}
							//위로 이동
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
						}
					}
					//아래쪽으로 이동이나 회전
					newR=cur.r+1;
					newC=cur.c;
					if(check(newR,newC)) {
						//아래쪽 둘다 0이다
						if(board[newR][newC]==0&&board[newR][newC+1]==0) {
							//왼쪽 축 회전
							newR--;
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
							//오른 축 회전
							if(!v[newR][newC+1][1]) {
								v[newR][newC+1][1]=true;
								que.offer(new Drone(newR,newC+1,false));
							}
							//아래로 이동
							newR++;
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
						}
					}
					//좌
					newR=cur.r;
					newC=cur.c-1;
					if(check(newR,newC)) {
						if(board[newR][newC]==0) {
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
						}
					}
					//우
					newR=cur.r;
					newC=cur.c+1;
					if(check(newR,newC+1)) {
						if(board[newR][newC+1]==0) {
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
						}
					}
				}else {//세로드론
					//도착 여부 판단
					if((cur.r==N-1) &&(cur.c==N)) {
						answer=t;
						//System.out.println(cur.r+" "+cur.c);
						return answer;
					}
					
					////////////
					//세로 드론이 갈 수 있는 곳 찾자
					//왼쪽으로 이동이나 회전
					//맵 밖여부 확인
					int newR=cur.r;
					int newC=cur.c-1;
					if(check(newR,newC)) {
						//왼쪽 둘다 0이다
						if(board[newR][newC]==0&&board[newR+1][newC]==0) {
							//위쪽 축 회전
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
							//아래 축 회전
							if(!v[newR+1][newC][0]) {
								v[newR+1][newC][0]=true;
								que.offer(new Drone(newR+1,newC,true));
							}
							//왼쪽로 이동
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
						}
					}
					//오른쪽으로 이동이나 회전
					newR=cur.r;
					newC=cur.c+1;
					if(check(newR,newC)) {
						//오른쪽 둘다 0이다
						if(board[newR][newC]==0&&board[newR+1][newC]==0) {
							//위쪽 축 회전
							newC--;
							if(!v[newR][newC][0]) {
								v[newR][newC][0]=true;
								que.offer(new Drone(newR,newC,true));
							}
							//아래 축 회전
							if(!v[newR+1][newC][0]) {
								v[newR+1][newC][0]=true;
								que.offer(new Drone(newR+1,newC,true));
							}
							//오른쪽으로 이동
							newC++;
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
						}
					}
					//위
					newR=cur.r-1;
					newC=cur.c;
					if(check(newR,newC)) {
						if(board[newR][newC]==0) {
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
						}
					}
					//아래
					newR=cur.r+1;
					newC=cur.c;
					if(check(newR+1,newC)) {
						if(board[newR+1][newC]==0) {
							if(!v[newR][newC][1]) {
								v[newR][newC][1]=true;
								que.offer(new Drone(newR,newC,false));
							}
						}
					}
					///////////
				}
			}
        	t++;
        }
        return answer;
    }

	private static boolean check(int r, int c) {
		return r>=0&&r<n&&c>=0&&c<n;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[][] {
			{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}
		}));
	}

}
