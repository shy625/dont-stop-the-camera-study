import java.io.*;
import java.util.*;

public class Week21_BJ_1039_교환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);

        int result = N;

        if (N < 10 || N < 100 && N % 10 == 0) {
            result = -1;
        } else {
            // for (int k = 0; k < K; k++) {
            //     int max = 0;
            //     char[] nCharArr = String.valueOf(result).toCharArray();
            //     for (int i = 0; i < nCharArr.length; i++) {
            //         for (int j = i + 1; j < nCharArr.length; j++) {
            //             if (i == 0 && nCharArr[j] == '0') {
            //                 continue;
            //             }
            //             char[] tmp = nCharArr.clone();
            //             tmp[i] = nCharArr[j];
            //             tmp[j] = nCharArr[i];
            //             max = Math.max(max, Integer.parseInt(String.valueOf(tmp)));
            //         }
            //     }
            //     result = max;
            // }
            result = bfs(N, K);
        }

        System.out.println(result);

        br.close();
    }

    private static int bfs(int n, int k) {
        Queue<int[]> q = new LinkedList<>();
        int max = 0;
        q.offer(new int[] { n, 0 });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[1] == k) {
                max = Math.max(max, cur[0]);
            } else {
                char[] charArr = String.valueOf(cur[0]).toCharArray();
                boolean[] isChecked = new boolean[1000001];
                for (int i = 0; i < charArr.length; i++) {
                    for (int j = i + 1; j < charArr.length; j++) {
                        if (i == 0 && charArr[j] == '0') {
                            continue;
                        }
                        char[] tmp = charArr.clone();
                        tmp[i] = charArr[j];
                        tmp[j] = charArr[i];
                        int next = Integer.parseInt(String.valueOf(tmp));
                        if (!isChecked[next]) {
                            q.offer(new int[] { next, cur[1] + 1 });
                        }
                    }
                }
            }
        }
        return max;
    }
}
