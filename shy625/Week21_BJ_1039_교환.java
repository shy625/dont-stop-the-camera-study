/**
 * BJ 1039 교환
 * @ prob : https://www.acmicpc.net/problem/1039
 * @ level : G3
 */

import java.io.*;
import java.util.*;

public class Week21_BJ_1039_교환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);

        int result = bfs(N, K);

        System.out.println(result);

        br.close();
    }

    private static int bfs(int n, int k) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isChecked = new boolean[k][1000001];    // k번쨰 연산에서 동일한 숫자로 교환하지 않도록 체크
        int max = -1;
        q.offer(new int[] { n, 0 });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[1] == k) {  // 연산 횟수가 k이면 최댓값 체크
                max = Math.max(max, cur[0]);
            } else {
                int digitCount = countDigit(cur[0]);
                for (int i = 1; i <= digitCount; i++) {
                    for (int j = i + 1; j <= digitCount; j++) {
                        int next = swapDigit(cur[0], i, j);
                        // 교환 전후 숫자의 자릿수가 다르거나(맨 앞자리가 0인 경우) || 해당 차수 연산에서 이미 나온 숫자인 경우 제외
                        if (digitCount == countDigit(next) && !isChecked[cur[1]][next]) {
                            isChecked[cur[1]][next] = true;
                            q.offer(new int[] { next, cur[1] + 1 });
                        }
                    }
                }
            }
        }
        return max;
    }

    // 주어진 수에서 두 위치의 자릿수 교환
    private static int swapDigit(int num, int i, int j) {
        int iNum = 0, jNum = 0;
        
        int tmpNum = num;
        int count = 0;
        while (tmpNum != 0) {
            count++;
            if (count == i) {
                iNum = tmpNum % 10;
            }
            if (count == j) {
                jNum = tmpNum % 10;
                break;
            }
            tmpNum /= 10;
        }

        num -= iNum * Math.pow(10, i - 1);
        num -= jNum * Math.pow(10, j - 1);
        num += iNum * Math.pow(10, j - 1);
        num += jNum * Math.pow(10, i - 1);

        return num;
    }

    // 주어진 수의 자릿수 세기
    private static int countDigit(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }
}

// 풀이 1 - 실패
// 매 K번째 연산에서 나올 수 있는 가장 최댓값을 찾고, 그 과정을 K번 반복
// 그러나 매 연산에서 최댓값을 찾는 것이 최종 결과가 최댓값임을 보장하지 않음
// ex) 입력이 아래와 같은 경우
// 2133 2
// 해당 풀이대로 하면 2133 -> 3132 -> 3312
// 실제 정답은 2133 -> 3123 -> 3321
// https://www.acmicpc.net/source/45640181


// 풀이 2 - 시간 초과
// BFS를 사용. 모든 경우를 체크하면서 그 중 최종 결과가 최댓값인 경우를 반환
// 입력이 132 3 으로 들어오는 경우, 132 -> 312 -> 321 -> 312 로 똑같은 숫자가 또 나올 수 있기 때문에 별도의 중복 확인 처리를 하지 않음
// 그러나 서로 다른 차수의 연산 결과는 중복될 수 있지만 동일한 K번째 연산에서 똑같은 숫자가 나오는 것은 불필요한 중복
// 따라서 해당 경우 중복 체크 처리를 해주어야 하나 하지 않아서 시간 초과 발생
// https://www.acmicpc.net/source/45641064

// 풀이 3 - 메모리 초과
// BFS에서 큐의 원소를 하나 빼고 교환을 통해 다음 값을 찾을 때 isChecked 배열 생성
// 전체 과정에서 K번째 연산에 대한 중복 체크가 되는 것이 아니라
// 단순히 K번째 연산 수행 차례인 여러 원소들 중 a -> b로 교환될 때 중복되는 b가 나오지 않도록 동작
// 중복 체크가 제대로 되지 않아 불필요한 연산 과정이 발생하고, 더불어 불필요하게 isChecked 배열을 많이 생성하게 됨 -> 메모리 초과 발생
// https://www.acmicpc.net/source/45683774

// 풀이 4 - 성공
// BFS 함수 전체에 대해 isChecked 배열 생성
// isChecked[k][n] : K번째 연산에서 등장한 n이라는 숫자에 대한 중복 체크
// 숫자의 자릿수 교환은 int형을 그대로 받아서 각 자릿수에 해당하는 숫자를 빼고 더하여 구현
// 문자 배열로 변환하여 구현도 가능, 구현상 더 편의, 시간은 약간 더 걸림, 문제 풀이 시간에는 문제 없음

// 다른 풀이 1
// 연산 시 중복 체크 배열을 다르게 처리
// 1차원 배열로 생성, 큐에 원소를 넣고 빼는 while 반복을 k번 반복, 연산 반복 시마다 중복 체크 배열 초기화
// https://github.com/shy625/dont-stop-the-camera-study/blob/main/JiHwanYoon/Main_1039_교환.java