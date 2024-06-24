/**
 * BJ 19539 사과나무
 * @ prob : https://www.acmicpc.net/problem/19539
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week15_BJ_19539_사과나무 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);     // 오름차순 정렬

        boolean isAvailable = false;    // 가능 여부
        for (int i = 0; i < N; i++) {
            isAvailable = false;

            if (trees[i] >= 3) {    // 우선 3으로 줄 수 있는만큼 다 줌
                trees[i] %= 3;
            }

            if (trees[i] == 2) {    // 3으로 채우고 남은 높이가 2인 경우
                trees[i] = 0;   // 2를 채움
                for (int j = i + 1; j < N; j++) {   // 남은 나무들 중 그 다음으로 작은 나무에 1 사용
                    if (trees[j] >= 1) {
                        trees[j] -= 1;
                        isAvailable = true;     // 1을 마저 사용했다면 가능 처리
                        break;
                    }
                }
            } else if (trees[i] == 1) {     // 3으로 채우고 남은 높이가 1인 경우
                trees[i] = 0;   // 1을 채움
                for (int j = i + 1; j < N; j++) {   // 남은 나무들 중 그 다음으로 작은 나무에 1 사용
                    if (trees[j] >= 2) {
                        trees[j] -= 2;
                        isAvailable = true;     // 2를 마저 사용했다면 가능 처리
                        break;
                    }
                }
            } else if (trees[i] == 0) {     // 3으로 완성된다면 가능 처리
                isAvailable = true;
            }

            if (!isAvailable) {     // 남은 1 또는 2를 사용하지 못했다면 실패 처리
                break;
            }
        }

        System.out.println(isAvailable ? "YES" : "NO");

        br.close();
    }
}

// 풀이 1
// 목표 높이가 작은 나무부터 완성시켰을 때 모든 나무가 가능한지 확인
// 목표 높이가 작은 순서대로 정렬
// 작은 것부터 3으로 나머지 연산 수행
// 결과가 2면 2짜리 물뿌리개를 써서 0으로 만들고 (목표 달성) 1짜리 물뿌리개는 1짜리를 쓸 수 있는 그 다음으로 작은 나무에 사용
// 결과가 1이면 1짜리 물뿌리개를 써서 0으로 만들고 (목표 달성) 2짜리 물뿌리개는 2짜리를 쓸 수 있는 그 다음으로 작은 나무에 사용
// 만약 현재 나무의 목표를 달성하고 남은 1 또는 2짜리 물뿌리개를 쓸 수 있는 나무가 없다면 실패
// 모든 N개 나무에 대해 반복 돌때까지 실패하는 경우가 없다면 성공

// 다른 풀이 1
// https://xkdlaldfjtnl.tistory.com/66