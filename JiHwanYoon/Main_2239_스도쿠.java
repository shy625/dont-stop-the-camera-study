

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_2239_스도쿠 {
	static int N;
	static int[][] map;
	static ArrayList<int[]> zeros;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		zeros = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				map[i][j] = cs[j] - '0';
				if (map[i][j] == 0) zeros.add(new int[] {i, j});
			}
 		}
		N = zeros.size();
		perm(0);
	}
	// 순열을 활용해 0이 있는 지점 각각에 숫자를 넣는다.
	private static void perm(int cnt) {
		// 숫자를 다 넣은 경우
		if (cnt == N) {
			// 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			sb.setLength(sb.length() - 1);
			System.out.println(sb.toString());
			System.exit(0);
		}
		// cnt번째 0에 1~9 중 하나를 넣는다.
		int[] zero = zeros.get(cnt);
		for (int i = 1; i <= 9; i++) {
			if (!check(zero[0], zero[1], i)) continue;
			map[zero[0]][zero[1]] = i;
			perm(cnt+1);
			map[zero[0]][zero[1]] = 0;
		}
	}
	// 숫자를 넣을 수 있는지 확인하는 함수
	private static boolean check(int i, int j, int n) {
		// 가로, 세로 확인
		for (int r = 0; r < 9; r++) {
			if (r != i && map[r][j] == n) return false;
			if (r != j && map[i][r] == n) return false;
		}
		// 3*3 격자 확인
		int t_r = (i/3)*3;
		int t_c = (j/3)*3;
		for (int r = t_r; r < t_r + 3; r++) {
			for (int c = t_c; c < t_c + 3; c++) {
				if (i == r && j == c) continue;
				if (map[r][c] == n) return false;
			}
		}
		return true;
	}

}
