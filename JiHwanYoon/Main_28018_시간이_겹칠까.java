

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_28018_시간이_겹칠까 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 누적합을 활용한다.
		// 우선 시작 시간에 1, 종료 시간+1에 -1을 더하고,
		// 누적합을 수행하면 시작 시간~종료 시간까지 1이 더해짐을 이용
		int[] sum = new int[1_000_002];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			sum[S] += 1;
			sum[E+1] -= 1;
		}
		for (int i = 1; i <= 1_000_001; i++) {
			sum[i] += sum[i-1];
		}
		int Q = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int T = Integer.parseInt(st.nextToken());
			sb.append(sum[T]).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
