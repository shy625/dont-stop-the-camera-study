

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630_색종이_만들기 {
	static int N;
	static int[][] paper;
	static int[] cnts;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 하얀색 색종이의 개수를 cnts[0], 파란색 색종이의 개수를 cnts[1]에 저장하는 배열
		cnts = new int[2];
		// 재귀함수를 활용해 각 색의 색종이의 개수를 센다.
		count(0, 0, N);
		System.out.println(cnts[0]);
		System.out.println(cnts[1]);
	}
	// 재귀적으로 각 색의 색종이를 찾는 함수
	// (r, c)가 왼쪽 꼭짓점이면서, 길이가 l인 정사각형의 색종이에 대해 조사한다.
	private static void count(int r, int c, int l) {
		int color = paper[r][c]; // 왼쪽 꼭짓점의 색
		boolean isSameColor = true; // 색종이가 하나의 색으로 이루어져 있는지를 나타내는 변수
		// (r, c)가 왼쪽 꼭짓점이면서, 길이가 l인 정사각형의 색종이가 하나의 색으로 이루어져 있는지 확인
		outer: for (int i = r; i < r+l; i++) {
			for (int j = c; j < c+l; j++) {
				if (paper[i][j] != color) {
					isSameColor = false;
					break outer;
				}
			}
		}
		// 만약 하나의 색으로 이루어져 있다면, 해당 색종이의 개수를 1 증가시킨다.
		if (isSameColor) cnts[color]++;
		// 그렇지 않으면 해당 색종이를 4등분해 재귀적으로 진행한다.
		else {
			count(r, c, l/2);
			count(r+l/2, c, l/2);
			count(r, c+l/2, l/2);
			count(r+l/2, c+l/2, l/2);
		}
		
	}

}
