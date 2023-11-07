import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_7562_나이트의이동 {
	static int [][] map;
	static boolean [][] v;
	static int L, ans, er, ec;
	static int [] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int [] dc = {1, 2, 2, 1, -1, -2, -2, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			L = Integer.parseInt(br.readLine());
			map = new int[L][L];
			v = new boolean[L][L];
			ans = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			
			bfs(sr, sc);
			
			sb.append(ans+"\n");
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}

	private static void bfs(int sr, int sc) {
		Queue<int []> q = new LinkedList<>();
		v[sr][sc] = true;
		q.add(new int[] {sr, sc, 0});
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(r == er && c == ec) {
				ans = cur[2];
				return;
			}
			
			for (int d = 0; d < 8; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr, nc)) continue;
				
				if(!v[nr][nc]) {
					q.add(new int[] {nr, nc, cur[2]+1});
					v[nr][nc] = true;
				}
				
			}
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<L && c>=0 && c<L;
	}

}
