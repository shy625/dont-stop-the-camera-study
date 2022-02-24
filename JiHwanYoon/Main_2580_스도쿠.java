

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_2580_스도쿠 {
	// 변수 초기화
	static int[][] sudoku = new int[9][9];
	// 스도쿠 내에서 채워야 할 부분의 위치를 저장하는 ArrayList
	static ArrayList<int[]> zeros = new ArrayList<>();
	// 채워야 할 부분의 개수
	static int N;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(s[j]);
				// zeros에 채워야 할 부분의 위치를 저장
				if (sudoku[i][j] == 0) zeros.add(new int[] {i, j});
			}
		}
		N = zeros.size();
		// 백트래킹을 통해 구현
		backtracking(0);
		// 출력
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static boolean backtracking(int cnt) {
		// base condition
		if (cnt == N) {
			return true;
		}
		// 현재 채울 부분의 위치를 찾는다.
 		int[] loc = zeros.get(cnt);
 		// 1부터 9까지 수를 넣어본다.
		for (int i = 1; i <= 9; i++) {
			// 단, 수를 넣을 수 있는지 확인한 뒤 넣는다.
			if (check(loc, i)) {
				sudoku[loc[0]][loc[1]] = i;
				// 다음으로 채울 부분으로 넘어가되 반환되는 값이 참, 즉 나머지 부분도 적절하게 채웠을 때 스도쿠가 완성된다면 true를 반환하고 함수를 종료한다.
				if (backtracking(cnt+1)) return true;
				// 실패하면 해당 부분을 0으로 초기화하고 다음 숫자로 넘어간다.
				sudoku[loc[0]][loc[1]] = 0;
			}
		}
		// 어떤 숫자도 해당 부분에 넣었을 때 만족하는 스도쿠가 없으면 false를 반환한다.
		return false;
	}
	// 어떤 수를 스도쿠의 비워진 부분에 넣었을 때 스도쿠의 성질을 만족하는지 확인하는 함수
	private static boolean check(int[] loc, int n) {
		// 수를 채울 위치를 기준으로 가로와 세로 확인
		for (int i = 0; i < 9; i++) {
			if (sudoku[loc[0]][i] == n || sudoku[i][loc[1]] == n) {
				return false;
			}
		}
		// 수를 채울 위치를 기준으로 3*3 정사각형 확인
		int squareR = 3*(loc[0]/3);
		int squareC = 3*(loc[1]/3);
		for (int i = squareR; i < squareR+3; i++) {
			for (int j = squareC; j < squareC+3; j++) {
				if (sudoku[i][j] == n) return false;
			}
		}
		// 위 기준을 모두 통과하면 true 반환
		return true;
	}
}
