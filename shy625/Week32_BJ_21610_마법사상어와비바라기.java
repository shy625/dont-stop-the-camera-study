/**
 * BJ 21610 마법사 상어와 비바라기
 * @ prob : https://www.acmicpc.net/problem/2630
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week32_BJ_21610_마법사상어와비바라기 {

    static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
    
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] isCloud = new boolean[N][N];
        isCloud[N - 1][0] = true;
        isCloud[N - 1][1] = true;
        isCloud[N - 2][0] = true;
        isCloud[N - 2][1] = true;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            isCloud = moveCloud(isCloud, d, s);
            rain(map, isCloud);
            copyWater(map, isCloud);
            isCloud = makeCloud(map, isCloud);
        }

        int amount = sumWater(map);
        System.out.println(amount);

        br.close();
    }
    
    private static boolean[][] moveCloud(boolean[][] beforeCloud, int d, int s) {
        boolean[][] afterCloud = new boolean[N][N];
        int ns = s % N;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (beforeCloud[r][c]) {
                    int nr = r + dr[d] * ns;
                    if (nr >= N) {
                        nr -= N;
                    } else if (nr < 0) {
                        nr += N;
                    }
                    int nc = c + dc[d] * ns;
                    if (nc >= N) {
                        nc -= N;
                    } else if (nc < 0) {
                        nc += N;
                    }
                    afterCloud[nr][nc] = true;
                }
            }
        }

        return afterCloud;
    }

    private static void rain(int[][] map, boolean[][] isCloud) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (isCloud[r][c]) {
                    map[r][c]++;
                }
            }
        }
    }

    private static void copyWater(int[][] map, boolean[][] isCloud) {
        int[] ddr = { -1, -1, 1, 1 };
        int[] ddc = { -1, 1, 1, -1 };

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (isCloud[r][c]) {
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + ddr[d];
                        int nc = c + ddc[d];
                        if (isInOfBound(nr, nc) && map[nr][nc] > 0) {
                            count++;
                        }
                    }
                    map[r][c] += count;
                }
            }
        }
    }

    private static boolean isInOfBound(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static boolean[][] makeCloud(int[][] map, boolean[][] oldCloud) {
        boolean[][] newCloud = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!oldCloud[r][c] && map[r][c] >= 2) {
                    map[r][c] -= 2;
                    newCloud[r][c] = true;
                }
            }
        }
        return newCloud;
    }

    private static int sumWater(int[][] map) {
        int amount = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                amount += map[r][c];
            }
        }
        return amount;
    }

}
