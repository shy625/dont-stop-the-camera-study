

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11054_가장_긴_바이토닉_부분_수열 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 활용
		int[] inc = new int[N]; // i번쨰 수를 포함하는, 0~i번째까지의 수열 내에서 가장 긴 증가하는 부분 수열의 길이
		int[] dec = new int[N]; // i번쨰 수를 포함하는, i~N-1번째까지의 수열 내에서 가장 긴 감소하는 부분 수열의 길이
		// 초기화
		Arrays.fill(inc, 1);
		Arrays.fill(dec, 1);
		// 증가하는 부분 수열을 찾는다.
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) inc[i] = Math.max(inc[i], inc[j]+1);
			}
		}
		// 감소하는 부분 수열을 찾는다.
		for (int i = N-2; i >= 0; i--) {
			for (int j = N-1; j > i; j--) {
				if (arr[i] > arr[j]) dec[i] = Math.max(dec[i], dec[j]+1);
			}
		}
		int max = 1; // 가장 긴 바이토닉 부분 수열의 길이
		for (int i = 0; i < N; i++) {
			max = Math.max(max, inc[i] + dec[i] - 1); // i번째 수를 2번 포함하므로 하나 빼준다.
		}
		System.out.println(max); 
	}

}
