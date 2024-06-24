/**
 * BJ 3186 소변기
 * @ prob : https://www.acmicpc.net/problem/3186
 * @ level : S2
 */

import java.io.*;
import java.util.*;

public class Week23_BJ_3186_소변기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[] data = br.readLine().toCharArray();

        boolean isUsing = false;
        int useCount = 0, flushCount = 0;
        for (int i = 0; i < N; i++) {
            if (!isUsing) {     // 사용 중 X
                if (data[i] == '1') {
                    useCount++;
                } else {
                    useCount = 0;
                }
                if (useCount >= K) {    // 사람이 연속해서 K 동안 있으면 사용 중 체크
                    isUsing = true;
                    useCount = 0;
                }
            } else {    // 사용 중 O
                if (data[i] == '0') {
                    flushCount++;
                } else {
                    flushCount = 0;
                }
                if (flushCount >= L) {  // 사람이 연속해서 L 동안 없으면 사용 완료 체크
                    isUsing = false;
                    flushCount = 0;
                    sb.append(i + 1).append(System.lineSeparator());
                }
            }
        }
        if (isUsing) {  // 사용 중인 상태로 데이터가 끝나면 마지막 flush 시간 체크
            sb.append(N + L - flushCount).append(System.lineSeparator());
        }

        System.out.println(sb.length() > 0 ? sb.toString() : "NIKAD");

        br.close();
    }
}

// 풀이 1
// 사용 중 상태와 사용 중이 아닌 상태를 구분하여 연속 카운트 체크
// 사용 중이 아니면 사람이 연속해서 있는 횟수를 카운트
// 사용 중이면 사람이 연속해서 없는 횟수를 카운트
