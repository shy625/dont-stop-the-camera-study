/**
 * BJ 18222 투에-모스 문자열
 * @ prob : https://www.acmicpc.net/problem/18222
 * @ level : S2
 */

import java.io.*;

public class Week19_BJ_18222_투에모스문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long K = Long.parseLong(br.readLine());

        int count = 0;
        while (K != 1) {
            K -= findLessPowerOf2(K);
            count++;
        }

        System.out.println(count % 2 == 0 ? 0 : 1);

        br.close();
    }
    
    // k보다 작은 2의 거듭제곱을 찾는 함수
    private static long findLessPowerOf2(long k) {
        long tmp = k;
        int count = 0;
        while (true) {
            tmp = tmp >> 1;
            if (tmp == 0) {
                break;
            }
            count++;
        }
        if (Math.pow(2, count) == k) {
            count--;
        }
        return (long) Math.pow(2, count);
    }
}

// 풀이 1
// 주어진 K값을 포함하는 문자열 X를 만든 후에 문자열에서 주어진 K값 위치의 값을 확인하고자 시도
// List<Boolean>, String, boolean Array 등으로 생성하여 K index에 접근 시도했으나
// get(), charAt(), 배열의 index 값에 long 타입이 허용되지 않는 문제로 인해 진행 불가

// 풀이 2
// K 자체를 줄이는 방식으로 변경
// K번째 자리의 문자는 K에서 K보다 작은 2의 거듭제곱값을 뺀 위치의 문자의 반대
// 첫번째 문자는 0으로 고정되어 있으므로 K - K보다 작은 2의 거듭제곱 을 1이 될 때까지 반복
// 반복한 횟수가 짝수면 첫번째 문자인 0과 동일, 홀수면 0에서 1번 바꾼 1

// 다른 풀이 1
// 재귀 및 분할 정복을 이용한 풀이
// K의 위치가 문자열의 절반보다 큰 경우와 작은 경우를 나누어 체크 + 딱 절반인 경우
// https://www.acmicpc.net/source/35101717