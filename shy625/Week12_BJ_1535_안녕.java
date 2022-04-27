/**
 * BJ 1535 안녕
 * @ prob : https://www.acmicpc.net/problem/1535
 * @ level : S2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week12_BJ_1535_안녕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] health = new int[N+1];    // 1 ~ N까지
        int[] happy = new int[N+1];     // 1 ~ N까지

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            health[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N+1][101];     // DP 배열 : j 체력일 때 i번째 사람을 고려한 최대 행복 결과 
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= 100; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if(j <= health[i]) {     // i번째 체력이 남은 체력보다 많아 i번째 사람을 선택할 수 없는 경우
                    dp[i][j] = dp[i-1][j];
                } else {    // i번째 사람을 선택할 수 있는 경우
                    // i번째 사람을 선택하는 것과 선택하지 않는 것 중 최댓값 저장
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-health[i]] + happy[i]);
                }
            }
        }

        System.out.println(dp[N][100]);     // 모든 사람을 고려한 체력 100일 때의 최적값

        br.close();
    }
}

// 풀이 1 - 실패
// DP 사용
// https://www.acmicpc.net/source/42549401

// int maxHappy = 0;
// int[][] dp = new int[N][100];
// if(health[0] < 100) {
//     dp[0][health[0]] = happy[0];    // 첫번째 사람을 선택한 결과 초기화
// }
// for(int i = 1; i < N; i++) {
//     if(health[i] < 100) {   // 소모 체력이 100 미만인 것만 고려
//         dp[i][health[i]] = happy[i];    // i번째 사람만 선택한 결과

//         for(int j = 0; j < 100; j++) {
//             if(dp[i-1][j] > 0) {
//                 dp[i][j] = dp[i-1][j];
//                 if(j + health[i] < 100) {   // 이전 사람의 선택 결과에 i번째 사람을 선택할 수 있는지 확인
//                     dp[i][j + health[i]] = dp[i-1][j] + happy[i];
//                     maxHappy = Math.max(maxHappy, dp[i][j + health[i]]);
//                 }
//             }
//         }
//     }
// }
// System.out.println(maxHappy);

// 풀이 2
// DP 사용
// 0-1 Knapsack 문제와 동일
// dp[i][j] : j 체력일 때 i번째 사람을 고려한 최대 행복 결과 저장
// 1. dp[i][j] = dp[i-1][j] - 체력이 부족해 i번째 사람을 선택할 수 없는 경우
// 2. dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-health[i]] + happy[i]) - i번째 사람을 고려하는 경우