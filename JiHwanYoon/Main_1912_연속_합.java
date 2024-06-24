

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속_합 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 분할 정복을 이용하되 분할을 반씩 하는 것이 아닌, i번째 원소를 조사할 때 i-1번째 원소까지와 i번째 원소로 분할해
		// i-1번째 원소까지의 합의 최댓값과 i번째 원소의 값, 그리고 i번째 원소부터 i-1번째 원소를 포함하는, 연속 합의 최댓값(thisSum) 중 가장 큰 값을 고른다.
		int thisSum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			thisSum += arr[i];
			maxSum = Math.max(maxSum, thisSum);
			if (thisSum < 0) thisSum = 0; // thisSum이 0보다 작아지면 i+1번쨰 원소가 thisSum + i+1번째 원소보다 무조건 작으므로 thisSum을 초기화
		}
		System.out.println(maxSum);
	}
}
