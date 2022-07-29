/**
 * BJ 9519 졸려
 * @ prob : https://www.acmicpc.net/problem/9519
 * @ level : S1
 */

import java.io.*;

public class Week24_BJ_9519_졸려 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());
        String word = br.readLine();

        int iterCount = 0;
        String tmp = word;
        while (true) {
            tmp = counterConvert(tmp);
            iterCount++;
            if (tmp.equals(word)) {
                break;
            }
        }

        int remain = x % iterCount;
        for (int i = 0; i < remain; i++) {
            word = counterConvert(word);
        }

        System.out.println(word);

        br.close();
    }

    private static String counterConvert(String word) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < word.length()) {
            sb.append(word.charAt(idx));
            idx += 2;
        }
        idx--;
        if (word.length() % 2 != 0) {
            idx -= 2;
        }
        while (idx > 0) {
            sb.append(word.charAt(idx));
            idx -= 2;
        }
        return sb.toString();
    }
}

// 풀이 1
// 1. 주어진 단어를 역변환하며 반복 찾기
// 2. 깜박인 횟수 x를 반복 구간으로 나눈 나머지만큼만 역변환하여 원래 단어 찾기
// 변환
// 길이가 짝수일 때, afbecd -> abcdef
// 0 -> 2 -> 4 -|-> 5 -> 3 -> 1 순으로 읽으면 ok
// 길이가 홀수일 때, aebdc -> abcde
// 0 -> 2 -> 4 -|-> 3 -> 1 순으로 읽으면 ok

// 풀이 2
// 동일한 방식으로 변환
// 변환 과정을 List에 저장
// 반복을 찾은 뒤, 반복 구간으로 나눈 나머지만큼 다시 변환하는게 아니라 변환 과정에서 바로 찾기
// https://www.acmicpc.net/source/46579026
