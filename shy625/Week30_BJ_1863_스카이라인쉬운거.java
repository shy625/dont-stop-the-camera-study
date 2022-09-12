/**
 * BJ 1863 스카이라인 쉬운 거
 * @ prob : https://www.acmicpc.net/problem/1863
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week30_BJ_1863_스카이라인쉬운거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[][] skylines = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            skylines[i][0] = x;
            skylines[i][1] = y;
        }

        Stack<Integer> stack = new Stack<>();
        int buildingCount = 0;

        stack.push(0);  // 초기값 세팅
        for (int i = 0; i < N; i++) {
            int y = skylines[i][1];
            // 최소 빌딩 개수는 높이가 낮아질 때 결정
            // 변경된 높이가 이전 높이보다 높으면 stack에 추가
            if (stack.peek() < y) {
                stack.push(y);
            } else { // 변경된 높이가 이전 높이보다 낮으면 빌딩 개수 결정
                // 변경된 높이보다 높은 이전 빌딩들을 제거 및 빌딩 개수 증가
                while (stack.peek() > y) {
                    stack.pop();
                    buildingCount++;
                }
                // 높은 빌딩들을 제거 후 변경된 높이가 더 높다면 stack에 추가
                if (stack.peek() < y) {
                    stack.push(y);
                }
            }
        }
        // stack에 아직 빌딩들이 남아있다면 체크
        while (stack.peek() > 0) {
            stack.pop();
            buildingCount++;
        }

        System.out.println(buildingCount);

        br.close();
    }
}

// 풀이 1
// 최소 빌딩 개수는 높이가 낮아질 때 결정
// 변경되는 빌딩 높이들을 stack에 저장하고
// 변경된 높이를 top과 비교하여 stack에 추가 또는 제거