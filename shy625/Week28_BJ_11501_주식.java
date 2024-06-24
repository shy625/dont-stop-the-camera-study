/**
 * BJ 11501 주식
 * @ prob : https://www.acmicpc.net/problem/11501
 * @ level : S2
 */

import java.io.*;
import java.util.*;

public class Week28_BJ_11501_주식 {

    static class Node implements Comparable<Node> {
        int idx;
        int stock;

        Node(int idx, int stock) {
            this.idx = idx;
            this.stock = stock;
        }

        // 주가가 가장 높은 것 중 가장 앞에 있는 것
        @Override
        public int compareTo(Node o) {
            if (this.stock == o.stock) {
                return Integer.compare(this.idx, o.idx);
            }
            return Integer.compare(o.stock, this.stock);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int day = Integer.parseInt(br.readLine());
            int[] stocks = new int[day];
            PriorityQueue<Node> pq = new PriorityQueue<>(); // 최대 주가를 찾기 위한 pq

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < day; i++) {
                stocks[i] = Integer.parseInt(st.nextToken());
                pq.offer(new Node(i, stocks[i]));
            }

            long profitSum = 0L;
            long purchaseCost = 0L;
            int holdingCount = 0;
            int curDay = 0;

            while (curDay < day) {
                Node node = pq.poll();
                if (node.idx < curDay) {    // 현재 날짜 이전이면 다음 노드 확인
                    continue;
                }
                while (curDay < node.idx) { // 주가가 최댓값이 날이 될때까지 모든 주식 구매
                    purchaseCost += stocks[curDay];
                    holdingCount++;
                    curDay++;
                }
                // 최댓값인 날이 되면 구매한 모든 주식 처분
                profitSum += node.stock * holdingCount - purchaseCost;
                purchaseCost = 0L;
                holdingCount = 0;
                curDay++;
            }

            sb.append(profitSum);
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// 1. 주가가 가장 비싼 날 찾기
// 2. 해당 날짜까지 모든 주식 구매
// 3. 해당 날짜가 되면 구매한 주식 모두 판매
// 4. 남은 날짜 중 주가가 가장 비싼 날 찾기
// 5. 위 로직 반복
// 주가가 가장 비싼 날을 찾는데 PriorityQueue 사용
// O(nlogn)에 풀이

// 다른 풀이 1
// 가장 마지막 날부터 역으로 진행
// 주가의 max 값 업데이트
// max 값과 현재 주가의 차를 수익으로 계산 -> 이전에 구매한 주식들을 최대 주가일 때 수익 실현
// O(n)에 풀이
// https://www.acmicpc.net/source/48306203

// long profitSum = 0L;
// int max = 0;
// for (int i = day - 1; i >= 0; i--) {
//     max = Math.max(max, stocks[i]);
//     profitSum += max - stocks[i];
// }
