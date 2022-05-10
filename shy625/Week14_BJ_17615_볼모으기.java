/**
 * BJ 17615 볼 모으기
 * @ prob : https://www.acmicpc.net/problem/17615
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week14_BJ_17615_볼모으기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] balls = br.readLine().toCharArray();

        char leftEndColor = balls[0], rightEndColor = balls[N - 1];
        int leftEndCnt = 0, rightEndCnt = 0;
        boolean leftEnd = true;
        int midRCnt = 0, midBCnt = 0;

        for (int i = 0; i < balls.length; i++) {
            // 왼쪽 끝에 연속한 공의 개수 구하기
            if (leftEnd) {
                if (balls[i] == leftEndColor) {
                    leftEndCnt++;
                } else {
                    leftEnd = false; // 다른 색의 공이 나오면 왼쪽 끝 공 개수 구하기 완료
                }
            }

            if (!leftEnd) { // 왼쪽 끝에 연속한 공의 개수 구하기 완료 후
                // 가운데 위치한 R, B 공 개수 세기
                if (balls[i] == 'R') {
                    midRCnt++;
                } else {
                    midBCnt++;
                }

                // 오른쪽 끝에 연속한 공의 개수 구하기
                if (balls[i] == rightEndColor) {
                    if (balls[i - 1] != balls[i]) {
                        rightEndCnt = 1;
                    } else {
                        rightEndCnt++;
                    }
                }
            }
        }
        // 공 개수 세기 완료 후 가운데 위치한 공의 개수에서 오른쪽 끝에 연속한 공의 개수 빼기
        if (rightEndColor == 'R') {
            midRCnt -= rightEndCnt;
        } else {
            midBCnt -= rightEndCnt;
        }

        // R 공의 최소 이동횟수 구하기
        int moveRCnt = midRCnt;     // 가운데 위치한 R 공 모두 이동
        if (leftEndColor == 'R' && rightEndColor == 'R') {  // 양 쪽 끝이 둘 다 R 이면 더 적은 쪽을 이동
            if (leftEndCnt > rightEndCnt) {
                moveRCnt += rightEndCnt;
            } else {
                moveRCnt += leftEndCnt;
            }
        }

        // B 공의 최소 이동횟수 구하기
        int moveBCnt = midBCnt;     // 가운데 위치한 B 공 모두 이동
        if (leftEndColor == 'B' && rightEndColor == 'B') {  // 양 쪽 끝이 둘 다 B 이면 더 적은 쪽을 이동
            if (leftEndCnt > rightEndCnt) {
                moveBCnt += rightEndCnt;
            } else {
                moveBCnt += leftEndCnt;
            }
        }

        System.out.println(moveRCnt > moveBCnt ? moveBCnt : moveRCnt);

        br.close();
    }
}

// 풀이 1
// 가운데에 있는 R 또는 B 공들을 모두 왼쪽 또는 오른쪽으로 옮겨야 한다.
// 왼쪽 끝에 연속해 있는 공의 색과 개수, 오른쪽 끝에 연속해 있는 공의 색과 개수, 양 끝을 제외하고 가운데 있는 R, B의 공의 개수를 구함
// 경우에 따라 R 공을 움직일 때의 최소 이동횟수 계산
// 1. 가운데 있는 R 공들은 무조건 왼쪽 또는 오른쪽으로 이동
// 2. 양 끝에 연속해 있는 공들이 둘 다 R이면 둘 중 적은 수를 반대쪽으로 모두 이동
// B 공에 대해서도 동일하게 계산
// 계산한 두 값 중 최솟값 출력

// 다른 풀이 1
// 같은 접근방법, 더 깔끔한 풀이
// https://www.acmicpc.net/source/24190241
// https://www.acmicpc.net/source/19108323