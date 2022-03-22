

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2217_로프 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] ropes = new int[N];
		for (int i = 0; i < N; i++) {
			int w = Integer.parseInt(br.readLine());
			ropes[i] = w;
		}
		Arrays.sort(ropes);
		int max = 0;
		// 로프의 허용 무게가 작은 것부터 하나씩 제거해 보면서 들 수 있는 최대 무게를 계산해본다.
		for (int i = 0; i < N; i++) {
			max = Math.max(max, (N - i) * ropes[i]);
		}
		System.out.println(max);
	}

}
