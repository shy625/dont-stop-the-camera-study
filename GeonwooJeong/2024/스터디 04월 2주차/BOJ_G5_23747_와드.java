import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_23747_와드 {
	static int R, C;
	static char [][] map;
	static boolean [][] v;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		v = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		
		String comm = br.readLine();
		
		for (int i = 0; i < comm.length(); i++) {
			char ch = comm.charAt(i);
			if(ch == 'W') {
				bfs(r, c);
				continue;
			}
			
			int d = -1;
			
			if(ch == 'U') d = 0;
			else if(ch == 'D') d = 1;
			else if(ch == 'L') d = 2;
			else if(ch == 'R') d = 3;
			
			r = r+dr[d];
			c = c+dc[d];
		}
		
		v[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr, nc)) continue;
			
			v[nr][nc] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(v[i][j]) sb.append('.');
				else sb.append('#');
			}
			sb.append('\n');
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void bfs(int r, int c) {
		char ch = map[r][c];
		Queue<int []> q = new ArrayDeque<>();
		v[r][c] = true;
		q.add(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int curR = cur[0];
			int curC = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = curR+dr[d];
				int nc = curC+dc[d];
				
				if(!check(nr, nc) || v[nr][nc]) continue;
				
				if(map[nr][nc] != ch) continue;
				
				v[nr][nc] = true;
				q.add(new int[] {nr, nc});
				
			}
			
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}
