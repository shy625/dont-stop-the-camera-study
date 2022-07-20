/**
 * BJ 1105 팔
 * @ prob : https://www.acmicpc.net/problem/1105
 * @ level : S1
 */

import java.io.*;
import java.util.*;

public class Week23_BJ_1105_팔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String L = st.nextToken();
        String R = st.nextToken();

        char[] num = L.toCharArray();

        int count8 = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == '8') {
                count8++;
            }
        }

        int minCount = 0;
        if (count8 > 0) {
            for (int i = 0; i < num.length; i++) {
                if (num[i] == '8') {
                    char[] tmp = num.clone();
                    tmp[i]++;
                    for (int j = i + 1; j < tmp.length; j++) {
                        tmp[j] = '0';
                    }
                    if (Integer.parseInt(String.valueOf(tmp)) <= Integer.parseInt(R)) {
                        break;
                    }
                    minCount++;
                }
            }
        }

        System.out.println(minCount);

        br.close();
    }
}

// 풀이 1
// L값부터 시작
// 큰 자릿수부터 시작해서 해당 자릿수가 8이면 해당 자릿수가 8보다 큰 수(9밖에 없음)가 되도록 하는 가장 작은 수 구하기
// ex) 88 -> 90 / 386 -> 390 / 8808 -> 8809
// 구한 수가 R보다 크면 (범위에 포함되지 않는 경우) 다음 자릿수에 대해 동일하게 확인
// 구한 수가 R보다 작으면 (범위에 포함되는 경우) 해당 자릿수 이상에서 8의 개수 출력
