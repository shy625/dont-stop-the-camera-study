import java.io.*;
import java.util.*;

public class Week27_BJ_1022_소용돌이예쁘게출력하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int maxDist = Math.max(Math.abs(r1), Math.max(Math.abs(c1), Math.max(Math.abs(r2), Math.abs(c2))));
        int size = maxDist * 2 + 1;
        int[][] square = new int[size][size];
        int count = 1;
        square[maxDist][maxDist] = count++;
        for (int dist = 1; dist <= maxDist; dist++) {
            for (int i = -dist + 1; i <= dist; i++) {
                square[maxDist - i][maxDist + dist] = count++;
            }
            for (int i = -dist + 1; i <= dist; i++) {
                square[maxDist - dist][maxDist - i] = count++;
            }
            for (int i = -dist + 1; i <= dist; i++) {
                square[maxDist + i][maxDist - dist] = count++;
            }
            for (int i = -dist + 1; i <= dist; i++) {
                square[maxDist + dist][maxDist + i] = count++;
            }
        }

        count--;
        int maxDigitCount = (int) Math.log10(count) + 1;
        // StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int r = r1 + maxDist; r <= r2 + maxDist; r++) {
            for (int c = c1 + maxDist; c <= c2 + maxDist; c++) {
                int digitCount = (int) Math.log10(square[r][c]) + 1;
                if (digitCount < maxDigitCount) {
                    for (int i = 0; i < maxDigitCount - digitCount; i++) {
                        // sb.append(" ");
                        bw.write(" ");
                    }
                }
                // sb.append(square[r][c] + " ");
                bw.write(square[r][c] + " ");
            }
            // sb.append(System.lineSeparator());
            bw.write(System.lineSeparator());
        }

        bw.flush();
        // System.out.print(sb.toString());

        br.close();
        bw.close();
    }
}
