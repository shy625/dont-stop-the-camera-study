/**
 * BJ 10422 괄호
 * @ prob : https://www.acmicpc.net/problem/10422
 * @ level : G3
 */

import java.io.*;

public class Week33_BJ_10422_괄호 {

    private static final int DIVISOR = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] memo = new long[2501];

        memo[0] = 1;
        for (int i = 1; i < memo.length; i++) {
            for (int j = 0; j < i; j++) {
                memo[i] += (memo[j] * memo[i - j - 1]) % DIVISOR;
                memo[i] %= DIVISOR;
            }
        }

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int input = Integer.parseInt(br.readLine());
            if (input % 2 == 0) {
                sb.append(memo[input / 2]);
            } else {
                sb.append(0);
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// DP 사용 - 규칙은 카탈랑 수
// memo[n + 1] = memo[0] * memo[n] + memo[1] * memo[n - 1] + ... + memo[n - 1] * memo[1] + memo[n] * memo[0]
// 1, 1, 2, 5, 14, 42, 132, ...
// memo 배열 계산 시 int 범위가 넘어갈 수 있기 때문에 long으로 선언
// memo 배열의 최댓값 = 약 10억, 계산 시 10억 * 10억이 있을 수 있음