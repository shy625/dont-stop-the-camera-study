

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_11559_Puyo_Puyo {
	static ArrayList<int[]> popGroup;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 없어질 수 있는 뿌요 그룹들을 모아둔다.
		popGroup = new ArrayList<>();
		// 몇 번째 연쇄인지 기록
		int cnt = 0;
		while (true) {
			// 없어질 수 있는 뿌요 그룹이 없으면 break
			if (!findGroup()) break;
			// 뿌요 그룹 제거
			removeGroup();
			// 중력 작용
			gravity();
			cnt++;
		}
		System.out.println(cnt);
	}
	
	// 없어질 수 있는 뿌요 그룹을 찾는다.
	private static boolean findGroup() {
		boolean[][] visited = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (!visited[i][j] && map[i][j] != '.') {
					bfsToFind(i, j, map[i][j], visited); // bfs를 통해 인접한 뿌요들을 모두 찾는다.
				}
			}
		}
		return popGroup.size() > 0;
	}
	
	// 뿌요 그룹을 찾기 위한 bfs
	private static void bfsToFind(int i, int j, char color, boolean[][] visited) {
		 Queue<int[]> q = new LinkedList<>();
		 q.offer(new int[] {i, j});
		 visited[i][j] = true;
		 int cnt = 1;
		 while (!q.isEmpty()) {
			 int[] u = q.poll();
			 for (int d = 0; d < 4; d++) {
				 int nr = u[0] + dr[d];
				 int nc = u[1] + dc[d];
				 if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == color) {
					 cnt++;
					 visited[nr][nc] = true;
					 q.offer(new int[] {nr, nc});
				 }
			 }
		 }
		 // 만약 인접한 뿌요의 개수가 4개 이상이면 없어질 수 있는 뿌요 그룹을 만들 수 있다. 
		 if (cnt >= 4) {
			 popGroup.add(new int[] {i, j});
		 }
	}
	
	private static boolean check(int r, int c) {
		return 0<=r && r<12 && 0<=c && c<6;
	}
	
	// 뿌요 그룹 제거
	private static void removeGroup() {
		for (int[] u : popGroup) {
			bfsToRemove(u[0], u[1], map[u[0]][u[1]]);
		}
		popGroup.clear();
	}
	
	// 뿌요 그룹 제거를 위한 bfs
	private static void bfsToRemove(int i, int j, char color) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[12][6];
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		map[i][j] = '.';
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == color) {
					map[nr][nc] = '.';
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}	
	}
	
	// 중력 작용
	private static void gravity() {
		for (int j = 0; j < 6; j++) {
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < 12; i++) {
				if (map[i][j] != '.') stack.push(map[i][j]);
			}
			int temp_i = 11;
			while (!stack.isEmpty()) {
				map[temp_i][j] = stack.pop();
				temp_i--;
			}
			for (int i = temp_i; i >= 0; i--) {
				map[i][j] = '.';
			}
		}
	}
}
