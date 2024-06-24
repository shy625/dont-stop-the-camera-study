package BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2186_문자판 {
    static int N, M, K;
    static char[][] map;
    static String word;

    static int[][][] dp;
    static int path = 0;

    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            str = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        word = bf.readLine();
        dp = new int[N][M][word.length()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < word.length(); k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (word.charAt(0) == map[i][j]) {
                    // System.out.println("find index 0 : " + i + " " + j);
                    findNextWord(i, j, 0);
                    path += dp[i][j][0];
                }
            }
        }

        System.out.println(path);
    }

    public static void findNextWord(int r, int c, int w) { // w = word index
        if (w == word.length() - 1) { // 마지막 문자열까지 찾음!
            dp[r][c][w] = 1;
            return;
        }
        if (dp[r][c][w] != -1) { // 이미 방문함
            return;
        }
        dp[r][c][w] = 0; // 첫 방문

        for (int i = 0; i < 4; i++) { // 상하좌우
            for (int j = 1; j <= K; j++) { // 여러번
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;
                if (checkedMap(nr, nc)) {
                    if (map[nr][nc] == word.charAt(w + 1)) {
                        // System.out.println("find index " + w + " : " + nr + " " + nc);
                        findNextWord(nr, nc, w + 1);
                        dp[r][c][w] += dp[nr][nc][w + 1];
                    }

                }
            }
        }
    }

    public static boolean checkedMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
