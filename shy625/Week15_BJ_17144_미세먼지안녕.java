/**
 * BJ 17144 미세먼지 안녕
 * @ prob : https://www.acmicpc.net/problem/17144
 * @ level : G4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week15_BJ_17144_미세먼지안녕 {

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] room = new int[R][C];   // 방 좌표
        int cleanerPos = 0;     // 공기청정기의 윗부분 위치
        int totalDust = 0;      // 현재 남은 전체 미세먼지양

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                room[r][c] = Integer.parseInt(st.nextToken());
                if (room[r][c] == -1) {     // 공기청정기 위치 저장
                    if (cleanerPos == 0) {
                        cleanerPos = r;
                    }
                    room[r][c] = 0;     // 공기청정기 위치의 미세먼지 0으로 세팅
                } else {
                    totalDust += room[r][c];
                }
            }
        }
        
        for (int t = 0; t < T; t++) {   // 주어진 시간만큼 반복
            room = spreadDust(room, cleanerPos);    // 미세먼지 확산
            totalDust -= cleanAir(room, cleanerPos);    // 공기청정기 작동
        }

        System.out.println(totalDust);

        br.close();
    }
    
    // 미세먼지 확산
    private static int[][] spreadDust(int[][] room, int cleanerPos) {
        int[][] tmp = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int dust = room[r][c];
                if (dust > 0) {     // 5 미만이면 어차피 확산이 일어나지 않으니 dust > 4 로 했어도 될듯
                    int spread = dust / 5;  // 확산되는 미세먼지 양
                    for (int d = 0; d < 4; d++) {   // 4방향
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        // 확산 가능 여부 체크 - 배열 범위 & 공기청정기 위치 체크
                        if (isInOfBound(nr, nc) && !isCleaner(nr, nc, cleanerPos)) {
                            tmp[nr][nc] += spread;
                            dust -= spread;
                        }
                    }
                    tmp[r][c] += dust;  // 확산되고 남은 미세먼지 원위치에 저장
                }
            }
        }
        return tmp;
    }

    // 공기청정기 작동
    private static int cleanAir(int[][] room, int cleanerPos) {
        int removeDust = 0;     // 제거된 미세먼지양

        // 위쪽 영역
        int upper = cleanerPos;
        removeDust += room[upper - 1][0];
        room[upper - 1][0] = 0;
        for (int r = upper - 1; r >= 0; r--) {  // 아래로
            room[r + 1][0] = room[r][0];
        }
        for (int c = 1; c < C; c++) {   // 왼쪽으로
            room[0][c - 1] = room[0][c];
        }
        for (int r = 1; r <= upper; r++) {  // 위로
            room[r - 1][C - 1] = room[r][C - 1];
        }
        for (int c = C - 2; c >= 0; c--) {  // 오른쪽으로
            room[upper][c + 1] = room[upper][c];
        }

        // 아래쪽 영역
        int lower = cleanerPos + 1;
        removeDust += room[lower + 1][0];
        room[lower + 1][0] = 0;
        for (int r = lower + 1; r < R; r++) {   // 위로
            room[r - 1][0] = room[r][0];
        }
        for (int c = 1; c < C; c++) {   // 왼쪽으로
            room[R - 1][c - 1] = room[R - 1][c];
        }
        for (int r = R - 2; r >= lower; r--) {  // 아래로
            room[r + 1][C - 1] = room[r][C - 1];
        }
        for (int c = C - 2; c >= 0; c--) {  // 오른쪽으로
            room[lower][c + 1] = room[lower][c];
        }

        return removeDust;
    }

    private static boolean isInOfBound(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static boolean isCleaner(int r, int c, int cleanerPos) {
        return c == 0 && (r == cleanerPos || r == cleanerPos + 1);
    }
}

// 풀이 1
// 매 시간마다 1. 미세먼지 확산 2. 공기청정기 작동 의 과정을 진핸
// 미세먼지 확산은 기존 배열에서 그대로 확산을 수행하면
// 현재 시간에 존재하는 미세먼지의 위치와 값들이 변경되어 서로 영향이 있을 수 있으므로
// 임시 배열을 생성하여 기존 배열의 값은 변경하지 않고, 미세먼지가 확산되며 변경되는 값은 임시 배열에 업데이트한다.
// 이후 확산이 완료되면 임시 배열을 기존 배열에 덮어씌운다.
// 공기청정기 작동은 위, 아래로 나누어 배열의 테두리 값을 시계 또는 반시계 방향으로 돌리면 된다.
// 이떄 진행하는 방향으로 대입하며 이동시키는 것이 아니라 진행방향의 마지막에서부터 값을 대입하며 시작부분으로 올라오는 것이 편리하다.