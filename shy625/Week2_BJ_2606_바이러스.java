/**
 * BJ 2606 바이러스
 * @ prob : https://www.acmicpc.net/problem/2606
 * @ level : S3
 * @ Blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Week2_BJ_2606_바이러스 {

    static int N;   // 컴퓨터 개수
    static int[][] networks;    // 컴퓨터 간 연결 정보
    static int count;   // 감염된 컴퓨터 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        networks = new int[N][N];

        int edgeCount = Integer.parseInt(br.readLine());
        for(int i = 0; i < edgeCount; i++) {
            String[] inputs = br.readLine().split(" ");
            int n1 = Integer.parseInt(inputs[0]) - 1;   // 인텍스 = 컴퓨터 번호 - 1
            int n2 = Integer.parseInt(inputs[1]) - 1;   // 인텍스 = 컴퓨터 번호 - 1
            // 0 : 연결 X, 1 : 연결 O
            networks[n1][n2] = 1;
            networks[n2][n1] = 1;
        }

        // 1번 컴퓨터(0번 인덱스)를 시작점으로 하여 BFS 진행
        bfs(0, new boolean[N]);

        System.out.println(count - 1);  // 1번 컴퓨터 제외한 수

        br.close();
    }

    private static void bfs(int node, boolean[] isVisited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        isVisited[node] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            count++;    // 감염된 컴퓨터 개수 +1
            for(int i = 0; i < N; i++) {
                // 현재 컴퓨터와 i+1 컴퓨터 연결 O && i+1 컴퓨터 방문 X
                if(networks[cur][i] == 1 && !isVisited[i]) {
                    q.offer(i);
                    isVisited[i] = true;    // 방문 처리
                }
            }
        }
    }
}