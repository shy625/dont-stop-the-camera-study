/**
 * BJ 16234 인구 이동
 * @ prob : https://www.acmicpc.net/problem/16234
 * @ level : G5
 */

// 80%에서 시간초과 Fail

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Week5_BJ_16234_인구이동 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, L, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;    // 인구 이동 진행 날짜
        while(true) {
            int[][] unions = new int[N][N];     // 연합 표시 배열
            List<Integer> populations = new ArrayList<>();      // 각 연합의 인구수
            int num = 0;    // 연합 번호. 1부터 시작

            populations.add(-1);    // 연합은 1부터 시작. 계산 편의를 위해 index 0에 빈 값 할당

            // 각 칸에 대해 연합 확인
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(unions[r][c] != 0) {     // 이미 연합이 있으면 skip
                        continue;
                    }

                    // 현재 칸 주변에 연합할 수 있는 칸이 있는지 확인
                    boolean needBFS = false;
                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(isInOfBound(nr, nc) && unions[nr][nc] == 0) {
                            int gap = Math.abs(map[r][c] - map[nr][nc]);
                            if(L <= gap && gap <= R) {
                                needBFS = true;
                                break;
                            }
                        }
                    }

                    if(needBFS) {   // 연합 확인을 위한 BFS 진행
                        int p = bfs(map, unions, r, c, ++num);
                        populations.add(p);     
                    } else {    // 아니면 현재 칸만 연합
                        unions[r][c] = ++num;
                        populations.add(map[r][c]);
                    }
                }
            }

            // 연합수가 전체 칸 수와 같으면 인구 이동 종료
            if(num == N * N) {
                break;
            }

            // 연합 인구에 따라 각 칸의 인구수 재배치
            for(int i = 1; i <= num; i++) {
                for(int r = 0; r < N; r++) {
                    for(int c = 0; c < N; c++) {
                        if(unions[r][c] == i) {
                            map[r][c] = populations.get(i);
                        }
                    }
                }
            }
            
            day++;
        }

        System.out.println(day);

        br.close();
    }

    // 연합 확인을 위한 BFS
    private static int bfs(int[][] map, int[][] unions, int sr, int sc, int num) {
        Queue<int[]> q = new LinkedList<>();
        int sum = 0, count = 0;

        q.offer(new int[]{sr, sc});
        unions[sr][sc] = num;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            sum += map[cur[0]][cur[1]];
            count++;
            for(int d = 0; d < 4; d++) {    // 4방 탐색
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isInOfBound(nr, nc) && unions[nr][nc] == 0) {    // 배열 범위 내 && 정해진 연합이 없는 경우
                    int gap = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);  // 현재 칸과 다음 칸의 인구 차이 계산
                    if(L <= gap && gap <= R) {      // 인구 차이 조건이 맞으면
                        unions[nr][nc] = num;   // 연합에 포함
                        q.offer(new int[]{nr, nc});     // 큐에 추가
                    }
                }
            }
        }
        return sum / count;     // 연합 각 칸의 인구수 반환
    }

    private static boolean isInOfBound(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}

// 풀이 1 - 시간초과
// BFS로 연합 확인 및 연합의 인구수 계산 -> 연합이 없는 모든 칸에 대해서 BFS 진행 -> 연합 설정 완료 후 인구수 재배치 -> 종료될 때까지 반복
// https://www.acmicpc.net/source/40192897
// https://www.acmicpc.net/source/40193270
