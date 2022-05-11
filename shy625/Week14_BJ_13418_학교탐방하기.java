/**
 * BJ 13418 학교 탐방하기
 * @ prob : https://www.acmicpc.net/problem/13418
 * @ level : G3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Week14_BJ_13418_학교탐방하기 {

    static class Edge implements Comparable<Edge> {
        int vertex1;
        int vertex2;
        int type;   // 0 : 오르막길, 1 : 내리막길

        Edge(int vertex1, int vertex2, int type) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.type = type;
        }

        @Override
        public int compareTo(Edge o) {
            return this.type - o.type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(vertex1, vertex2, type));
        }

        int max = kruskal(N + 1, edgeList, 0);  // 오르막 먼저 선택
        int min = kruskal(N + 1, edgeList, 1);  // 내리막 먼저 선택

        System.out.println(max * max - min * min);

        br.close();
    }

    private static void makeSet(int[] parents) {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    private static int find(int[] parents, int v) {
        if (parents[v] == v) {
            return v;
        } else {
            return parents[v] = find(parents, parents[v]);
        }
    }

    private static boolean union(int[] parents, int v1, int v2) {
        int root1 = find(parents, v1);
        int root2 = find(parents, v2);
        if (root1 == root2) {
            return false;
        } else {
            parents[root2] = root1;
            return true;
        }
    }

    private static int kruskal(int n, List<Edge> edgeList, int flag) {
        int[] parents = new int[n];
        PriorityQueue<Edge> pq;
        if (flag == 0) {    // 오르막길 우선
            pq = new PriorityQueue<>();
        } else {    // 내리막길 우선
            pq = new PriorityQueue<>(Collections.reverseOrder());
        }

        makeSet(parents);
        for (int i = 0; i < edgeList.size(); i++) {
            pq.offer(edgeList.get(i));
        }

        int count = 1, upCount = 0;     // upCount : MST에 포함된 오르막길 개수
        while (count < n) {     // N개를 선택하면 종료
            Edge cur = pq.poll();
            if (union(parents, cur.vertex1, cur.vertex2)) {
                count++;
                if (cur.type == 0) {
                    upCount++;
                }
            }
        }

        return upCount;
    }
}

// 풀이 1
// MST - Kruskal 알고리즘 사용
// 간선 리스트 형태로 들어온 입력을 그대로 사용하기 좋은 Kruskal 알고리즘 사용
// 길이 내리막인지 오르막인지 나타내는 type 값을 간선의 비용으로 하여 Minimum ST와 Maximum ST를 구함
// 각각의 Spanning Tree에 포함된 오르막의 개수를 가지고 결과를 구해서 출력