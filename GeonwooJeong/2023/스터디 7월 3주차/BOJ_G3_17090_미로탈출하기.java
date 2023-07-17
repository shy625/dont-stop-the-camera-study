import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_17090_미로탈출하기 {
	static int N, M, area, ans;
	static int [][] map, v;
	static boolean [] able;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 맵 정보를 저장하기 위한 배열
		map = new int[N][M];
		// 이미 방문한 적이 있는지 확인할 배열
		v = new int[N][M];
		// 방문했던 배열의 출구가 있는지를 저장할 배열
		able = new boolean[N*M+1];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				// 이동하는 과정에서 편하게 하기 위해 int 배열로 변환
				char c = str.charAt(j);
				if(c == 'U') map[i][j] = 0;
				else if(c == 'R') map[i][j] = 1;
				else if(c == 'D') map[i][j] = 2;
				else if(c == 'L') map[i][j] = 3;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 이미 방문한 적이 있다면 넘긴다.
				if(v[i][j] != 0) continue;
				// 구역을 나누기 위한 변수
				area++;
				dfs(i, j, 0);
			}
		}
		
		System.out.println(ans);
		
	}

	private static void dfs(int r, int c, int cnt) {
		// r,c가 맵 밖이라면 해당 구역을 탈출 가능하다고 처리하고, 이동한 만큼 ans에 더해준다.
		if(!check(r, c)) {
			able[area] = true;
			ans += cnt;
			return;
		// r,c가 이미 방문한 적이 있다면
		} else if(v[r][c] != 0) {
			// r,c가 탈출 가능하다면 현재 있는 구역도 탈출 가능 처리를 해주고, 이동한 만큼 ans에 더해준다. 
			if(able[v[r][c]]) {
				able[area] = true;
				ans += cnt;
			}
			// r,c가 탈출 불가능하다면 ans에 더해주지 않고 종료시킨다.
			return;
		}
		
		// dfs를 진행하면서 움직인 곳을 모두 같은 구역으로 정해준다.
		v[r][c] = area;
		int nr = r+dr[map[r][c]];
		int nc = c+dc[map[r][c]];

		dfs(nr, nc, cnt+1);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
