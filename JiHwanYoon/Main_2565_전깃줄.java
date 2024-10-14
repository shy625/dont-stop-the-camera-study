

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2565_전깃줄 {
	// 전깃줄을 나타내는 클래스
	static class Edge implements Comparable<Edge> {
		int A, B;
		public Edge(int A, int B) {
			this.A = A;
			this.B = B;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.A, e.A);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Edge[] arr = new Edge[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[i] = new Edge(A, B);
		}
		// dynamic programming을 활용
		// 우선, 전깃줄을 A에 연결된 위치가 작은 순서대로 정렬
		// 그리고, i번째 전깃줄이 남아있을 때 0~i번째 전깃줄 중 남겨둘 수 있는 전깃줄 개수의 최댓값을 dp[i]로 하는 배열을 정의
		Arrays.sort(arr);
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int max = 0; // 남겨둘 수 있는 전깃줄 개수의 최댓값
		for (int i = 0; i < N; i++) {
			int temp = arr[i].B;
			for (int j = 0; j < i; j++) {
				if (arr[j].B < temp) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(N - max);
	}
}