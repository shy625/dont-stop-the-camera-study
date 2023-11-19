

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_21278_호석이_두_마리_치킨 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {			
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
			dp[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			dp[A][B] = 1;
			dp[B][A] = 1;
		}
		// 플로이드-와샬 알고리즘 활용
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (j == i || j == k) continue;
					if (dp[i][k]+dp[k][j] < dp[i][j]) dp[i][j] = dp[i][k] + dp[k][j];
				}
			}
		}
		// 건물 2개가 지어질 건물 번호
		int minA = -1;
		int minB = -1;
		int min = Integer.MAX_VALUE; // 왕복 시간의 합의 최솟값
		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				int sum = 0;
				for (int k = 1; k <= N; k++) {
					sum += Math.min(dp[i][k], dp[j][k])*2;
				}
				if (min > sum) {
					min = sum;
					minA = i;
					minB = j;
				}
			}
		}
		System.out.println(minA+" "+minB+" "+min);
	}

}
