/**
 * BJ 18291 비요뜨의 징검다리 건너기
 * @ prob : https://www.acmicpc.net/problem/18291
 * @ level : G5
 */

import java.io.*;

public class Week29_BJ_18291_비요뜨의징검다리건너기 {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                sb.append(1);
            } else {
                sb.append(pow(2, N - 2));
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);

        br.close();
    }

    // 방법 1 : 재귀
    private static long pow(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        // 계산 결과 저장
        // 계산 결과를 저장해서 사용하지 않고 재귀 호출을 여러번 하는 경우 시간초과 발생
        long tmp = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return tmp * tmp % MOD;
            // 시간 초과
            // return pow(base, exp / 2) * pow(base, exp / 2) % MOD;
        } else {
            return base * tmp * tmp % MOD;
            // 시간 초과
            // return base * pow(base, exp / 2) * pow(base, exp / 2) % MOD;
        }
    }

    // 방법 2 : 반복
    // private static long pow(long base, int exp) {
    //     long res = 1;
    //     while (exp > 0) {
    //         if ((exp & 1) == 1) {
    //             res = res * base % MOD;
    //         }
    //         base = base * base % MOD;
    //         exp >>= 1;
    //     }
    //     return res;
    // }
}

// 풀이 1
// 주어진 경우의 수 조건 찾기
// (N, result) -> (1, 1), (2, 1), (3, 2), (4, 4), (5, 8), (6, 16), ...
// 따라서 result = 2^(N-2)
// 2의 거듭제곱 계산
// 그냥 Math.pow(2, N - 2)를 하게 되면 int, long 범위를 벗어나기 때문에
// 매 결과마다 주어진 수로 나누면서 직접 거듭제곱 계산
// 그냥 반복해서 곱하면 O(N)의 시간이 걸리므로
// 분할정복을 이용해 O(logN)으로 단축
// 재귀 또는 반복으로 구현 가능
// 계산 중에 값 범위를 벗어날 수 있으므로 자료형과 MOD로 나누는 시점에 주의