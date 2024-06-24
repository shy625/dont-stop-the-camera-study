/**
 * BJ 21314 민겸 수
 * @ prob : https://www.acmicpc.net/problem/21314
 * @ level : S2
 */

import java.io.*;

public class Week25_BJ_21314_민겸수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringBuilder result = new StringBuilder();

        // 변환될 수 있는 가장 큰 수
        int idx = 0;
        while (idx < str.length()) {
            int kIdx = str.indexOf("K", idx);
            if (kIdx == idx) {  // 현재 위치가 K일 때
                result.append(5);
                idx++;
            } else if (kIdx > 0) {  // 현재 위치 이후로 K가 있을 때
                result.append(5);
                for (int i = idx; i < kIdx; i++) {
                    result.append(0);
                }
                idx = kIdx + 1;
            } else {    // 현재 위치 이후로 K가 없을 때
                for (int i = idx; i < str.length(); i++) {
                    result.append(1);
                }
                break;
            }
        }

        result.append(System.lineSeparator());
        
        // 변환될 수 있는 가장 작은 수
        idx = 0;
        while (idx < str.length()) {
            int kIdx = str.indexOf("K", idx);
            if (kIdx == idx) {  // 현재 위치가 K일 때
                result.append(5);
                idx++;
            } else if (kIdx > 0) {  // 현재 위치 이후로 K가 있을 때
                result.append(1);
                for (int i = idx; i < kIdx - 1; i++) {
                    result.append(0);
                }
                idx = kIdx;
            } else {    // 현재 위치 이후로 K가 없을 때
                result.append(1);
                for (int i = idx; i < str.length() - 1; i++) {
                    result.append(0);
                }
                break;
            }
        }

        System.out.println(result);

        br.close();
    }
}

// 풀이 1
// indexOf()로 K 위치 확인
// K 위치에 따라 현재 위치인 경우, 이후에 K가 있는 경우, 이후에 K가 없는 경우 처리
// 민겹 수와 십진수 변환 시 자릿수는 동일 & 다른 자리에 영향 X
// 변환 시 값 크기 비교 : MM...K > M > MM...M
// 위 크기 비교에 따라 최대는 값이 가장 커지도록, 최소는 값이 가장 작아지도록 변환
// K가 연속되는 경우는 각각의 K를 처리하는 방법 밖에 없음
// 문자열의 길이가 3000까지 가능하기 때문에 M이 3000개면 int 범위 벗어남
// -> int로 한번에 변환하지 않고 문자열로 처리

// 다른 풀이 1
// 앞에서부터 M 개수를 카운트
// K가 나오면 M 개수 초기화 후 최대, 최소에 맞게 처리
// 최대는 MMM ... K로 처리
// 최소는 MMM ... 으로 처리
// M만 나오고 끝나는 경우
// 최대는 M 개별로 처리
// 최소는 MMM ... 으로 처리
// https://www.acmicpc.net/source/46332163