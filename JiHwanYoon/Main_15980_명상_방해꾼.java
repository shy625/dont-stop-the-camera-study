

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15980_명상_방해꾼 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] birds = new int[N+1][M];
		int[] sum = new int[M]; // 각 초마다 정신의 중심이 이동하는 정도
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char d = st.nextToken().charAt(0);
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				int n = s.charAt(j) - '0';
				if (d == 'L') n *= -1;
				birds[i][j] = n;
				sum[j] += n;
			}
		}
		// 누적합을 활용
		int minIdx = 0; // 정신을 방해받는 정도가 최소가 되기 위해 잡아야 할 새
		int min = Integer.MAX_VALUE; // 정신을 방해받는 정도의 최솟값
		// 각 새를 잡았을 때 정신을 방해받는 정도를 구한다.
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				sum[j] -= birds[i][j];
			}
			int cur = 0;
			int max = 0;
			for (int j = 0; j < M; j++) {
				cur += sum[j];
				max = Math.max(max, Math.abs(cur));
			}
			if (min > max) {
				min = max;
				minIdx = i;
			}
			for (int j = 0; j < M; j++) {
				sum[j] += birds[i][j];
			}
		}
		System.out.println(minIdx);
		System.out.println(min);
	}

}
