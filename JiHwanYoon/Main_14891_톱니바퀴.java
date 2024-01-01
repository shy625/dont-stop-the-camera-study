

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
	static int[][] wheels;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		wheels = new int[4][8];
		for (int i = 0; i < 4; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = cs[j] - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			// 회전으로 인해 영향을 받을 톱니바퀴들 전체를 회전시키기 위해, 가장 바깥쪽 톱니바퀴부터 회전시킨다.
			if (n > 0 && wheels[n-1][2] != wheels[n][6]) rotateLeft(n-1, -d);
			if (n < 3 && wheels[n][2] != wheels[n+1][6]) rotateRight(n+1, -d);
			// 주변 톱니바퀴를 회전시킨 후, 자신을 회전시킨다.
			rotate(n, d);
		}
		// 점수 계산
		int score = 0;
		for (int i = 0; i < 4; i++) {
			score += wheels[i][0] * Math.pow(2, i);
		}
		System.out.println(score);
	}
	// 오른쪽에 있는 톱니바퀴를 회전시킨 후, 자신을 회전시키는 함수
	private static void rotateRight(int n, int d) {
		if (n < 3 && wheels[n][2] != wheels[n+1][6]) {
			rotateRight(n+1, -d);
		}
		rotate(n, d);
	}
	// 왼쪽에 있는 톱니바퀴를 회전시킨 후, 자신을 회전시키는 함수
	private static void rotateLeft(int n, int d) {
		if (n > 0 && wheels[n-1][2] != wheels[n][6]) {
			rotateLeft(n-1, -d);
		}
		rotate(n, d);
	}
	// 톱니바퀴를 회전시키는 함수
	private static void rotate(int n, int d) {
		int temp = wheels[n][0];
		for (int i = 1; i < 8; i++) {
			wheels[n][((i-1)*(-d)+8)%8] = wheels[n][(i*(-d)+8)%8];
		}
		wheels[n][(d+8)%8] = temp;
	}

}

