/**
 * BJ 16139 인간 컴퓨터 상호작용
 * @ prob : https://www.acmicpc.net/problem/16139
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week14_BJ_16139_인간컴퓨터상호작용 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();

        int[][] cumSum = new int[26][S.length() + 1];   // 누적합 배열, 계산의 편의를 위해 idx 1부터 시작
        for (int i = 1; i <= S.length(); i++) {
            for (int j = 0; j < 26; j++) {
                cumSum[j][i] = cumSum[j][i - 1]; // 이전 idx 값들 모두 저장
            }
            char ch = S.charAt(i - 1);
            cumSum[ch - 'a'][i]++;  // 이번에 나온 알파벳 값에 대해서 +1
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            char x = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int count = cumSum[x - 'a'][r + 1] - cumSum[x - 'a'][l];    // l부터 r까지 알파벳 등장횟수(둘 다 포함)
            sb.append(count).append(System.lineSeparator());
        }
        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// 누적합 이용
// cumSum[알파벳 a~z][문자열 길이 + 1]
// cumSum[i][j] : j idx까지의 각 알파벳들의 누적 등장횟수를 저장