

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11057_오르막_수 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int CONST = 10007;
		// dynamic programming을 활용
		// arr[i]는 가장 끝 자리가 i일 때 가능한 오르막 수의 개수
		int[] arr = new int[10];
		Arrays.fill(arr, 1);
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 9-j; k++) {
					arr[9-j] = (arr[9-j] + arr[k])%CONST;
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + arr[i])%CONST;
		}
		System.out.println(sum);
	}

}
