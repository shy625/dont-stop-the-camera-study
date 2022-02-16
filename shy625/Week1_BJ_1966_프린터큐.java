/**
 * BJ 1966 프린터 큐
 * @ prob : https://www.acmicpc.net/problem/1966
 * @ level : S3
 * @ Blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Week1_BJ_1966_프린터큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);

            input = br.readLine().split(" ");
            
            Queue<int[]> printQ = new ArrayDeque<>();   // 인쇄 큐
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    // 중요도 큐

            for(int i = 0; i < N; i++) {
                printQ.offer(new int[]{Integer.parseInt(input[i]), i});     // 0 : 중요도, 1: 최초 위치
                pq.offer(Integer.parseInt(input[i]));
            }

            int printSeq = 1;  // 인쇄 순서
            while(true) {
                if(printQ.peek()[0] == pq.peek()) {     // 중요도가 가장 높으면 인쇄
                    if(printQ.poll()[1] == M) {     // 궁금한 문서면 인쇄 순서 출력 후 break
                        System.out.println(printSeq);
                        break;
                    }
                    pq.poll();
                    printSeq++;
                } else {    // 더 높은 중요도의 문서가 있는 경우
                    printQ.offer(printQ.poll());
                }
            }
        }

        br.close();
    }
}

// 다른 풀이 1
// 처음 배열로 받은 상태에서 그대로 현재 위치값만 옮겨가면서 체크, 중요도는 배열을 정렬하고 뺄 때마다 배열의 최대 idx를 줄임
// https://www.acmicpc.net/source/38405491