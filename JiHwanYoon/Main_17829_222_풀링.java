

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17829_222_풀링 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(pooling(arr, N));
	}
	// 재귀를 활용해 가장 마지막에 남는 숫자를 구하는 함수
	// N*N 크기의 행렬 arr에 pooling을 적용한다.
	private static int pooling(int[][] arr, int N) {
		if (N == 1) return arr[0][0];
		int[][] next = new int[N/2][N/2];
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < N/2; j++) {
				next[i][j] = find(arr, i, j);
			}
		}
		return pooling(next, N/2);
	}
	// 행렬 arr의 (2*R, 2*C)를 왼쪽 위 꼭짓점으로 하는 2*2 정사각형에서 두 번째로 큰 숫자를 찾는 함수
	private static int find(int[][] arr, int R, int C) {
		int[] nums = new int[4];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				nums[i*2+j] = arr[2*R+i][2*C+j];
			}
		}
		Arrays.sort(nums);
		return nums[2];
	}

}
