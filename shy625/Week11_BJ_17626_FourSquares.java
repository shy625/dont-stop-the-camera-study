/**
 * BJ 17626 Four Squares
 * @ prob : https://www.acmicpc.net/problem/17626
 * @ level : S4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week11_BJ_17626_FourSquares {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];    // DP 배열

        dp[1] = 1;
        for(int i = 2; i <= N; i++) {
            int maxSquare = (int)Math.sqrt(i);
            int min = 4;
            for(int j = 1; j <= maxSquare; j++) {
                min = Math.min(min, dp[i - j*j]);   // n에서 갈 수 있는 값들 중 최솟값
            }
            dp[i] = min + 1;
        }

        System.out.println(dp[N]);

        br.close();
    }
}

// 풀이 1 - 시간 초과
// 중복 조합, 재귀 사용

// private static void check(int n, int count) {
//     // base condition
//     if(n == 0) {
//         minCount = Math.min(minCount, count);
//         return;
//     }
//     // backtracking
//     if(minCount <= count) {
//         return;
//     }
//     // n보다 작은 완전제곱수들을 돌아가면서 선택
//     int a = (int)Math.sqrt(n);
//     for(int i = a; i >= 1; i--) {
//         check(n - i * i, count + 1);
//     }
// }

// 풀이 2
// DP 사용
// 현재 값을 만드는 제곱수의 최소 개수를 저장
// 현재 값 이하의 완전제곱수를 뺀 값의 dp 값 + 1
// dp[i] = Math.min(min, dp[i - j*j]) + 1

// 다른 풀이 1
// N에서 특정 완전제곱수를 밴 결과가 다시 완전제곱수가 되는지 확인
// 최대 4개 밖에 안되므로 그냥 1, 2, 3, 4개를 사용하는 경우에 대해서 다 체크
// https://www.acmicpc.net/source/31821693

// 다른 풀이 2
// 중복 조합, 반복문 사용
// https://www.acmicpc.net/source/35523860