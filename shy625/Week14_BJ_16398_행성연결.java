/**
 * BJ 16398 행성 연결
 * @ prob : https://www.acmicpc.net/problem/16398
 * @ level : G4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week14_BJ_16398_행성연결 {

    static class Node implements Comparable<Node> {
        int planet;
        int cost;

        Node(int planet, int cost) {
            this.planet = planet;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());    // 행성의 개수
        int[][] costs = new int[N][N];      // 행성 간 연결 비용

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Prim Algorithm
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N];   // 각 행성의 MST 포함 여부
        int count = 0;      // MST에 포함된 행성 개수
        long costSum = 0;   // 최소 연결 비용

        pq.offer(new Node(0, 0));   // 0번 행성부터 시작
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            // 이미 MST에 포함된 행성이면 skip
            if (isVisited[cur.planet]) {
                continue;
            }
            // 새로 MST에 포함된 행성에 대한 처리
            isVisited[cur.planet] = true;
            costSum += cur.cost;
            if (++count == N) {
                break;
            }
            // 새로 포함된 행성에서 연결되는 간선들 추가
            for (int i = 0; i < N; i++) {
                if (!isVisited[i]) {
                    pq.offer(new Node(i, costs[cur.planet][i]));
                }
            }
        }

        System.out.println(costSum);

        br.close();
    }
}

// 풀이 1
// PriorityQueue를 이용한 Prim 알고리즘
// MST를 구하는 문제
// 주어지는 그래프가 완전 그래프로 간선의 수가 많기 때문에 Kruskal이 아닌 Prim을 사용

// 다른 풀이 1
// PriorityQueue를 사용하지 않은 Prim 알고리즘
// 모든 정점이 서로 연결되어 있는 완전 그래프이기 때문에 PQ를 사용한 것보다 사용하지 않는 방법이 조금 더 빠름
// PQ를 사용하지 않은 방법은 딱 정점의 수인 N번만 돌면 되지만
// PQ를 사용하면 이미 Spanning Tree에 포함된 정점도 비용이 최소라면 뽑힐 수 있어 N번 이상을 돌게 되는데
// 완전 그래프일수록 PQ에 들어가는 원소의 개수가 많아지기 때문에 그만큼 더 많이 반복을 돌게 되기 때문이다.
// https://www.acmicpc.net/source/30057972