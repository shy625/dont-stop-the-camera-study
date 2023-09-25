import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S2_16924_십자가찾기 {

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int level;
		
		public Point(int r, int c, int level) {
			this.r = r;
			this.c = c;
			this.level = level;
		}

		@Override
		public int compareTo(Point o) {
			if(this.r != o.r) {
				return Integer.compare(this.r, o.r);
			} else if(this.c != o.c) {
				return Integer.compare(this.c, o.c);
			} else {
				return Integer.compare(this.level, o.level);
			}
		}
	}
	
	static int N, M, max;
	static List<Point> list = new ArrayList<>();
	static char [][] map;
	static boolean [][] v;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		v = new boolean[N][M];
		// N과 M 중 더 작은 값을 저장할 변수
		max = Math.min(N, M);
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				char c = map[i][j];
				if(c == '*') {
					find(i, j);
				}
			}
		}
		
		boolean flag = true;
		outer :
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// *이지만 십자가에 포함되지 않은 경우(십자가를 채울 수 없는 경우)를 체크한다.
					if(map[i][j] == '*' && !v[i][j]) {
						flag = false;
						break outer;
					}
				}
			}
		
		if(!flag) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(list.size()+"\n");
			
			Collections.sort(list);
			
			for(Point p : list) {
				sb.append(p.r+" "+p.c+" "+p.level+"\n");
			}
			
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
		
	}

	private static void find(int r, int c) {
		// 십자가의 크기를 저장할 변수
		int level = 0;
		
		// 크기 1부터 max까지 r, c를 중심으로 십자가를 만들 수 있는지를 체크한다.
		for (int i = 1; i <= max; i++) {
			if(!checkCross(r, c, i)) break;
			level++;
		}
		
		// 크기 1 이상의 십자가를 만들 수 있다면 list에 집어넣는다.
		if(level > 0) {
			list.add(new Point(r+1, c+1, level));
		}
		
	}
	

	private static boolean checkCross(int r, int c, int level) {
		// r, c를 중심으로 4방향으로 십자가를 만들 수 있는지를 체크한다.
		for (int d = 0; d < 4; d++) {
			for (int i = 1; i <= level; i++) {
				int nr = r+dr[d]*i;
				int nc = c+dc[d]*i;
				// 맵 밖을 벗어나면 return false
				if(!check(nr, nc)) return false;
				// 십자가를 만들 수 없다면 return false
				if(map[nr][nc] == '.') return false;
			}
		}
		
		// 십자가를 만들 수 있는 경우에 한해 visit 처리를 해준다.
		v[r][c] = true;
		for (int d = 0; d < 4; d++) {
			for (int i = 1; i <= level; i++) {
				int nr = r+dr[d]*i;
				int nc = c+dc[d]*i;
				v[nr][nc] = true;
			}
		}
		return true;
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
