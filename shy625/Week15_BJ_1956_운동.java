/**
 * BJ 1956 운동
 * @ prob : https://www.acmicpc.net/problem/1956
 * @ level : G4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week15_BJ_1956_운동 {

    static final int INF = 10_000_000;      // 최대 경로 길이 = 4_000_000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] adjMatrix = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = length;
        }

        // Floyd-Warshall
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }

        int minLengthSum = INF;
        for (int i = 1; i <= V; i++) {
            minLengthSum = Math.min(minLengthSum, adjMatrix[i][i]);
        }
        if (minLengthSum == INF) {
            minLengthSum = -1;
        }

        System.out.println(minLengthSum);

        br.close();
    }
}

// 풀이 1 - 시간초과
// 각 정점을 시작점으로 하여 DFS 수행
// DFS 시작 시 시작점에 대해 방문처리 X - 이후에 2번째 방문되었을 때 방문처리 O
// DFS를 돌다가 현재 정점이 시작점과 같고 방문 처리가 되어 있으면 사이클 성립
// 그때까지의 경로 합을 전체의 최솟값과 비교하여 최솟값 저장
// https://www.acmicpc.net/source/43447454

// private static void dfs(int start, int v, int weightSum, int isVisited) {
//     if (v == start && (isVisited & 1 << v) != 0) {
//         minWeightSum = Math.min(minWeightSum, weightSum);
//         return;
//     }
//     for (int i = 0; i < adjList[v].size(); i++) {
//         Edge e = adjList[v].get(i);
//         if ((isVisited & 1 << e.to) == 0) {
//             dfs(start, e.to, weightSum + e.weight, isVisited | 1 << e.to);
//         }
//     }
// }

// 풀이 2
// Floyd-Warshall 사용
// 플로이드를 이용해 도시의 모든 두 쌍에 대해서 최단 경로 계산
// cycle을 찾아야 하므로 출발점과 도착점이 같은 경우도 포함하여 계산
// 계산 후 adjMatrix[i][i] 중 최소값을 출력