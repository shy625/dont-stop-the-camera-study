/**
 * BJ 1099 알 수 없는 문장
 * @ prob : https://www.acmicpc.net/problem/1099
 * @ level : G4
 */

import java.io.*;
import java.util.*;

public class Week29_BJ_1099_알수없는문장 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sentence = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // DP 배열 선언 및 초기화
        int[] dp = new int[sentence.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 시작값 초기화
        for (int i = 0; i < N; i++) {
            if (words[i].length() <= sentence.length()) {
                int cost = checkConversionCost(words[i], sentence.substring(0, words[i].length()));
                if (cost >= 0) {
                    dp[words[i].length() - 1] = Math.min(dp[words[i].length() - 1], cost);
                }
            }
        }

        for (int i = 0; i < dp.length - 1; i++) {
            // 해당 idx까지 변환 불가능한 경우 제외
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            // 0 ~ i까지 변환 가능한 경우에 대해서 진행
            int beginIdx = i + 1;   // 변환 완료된 다음 위치부터 시작
            for (int j = 0; j < N; j++) {
                if (beginIdx + words[j].length() <= sentence.length()) {
                    int cost = checkConversionCost(words[j],
                            sentence.substring(beginIdx, beginIdx + words[j].length()));
                    if (cost >= 0) { // 변환 가능한 경우
                        // 변환되는 단어의 마지막 위치의 DP 배열에 최소 변환 비용 저장
                        int dpIdx = beginIdx + words[j].length() - 1;
                        dp[dpIdx] = Math.min(dp[dpIdx], dp[i] + cost);
                    }
                }
            }
        }
        
        System.out.println(dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1]);

        br.close();
    }

    // 두 단어의 변환 비용 확인
    private static int checkConversionCost(String origin, String result) {
        // 단어의 종류와 개수가 같은지 확인
        int[] alphabetCounts = new int[26];
        for (int i = 0; i < origin.length(); i++) {
            alphabetCounts[origin.charAt(i) - 'a']++;
            alphabetCounts[result.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (alphabetCounts[i] != 0) {
                return -1;  // 변경 불가 시 -1 반환
            }
        }

        // 같다면 위치가 다른 개수 확인
        int cost = 0;
        for (int i = 0; i < origin.length(); i++) {
            if (origin.charAt(i) != result.charAt(i)) {
                cost++;
            }
        }
        return cost;
    }
}

// 풀이 1 - 메모리 초과
// 백트래킹 사용
// https://www.acmicpc.net/source/48679608

// 풀이 2
// DP 사용
// dp[i] : 주어진 문장의 처음부터 해당 idx까지의 최소 변환 비용을 저장
// DP 배열 초기화 후 처음에 변환 가능한 단어들에 대한 비용을 각 위치에 시작값으로 초기화
// 0 ~ 해당 위치까지 단어 변환이 완료된 부분부터 각 단어에 대해 변환 가능 여부 체크
// 변환이 가능하면 기존 변환 비용과 해당 단어로 변환 시 최종 변환 비용 중 최솟값을 변환되는 단어의 마지막 idx에 저장
// DP 값 업데이트 완료 후 마지막 idx 값 확인