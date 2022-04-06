/**
 * BJ 1912 연속합
 * @ prob : https://www.acmicpc.net/problem/1912
 * @ level : S2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week9_BJ_1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];  // dp 배열
        int max = 0;

        dp[0] = arr[0];
        max = dp[0];
        for(int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + dp[i], dp[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

        br.close();
    }
}

// 풀이 1
// DP 사용
// i번째 원소를 더했을 때, 이전까지의 연속합에 더하는 것과 더하지 않는 것 중 큰 값을 저장
// dp[i] = Math.max(dp[i-1] + arr[i], arr[i])