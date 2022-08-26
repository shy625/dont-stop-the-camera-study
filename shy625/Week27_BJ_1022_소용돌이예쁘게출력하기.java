/**
 * BJ 1022 소용돌이 예쁘게 출력하기
 * @ prob : https://www.acmicpc.net/problem/1022
 * @ level : G4
 */

import java.io.*;
import java.util.*;

public class Week27_BJ_1022_소용돌이예쁘게출력하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int[][] map = new int[r2 - r1 + 1][c2 - c1 + 1];

        int max = 0;
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                map[r - r1][c - c1] = getSpiralValue(r, c);
                max = Math.max(max, map[r - r1][c - c1]);
            }
        }

        int maxDigitCount = (int) Math.log10(max) + 1;
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                int digitCount = (int) Math.log10(map[r][c]) + 1;
                if (digitCount < maxDigitCount) {
                    for (int i = 0; i < maxDigitCount - digitCount; i++) {
                        sb.append(" ");
                    }
                }
                sb.append(map[r][c] + " ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb.toString());

        br.close();
    }

    private static int getSpiralValue(int r, int c) {
        int dist = Math.max(Math.abs(r), Math.abs(c));
        int base = (2 * dist + 1) * (2 * dist + 1);     // 규칙을 가지는 우측 하단 대각선 값

        if (r == dist) {    // 하단 행
            return base - (dist - c);
        } else if (c == -dist) {    // 좌측 열
            return base - 2 * dist - (dist - r);
        } else if (r == -dist) {    // 상단 행
            return base - 4 * dist - (dist + c);
        } else {    // 우측 열
            return base - 6 * dist - (dist + r);
        }
    }
}

// 풀이 1 - 메모리 초과
// 주어지는 꼭짓점 2개 입력 받기
// 주어진 두 점의 좌표값 중 소용돌이의 중심으로부터 가장 먼 값 체크
// 해당 값 * 2 + 1을 한 변의 길이로 하는 정사각형 모양의 소용돌이 생성
// 처리의 편의성을 위해 중심에 가장 먼 값을 배열 좌표에 더해 소용돌이의 왼쪽 상단이 (0,0) 이 되도록 조정
// 생성한 정사각형 모양의 소용돌이에서 주어진 두 꼭짓점에 해당하는 범위를 조건에 맞게 출력
// 출력 시 값의 자릿수를 체크해 가장 값의 자릿수보다 작으면 공백 추가
// 처음에 주어지는 꼭짓점 좌표 중 하나라도 5000이라는 수가 들어오면
// 결국 한 변의 길이가 10001인 정사각형 모양의 소용돌이를 그려야 함
// 10001 * 10001 * 4B > 128MB 이기 때문에 메모리 초과 발생

// 풀이 2
// 주어지는 꼭짓점 2개 입력 받기
// 정사각형 모양의 소용돌이에서 중심으로부터 우측 하단 대각선에 위치한 값들의 규칙 확인
// 중심으로부터의 거리 (행 또는 열) * 2 + 1 의 제곱
// 해당 규칙을 이용해 전체 소용돌이를 그리지 않고 주어진 범위 내의 값들을 바로 찾기
// 주어진 범위의 배열 값들을 모두 채우면 조건에 맞게 출력