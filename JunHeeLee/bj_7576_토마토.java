package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_7576_토마토 {
	static int N,M;
	static int[][] map;
	static int check;
	static Queue<int[]> q;
	static int[] dx= {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		M = Integer.parseInt(s[0]);
		N = Integer.parseInt(s[1]);
		q = new LinkedList<>();
		map = new int[N][M];
		visit = new boolean[N][M];
		check = 0;
		for(int i=0;i<N;i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j]==1) {
					q.offer(new int[] {i,j}); //1인경우 익은토마토이므로 큐에 넣어준다
				}
				if(map[i][j]==0) {//0인경우가 없으면 0 프린트하고 끝내기
					check++;
				}
			}
		}//input 받기
		if(check==0) {//0인 좌표가 없으므로 0 출력후 프로그램 종료
			System.out.println(0);
			System.exit(0);
		}
		bfs();
	}//main
	private static void bfs() {
		int cnt = 0;//토마토가 다 익기까지 걸리는 시간 담는 변수
		while (!q.isEmpty()) {
			int qlen = q.size();
			while(qlen>0) { // q에들어있는 여러좌표중 n쨋날 들어온 좌표만 퍼뜨려야하므로 qlen변수 도입
				int[] point = q.poll();
				int x = point[0];
				int y = point[1];
				for(int i=0;i<4;i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(0<=nx && nx<N && 0<=ny && ny<M)
						if(visit[nx][ny]==false && map[nx][ny]==0) {
							visit[nx][ny] = true;
							map[nx][ny] = 1;
							q.offer(new int[] {nx,ny});
					}
				}
				qlen--;
			}
			cnt++;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if (map[i][j]==0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		System.out.println(cnt-1);
	}

}//class
