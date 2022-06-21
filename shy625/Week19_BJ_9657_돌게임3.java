/**
 * BJ 9657 돌 게임 3
 * @ prob : https://www.acmicpc.net/problem/9657
 * @ level : S3
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Week19_BJ_9657_돌게임3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        // memo[n] : n개의 돌이 있을 때 누가 승자가 되는지를 저장하는 DP 배열
        // 0 : 먼저 돌을 가져가는 사람 (상근), 1 : 나중에 돌을 가져가는 사람 (창영)
        int[] memo = new int[N + 1];
        // DP 초기값 세팅
        if (N >= 4) {
            memo[4] = 0;
        }
        if (N >= 3) {
            memo[3] = 0;
        }
        if (N >= 2) {
            memo[2] = 1;
        }
        if (N >= 1) {
            memo[1] = 0;
        }
        // DP 점화식
        for (int i = 5; i <= N; i++) {
            if (memo[i - 1] == 1 || memo[i - 3] == 1 || memo[i - 4] == 1) {
                memo[i] = 0;
            } else {
                memo[i] = 1;
            }
        }

        if (memo[N] == 0) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }

        br.close();
    }
}

// 풀이 1
// DP 사용
// memo[n] : n개의 돌이 있을 때 누가 승자가 되는지를 저장하는 DP 배열
// 0 : 먼저 돌을 가져가는 사람 (상근), 1 : 나중에 돌을 가져가는 사람 (창영)
// if memo[i - 1] == 1 || memo[i - 3] == 1 || memo[i - 4] == 1 -> memo[i] = 0
// else -> memo[i] = 1
// 상근이 이번 턴에 1, 3, 4개의 돌을 가져갔을 때, 다음 턴에 창영이 이기지 않는 경우가 있다면 상근 승리
// 모든 경우에 창영이 이긴다면 상근 패배

// 다른 풀이 1
// 비슷한 접근방식
// 주어진 값에 맞춰 배열을 생성하게 되면 초기값 세팅 시
// ArrayIndexOutOfBoundsException를 피하기 위해 조건문이 여러 개 필요한 경우가 발생할 수 있음
// 따라서 DP 배열 생성 시 최댓값이 그리 크지 않다면 구현의 편의성을 위해서 그냥 처음부터 최대 크기로 선언하는 것도 괜찮아 보임
// https://www.acmicpc.net/source/36498887

// 다른 풀이 2
// 재귀를 이용해 배열값들을 채움
// https://www.acmicpc.net/source/42154634

// 다른 풀이 3
// 규칙을 찾아서 풀이
// https://www.acmicpc.net/source/11973232