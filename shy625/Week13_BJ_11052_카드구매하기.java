/**
 * BJ 11052 카드 구매하기
 * @ prob : https://www.acmicpc.net/problem/11052
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week13_BJ_11052_카드구매하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];    // dp 배열 : i개 카드를 구매하는 가장 최대 비용
        for(int i = 1; i <= N; i++) {
            int max = 0;
            for(int j = 1; j <= i; j++) {
                max = Math.max(max, dp[i-j] + P[j]);
            }
            dp[i] = max;
        }

        System.out.println(dp[N]);

        br.close();
    }
}

// 풀이 1
// DP 사용
// dp[i] : i개 카드를 구매하는 가장 최대 비용
// dp[i] = max(dp[i-1] + P[1], dp[i-2] + P[2], dp[i-3] + P[3], ... , dp[i-i] + P[i])
// 1~i개짜리 카드팩을 살 때의 비용들 중 최댓값을 저장