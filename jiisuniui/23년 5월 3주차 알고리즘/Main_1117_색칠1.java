import java.util.Scanner;

// 위아래 좌표 r이 뒤집혀도 대칭이라 ㄱㅊ
public class Main_1117_색칠1 {
    static int W, H;
    static int f, c;
    static int x1, y1, x2, y2;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        W = scann.nextInt();
        H = scann.nextInt();
        f = scann.nextInt();
        c = scann.nextInt();
        x1 = scann.nextInt();
        y1 = scann.nextInt();
        x2 = scann.nextInt();
        y2 = scann.nextInt();

        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = 1;
            }
        }

        int maxF = Math.max(f, Math.abs(W - f));
        int minF = Math.min(f, Math.abs(W - f));
        if (f != 0) {
            for (int x = 0; x < H; x++) {
                for (int y = 0; y < maxF; y++) {
                    if (y < minF) {
                        map[x][y] = 2;
                    } else {
                        map[x][y] = 1;
                    }
                }
            }
        }

        if (c != 0) {
            for (int x = 0; x < c; x++) {
                for (int y = 0; y < maxF; y++) {
                    map[x][y] = (c + 1) * map[x][y];
                }
            }
        }

        //// ----------------------------------------
        // for (int x = 0; x < H; x++) {
        // for (int y = 0; y < maxF; y++) {
        // System.out.print(fold1[x][y] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println();
        // System.out.println();

        // for (int x = 0; x < c; x++) {
        // for (int y = 0; y < maxF; y++) {
        // System.out.print(fold2[x][y] + " ");
        // }
        // System.out.println();
        // }
        // System.out.println();
        //// ----------------------------------------

        int paint = 0;
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                paint += map[j][i];
            }
        }

        int ans = W * H - paint;
        System.out.println(ans);
    }
}
