

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_27277_장기자랑 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// N이 1인 경우 arr[0]과 같다.
		if (N == 1) {
			System.out.println(arr[0]);
			System.exit(0);
		}
		// greedy algorithm 활용
		// 1, 3, 5, ... 번째는 실력이 가장 높은 순서대로 배치하고,
		// 2, 4, 6, ... 번째는 실력이 가장 낮은 순서대로 배치
		Arrays.sort(arr);
		int[] res = new int[N];
		for (int i = 0; i < N/2; i++) {
			res[2*i+1] = arr[i];
		}
		for (int i = 0; i < (N+1)/2; i++) {
			res[((N+1)/2)*2-2-i*2] = arr[i+N/2];
		}
		int sum = res[0];
		for (int i = 1; i < N; i++) {
			sum += Math.max(res[i] - res[i-1], 0);
		}
		System.out.println(sum);
	}

}
