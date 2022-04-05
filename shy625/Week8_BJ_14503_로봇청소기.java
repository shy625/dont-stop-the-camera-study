/**
 * BJ 14503 로봇청소기
 * @ prob : https://www.acmicpc.net/problem/14503
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week8_BJ_14503_로봇청소기 {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int R, C;
    static int[][] map;
    static int count;
    static boolean on;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 입력 초기화
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 초기화 완료

        on = true;      // 청소기 전원 on
        clean(r, c, d);     // 청소 시작
        System.out.println(count);

        br.close();
    }

    // dfs
    private static void clean(int r, int c, int d) {
        // 청소기 전원 off
        if(!on) {
            return;
        }
        // 이동한 곳이 청소 가능한 곳이면 청소
        if(map[r][c] == 0) {
            map[r][c] = 2;
            count++;
        }
        // 현재 방향의 왼쪽부터 차례대로 청소 가능한 곳인지 확인
        for(int i = 1; i <= 4; i++) {
            int nd = d-i >= 0 ? d-i : d+4-i;
            int nr = r + dr[nd];
            int nc = c + dc[nd];
            if(isInOfBound(nr, nc) && map[nr][nc] == 0) {   // 청소 가능한 곳이면 이동
                clean(nr, nc, nd);
            }
        }
        // 4방향 모두 청소 불가능하면 현재 방향의 뒤로 이동
        int bd = d-2 >= 0 ? d-2 : d+2;
        int br = r + dr[bd];
        int bc = c + dc[bd];
        if(map[br][bc] != 1) {  // 뒤쪽이 벽이 아니면 이동
            clean(br, bc, d);
        } else {    // 뒤쪽이 벽이면 전원 off
            on = false;
            return;
        }
    }

    private static boolean isInOfBound(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
