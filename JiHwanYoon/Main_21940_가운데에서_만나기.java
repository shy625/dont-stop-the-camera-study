

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21940_가운데에서_만나기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE/10);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			graph[A][B] = T;
		}
		int K = Integer.parseInt(br.readLine());
		int[] people = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		// 플로이드-와샬 알고리즘 활용
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (j == i || j == k) continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) graph[i][j] = graph[i][k] + graph[k][j];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		String blank = " ";
		// 준형이와 친구들의 왕복시간의 최댓값의 최솟값
		int min = Integer.MAX_VALUE;
		for (int X = 1; X <= N; X++) {
			int max = -1;
			for (int p : people) {
				// X를 갈 수 없는 경우
				if (graph[p][X] == Integer.MAX_VALUE/10 || graph[X][p] == Integer.MAX_VALUE/10) continue;
				max = Math.max(max, graph[p][X] + graph[X][p]);
			}
			// 최솟값 갱신 시
			if (max < min) {
				sb.setLength(0);
				min = max;
				sb.append(X).append(blank);
			// 기존의 최솟값과 동일한 경우
			} else if (max == min) {
				sb.append(X).append(blank);
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
