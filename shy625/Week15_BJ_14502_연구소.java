/**
 * BJ 14502 연구소
 * @ prob : https://www.acmicpc.net/problem/14502
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week15_BJ_14502_연구소 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = { 0, 1, 0, -1 };
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기

        int[][] map = new int[N][M];
        List<int[]> emptyList = new ArrayList<>();  // 빈칸 위치를 저장하는 리스트
        List<int[]> virusList = new ArrayList<>();  // 바이러스 위치를 저장하는 리스트
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {   // 빈칸
                    emptyList.add(new int[] { i, j });
                } else if (map[i][j] == 2) {    // 바이러스
                    virusList.add(new int[] { i, j });
                }
            }
        }

        int max = 0; // 안전 영역 크기의 최댓값
        // Brute-Force : 조합
        for (int i = 0; i < emptyList.size(); i++) {
            for (int j = i + 1; j < emptyList.size(); j++) {
                for (int k = j + 1; k < emptyList.size(); k++) {
                    int[][] tmpMap = new int[N][M];     // 벽 및 바이러스 확산 체크를 위한 임시 map 생성
                    for (int l = 0; l < N; l++) {
                        tmpMap[l] = map[l].clone();
                    }
                    // 조합에 의해 벽을 세울 위치로 선택된 좌표 3개
                    int[][] pos = new int[3][2];
                    pos[0] = emptyList.get(i);
                    pos[1] = emptyList.get(j);
                    pos[2] = emptyList.get(k);
                    for (int l = 0; l < 3; l++) {
                        tmpMap[pos[l][0]][pos[l][1]] = 1;   // 벽 세우기
                    }

                    int tmpSafetyCnt = emptyList.size() - 3;
                    for (int l = 0; l < virusList.size(); l++) {    // 각 바이러스에 대해서 bfs(확산) 수행
                        int[] virus = virusList.get(l);
                        tmpSafetyCnt -= bfs(tmpMap, virus[0], virus[1]);    // 해당 바이러스로부터 감염된 개수 제외
                    }
                    max = Math.max(max, tmpSafetyCnt);  // 이번에 세운 벽 위치에 대해 최댓값 체크
                }
            }
        }
        System.out.println(max);

        br.close();
    }
    
    private static int bfs(int[][] map, int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        int turnCnt = 0;    // 빈칸이었다가 바이러스에 감염된 수

        q.offer(new int[] { r, c });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            turnCnt++;
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (isInOfBound(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                    q.offer(new int[] { nr, nc });
                }
            }
        }
        return turnCnt - 1;     // 처음부터 바이러스였던 시작점 제외
    }

    private static boolean isInOfBound(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}

// 풀이 1
// Brute-Force(조합)과 BFS 사용
// 조합을 수행하기 편하도록 빈칸 위치를 ArrayList에 넣어서 1차원으로 변경
// 조합을 이용해 연구소 빈칸에 3개의 벽을 세우는 모든 경우에 대해 바이러스 확산 체크
// 바이러스 확산은 각 바이러스에 대해 BFS를 수행하여 체크
// 바이러스 위치만 미리 ArrayList에 넣어놔서 바이러스 위치 확인을 위해 전체 map 돌지 않음
// 조합의 경우의 수 : N, M은 최대 8 -> 연구소의 전체 칸 수 = 64 -> 64개 중 3개를 선택하는 경우의 수 = 64 * 63 * 62

// 다른 풀이 1
// 조합과 DFS 사용
// 조합을 위한 반복문 사용 시 idx를 0~전체개수 로 진행 -> map에는 그 idx를 다시 2차원용으로 변환
// arr2D[i][j] = arr1D[i * N + j]
// arr1D[i] = arr2D[i / N][i % N]
// https://www.acmicpc.net/source/21403174