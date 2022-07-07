/**
 * BJ 16918 봄버맨
 * @ prob : https://www.acmicpc.net/problem/16918
 * @ level : S1
 */

import java.io.*;

public class Week21_BJ_16918_봄버맨 {

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int R = Integer.parseInt(inputs[0]);
        int C = Integer.parseInt(inputs[1]);
        int N = Integer.parseInt(inputs[2]);

        int[][] map = new int[R][C];    // 0 : 빈칸, 숫자(N) : N초에 터지는 폭탄
        for (int r = 0; r < R; r++) {
            String inputStr = br.readLine();
            for (int c = 0; c < C; c++) {
                if (inputStr.charAt(c) == '.') {
                    map[r][c] = 0;
                } else {
                    map[r][c] = 3;
                }
            }
        }

        // 4초 주기로 반복되는 규칙에 따라 N초 값 변경
        if (N > 1) {
            N %= 4;
            if (N < 2) {
                N += 4;
            }
        }

        if (N > 1) {
            // 2초 후부터 설치 -> 폭발 -> 설치 -> 폭발 ... 반복
            for (int n = 2; n <= N; n++) {
                if (n % 2 == 0) {
                    fillBombs(map, n);
                } else {
                    map = explodeBombs(map, n);
                }
            }
        }

        printMap(map);

        br.close();
    }

    // 빈칸에 폭탄 설치
    private static void fillBombs(int[][] map, int n) {
        int R = map.length;
        int C = map[0].length;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 0) {
                    map[r][c] = n + 3;  // 현채 초로부터 3초 뒤 터지는 폭탄
                }
            }
        }
    }

    // n초에 터지는 폭탄 폭발
    private static int[][] explodeBombs(int[][] map, int n) {
        int R = map.length;
        int C = map[0].length;
        
        // 모든 폭탄은 동시에 폭발
        // 터지기 전 폭탄이 다른 폭탄에 의해 파괴되지 않도록 임시 map을 만들어서 폭발 반영
        int[][] tmp = new int[R][C];
        for (int r = 0; r < R; r++) {
            tmp[r] = map[r].clone();
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == n) {
                    // 자기 자신과 4방향을 빈칸으로 만듦
                    tmp[r][c] = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (isInOfBound(nr, nc, R, C)) {
                            tmp[nr][nc] = 0;
                        }
                    }
                }
            }
        }
        return tmp;
    }

    private static boolean isInOfBound(int r, int c, int R, int C) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static void printMap(int[][] map) {
        int R = map.length;
        int C = map[0].length;
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 0) {
                    sb.append('.');
                } else {
                    sb.append('O');
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());

    }

}

// 풀이 1
// 4초 주기로 일정한 모양이 반복
// 2, 6, 10 ... 초 후 상태 동일
// 3, 7, 11 ... 초 후 상태 동일
// 4, 8, 12 ... 초 후 상태 동일
// 5, 9, 13 ... 초 후 상태 동일
// 규칙에 따라 N초를 1, 2, 3, 4, 5 초 중 하나의 값으로 만들고 해당 초 일 때의 상태를 출력