/**
 * BJ 17396 백도어
 * @ prob : https://www.acmicpc.net/problem/17396
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week25_BJ_17396_백도어 {

    static class Node implements Comparable<Node> {
        int num;
        int time;
        long distance;

        Node(int num, int time, long distance) {
            this.num = num;
            this.time = time;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        boolean[] isAvailables = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(st.nextToken()) == 0) {
                isAvailables[i] = true;
            } else {
                isAvailables[i] = false;
            }
        }
        isAvailables[N - 1] = true;

        List<Node>[] adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, time, 0L));
            adjList[b].add(new Node(a, time, 0L));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] isChecked = new boolean[N];
        long[] minDistances = new long[N];

        Arrays.fill(minDistances, Long.MAX_VALUE);

        pq.offer(new Node(0, 0, 0));
        minDistances[0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (isChecked[cur.num]) {
                continue;
            }
            isChecked[cur.num] = true;

            for (Node node : adjList[cur.num]) {
                if (!isChecked[node.num] && isAvailables[node.num]
                        && minDistances[node.num] > minDistances[cur.num] + node.time) {
                    minDistances[node.num] = minDistances[cur.num] + node.time;
                    pq.offer(new Node(node.num, 0, minDistances[node.num]));
                }
            }
        }

        System.out.println(minDistances[N - 1] == Long.MAX_VALUE ? -1 : minDistances[N - 1]);

        br.close();
    }
}

// 풀이 1
// 목적지로 가는 최단 경로 찾기
// 다익스트라 알고리즘 사용

// 다른 풀이 1
// 다익스트라의 좀 더 정돈된 풀이
// https://www.acmicpc.net/source/37567188