/**
 * PG 등산코스 정하기
 * @ prob : https://school.programmers.co.kr/learn/courses/30/lessons/118669#
 * @ level : 3
 */

import java.util.*;

class Solution {
    
    private static class Node implements Comparable<Node> {
        int num;
        int intensity;
        
        private Node(int num, int intensity) {
            this.num = num;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.intensity, n.intensity);
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 각 지점의 출입구, 쉼터, 산봉우리 여부 배열
        int[] types = new int[n + 1];
        for (int i = 0; i < gates.length; i++) {
            types[gates[i]] = 1;
        }
        for (int i = 0; i < summits.length; i++) {
            types[summits[i]] = 2;
        }
        
        // 인접리스트
        List<int[]>[] adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 출입구는 나가는 방향만, 산봉우리는 들어오는 방향 설정, 그 외는 양방향
        for (int i = 0; i < paths.length; i++) {
            int v = paths[i][0];
            int w = paths[i][1];
            int weight = paths[i][2];
            if (types[v] == 1 || types[w] == 2) {
                adjList[v].add(new int[] {w, weight});
            } else if (types[v] == 2 || types[w] == 1) {
                adjList[w].add(new int[] {v, weight});
            } else {
                adjList[v].add(new int[] {w, weight});
                adjList[w].add(new int[] {v, weight});
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 출발점으로부터 각 지점까지 가는 경로의 최대 weight 중 최솟값
        int[] intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        // 출입구 모두 시작점 세팅
        for (int i = 0; i < gates.length; i++) {
            intensities[gates[i]] = 0;
            pq.offer(new Node(gates[i], 0));
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            // 이미 저장된 intensity 배열 값보다 큰 경우 제외
            if (cur.intensity > intensities[cur.num]) {
                continue;
            }
            
            for (int i = 0; i < adjList[cur.num].size(); i++) {
                int[] next = adjList[cur.num].get(i);
                // 이전 지점까지의 intensity 값과 다음으로 이동하는 weight 중 큰 값 -> 이동한 경로 중 최댓값
                int maxWeight = Math.max(intensities[cur.num], next[1]);
                // 새로 이동한 곳의 intensity 값보다 작은 경우
                if (intensities[next[0]] > maxWeight) {
                    intensities[next[0]] = maxWeight;
                    pq.offer(new Node(next[0], maxWeight));
                }
            }
        }

        int[] result = new int[] {-1, Integer.MAX_VALUE};
        Arrays.sort(summits);
        for (int i = 0; i < summits.length; i++) {
            if (result[1] > intensities[summits[i]]) {
                result[0] = summits[i];
                result[1] = intensities[summits[i]];
            }
        }
        return result;
    }
}

// 풀이 1 - 시간초과
// DFS
// 각 출발점 -> 각 산봉우리 로 가는 경로를 DFS로 탐색하면서 지나가는 길의 weight 최댓값을 저장
// 목표한 산봉우리가 나오면 그때까지 지나온 경로 중 weight의 최댓값이 전체 중에 최소인지 확인, return
// 시간복잡도를 최대한 줄이기 위해 DFS를 하는 과정에서 출발점이 나오거나, 목표가 아닌 다른 산봉우리가 나오거나, 방문한 곳을 또 방문하는 경우는 진행 X

// 풀이 2
// 다익스트라 응용
// 출발점으로부터 각 노드로 가는 최소경로값 배열을 각 노드로 가는 경로의 intensity 배열로 응용하여 진행
// 시작 가능한 모든 출발점을 처음에 PQ와 intensity 배열에서 초기화하고 진행
// 출발점 또는 산봉우리가 나오면 제외 (또는 애초에 인접그래프에서 출발점은 나가는 화살표만, 산봉우리는 들어오는 화살표만 세팅)
// intensity 배열의 값보다 현재 뽑은 값이 큰 경우는 배열의 업데이트와 관련없는 노드이므로 제외
// 지금까지 나온 weight & 다음 갈 노드의 weight 중 큰 값이 현재 intensity 배열 값보다 작으면 배열 업데이트 및 PQ에 넣기
// https://ksb-dev.tistory.com/75
// https://velog.io/@doforme/프로그래머스-자바-등산-코스-정하기