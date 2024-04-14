

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_8972_미친_아두이노 {
	// 아두이노의 index, 위치, 파괴 여부를 나타내는 클래스
	static class Arduino {
		int idx, r, c;
		boolean destroyed;
		public Arduino(int idx, int r, int c) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			destroyed = false;
		}
		public void move(int d) {
			this.r += dr[d];
			this.c += dc[d];
		}
		public void destroy() {
			destroyed = true;
		}
	}
	static Arduino player; // 종수의 아두이노
	static ArrayList<Arduino> others; // 미친 아두이노
	static int[][] map;
	static int R, C;
	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		others = new ArrayList<>();
		map = new int[R][C];
		others.add(new Arduino(0, -1, -1));
		for (int r = 0; r < R; r++) {
			String s = br.readLine();
			for (int c = 0; c < C; c++) {
				if (s.charAt(c) == 'I') {
					player = new Arduino(-1, r, c);
					map[r][c] = -1;
				} else if (s.charAt(c) == 'R') {
					others.add(new Arduino(others.size(), r, c));
					map[r][c] = others.size()-1;
				}
			}
		}
		String s = br.readLine();
		for (int t = 1; t <= s.length(); t++) {
			int d = s.charAt(t-1) - '0';
			// 종수 아두이노를 옮긴다.
			movePlayer(d);
			// 종수 아두이노가 파괴되었는지 확인
			if (player.destroyed) {
				System.out.println("kraj " + t);
				return;
			}
			// 다른 아두이노들을 옮긴다.
			for (int i = 1; i < others.size(); i++) {
				Arduino a = others.get(i);
				map[a.r][a.c] = 0;
			}
			for (int i = 1; i < others.size(); i++) {
				if (others.get(i).destroyed) continue;
				moveOthers(i);
				if (player.destroyed) {
					System.out.println("kraj " + t);
					return;
				}
			}			
			for (int i = 1; i < others.size(); i++) {
				for (int j = i+1; j < others.size(); j++) {
					Arduino a = others.get(i);
					Arduino b = others.get(j);
					if (a.r == b.r && a.c == b.c) {
						a.destroy();
						b.destroy();
						map[a.r][a.c] = 0;
					}
				}
			}
			for (int i = others.size() - 1; i > 0; i--) {
				Arduino a = others.get(i);
				if (a.destroyed) others.remove(i);
			}
			for (int i = 1; i < others.size(); i++) {
				Arduino a = others.get(i);
				if (!a.destroyed) {
					map[a.r][a.c] = a.idx;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c] == -1 ? "I" : (map[r][c] > 0 ? "R" : "."));
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 종수 아두이노를 옮기는 함수
	private static void movePlayer(int d) {
		int nr = player.r + dr[d];
		int nc = player.c + dc[d];
		if (map[nr][nc] > 0) {
			player.destroy();
			others.get(map[nr][nc]).destroy();
			return;
		}
		map[player.r][player.c] = 0;
		map[nr][nc] = -1;
		player.move(d);		
	}
	// 다른 아두이노들을 옮기는 함수
	private static void moveOthers(int i) {
		int minD = 0;
		int min = Integer.MAX_VALUE;
		Arduino cur = others.get(i);
		for (int d = 1; d <= 9; d++) {
			int nr = cur.r + dr[d];
			int nc = cur.c + dc[d];
			if (check(nr, nc) && min > Math.abs(nr - player.r) + Math.abs(nc - player.c)) {
				min = Math.abs(nr - player.r) + Math.abs(nc - player.c);
				minD = d;
			}
		}
		int nr = cur.r + dr[minD];
		int nc = cur.c + dc[minD];
		if (map[nr][nc] == -1) {
			player.destroy();
			cur.destroy();
			return;
		}
		cur.move(minD);	
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
