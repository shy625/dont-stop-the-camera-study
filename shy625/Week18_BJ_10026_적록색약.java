/**
 * BJ 10026 적록색약
 * @ prob : https://www.acmicpc.net/problem/10026
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Week18_BJ_10026_적록색약 {

    private static int[] dr = { 1, 0, -1, 0 };
    private static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[][] picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            String inputStr = br.readLine();
            picture[i] = inputStr.toCharArray();
        }

        // 정상의 경우 BFS 이용하여 구역 세기
        int normalRegionCnt = 0;
        boolean[][] isNormalVisited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!isNormalVisited[r][c]) {
                    normalRegionCnt++;
                    countRegion(picture, r, c, isNormalVisited);
                }
            }
        }

        // 적록색약 처리를 위해 R -> G로 변경
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (picture[r][c] == 'R') {
                    picture[r][c] = 'G';
                }
            }
        }

        // 적록색약의 경우 BFS 이용하여 구역 세기
        int abnormalRegionCnt = 0;
        boolean[][] isAbnormalVisited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!isAbnormalVisited[r][c]) {
                    abnormalRegionCnt++;
                    countRegion(picture, r, c, isAbnormalVisited);
                }
            }
        }

        System.out.println(normalRegionCnt + " " + abnormalRegionCnt);

        br.close();
    }
    
    private static void countRegion(char[][] picture, int r, int c, boolean[][] isVisited) {
        int n = picture.length;
        char color = picture[r][c];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] { r, c });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (isInOfBound(n, nr, nc) && !isVisited[nr][nc]
                        && picture[nr][nc] == color) {      // 색깔이 같은 경우에만 진행 가능 (같은 구역)
                    isVisited[nr][nc] = true;
                    q.offer(new int[] { nr, nc });
                }
            }
        }
    }

    private static boolean isInOfBound(int n, int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}

// 풀이 1
// BFS 또는 DFS를 이용하여 그룹 개수 확인
// 방문 처리가 되어 있지 않다면 해당 위치를 시작점으로 BFS 시작
// BFS를 돌면서 같은 그룹으로 묶인 위치는 모두 방문 처리
// 다른 모든 위치에 대해서도 동일하게 반복
// 정상 먼저 확인 후, R와 G를 동일한 값으로 변환하여 적록색약이 보는 상태를 만든 후에 다시 BFS를 사용하여 개수 확인