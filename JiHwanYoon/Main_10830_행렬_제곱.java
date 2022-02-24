

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10830_행렬_제곱 {
	static int N;
	// B가 1,000,000,000,000까지 될 수 있으므로 int 타입이 아닌 long 타입으로 선언해야 한다.
	static long B;
	static int[][] matrix;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		B = Long.parseLong(s[1]);
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				// B = 1인 경우에도 각 원소를 1000으로 나눈 나머지 형태로 matrix를 출력해야 하기에 행렬의 각 원소를 입력 받을 때도 1000으로 나눈 나머지로 한다.
				matrix[i][j] = Integer.parseInt(s[j])%1000;
			}
		}
		// 행렬 제곱 계산
		int[][] res = matrixPower(B);
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
	}
	// B가 매우 크기 때문에 분할 정복을 통해 행렬 제곱을 계산한다.
	private static int[][] matrixPower(long pow) {
		// 기본 조건
		if (pow == 1) {
			return matrix;
		}
		// M^(pow/2)를 미리 계산
		int[][] temp = matrixPower(pow/2);
		// 제곱수가 짝수일 때는 M^(pow/2) * M^(pow/2)를 계산
		if (pow%2 == 0) {
			return matMul(temp, temp);
		} else { // 제곱수가 홀수일 때는 M^(pow/2) * M^(pow/2) * M을 계산
			return matMul(matMul(temp, temp), matrix);
		}
	}
	// 두 행렬의 곱을 계산하는 함수
	private static int[][] matMul(int[][] X, int[][] Y) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					temp[i][j] = (temp[i][j] + X[i][k]*Y[k][j])%1000;
				}
			}
		}
		return temp;
	}

}
