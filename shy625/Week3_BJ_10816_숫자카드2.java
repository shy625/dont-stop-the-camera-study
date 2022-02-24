/**
 * BJ 10816 숫자 카드 2
 * @ prob : https://www.acmicpc.net/problem/10816
 * @ level : S4
 * @ Blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week3_BJ_10816_숫자카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N 관련 초기화
        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        int[] nArr = new int[N];
        for(int i = 0; i < N; i++) {
            nArr[i] = Integer.parseInt(inputs[i]);
        }

        // M 관련 초기화
        int M = Integer.parseInt(br.readLine());
        inputs = br.readLine().split(" ");
        int[] mArr = new int[N];
        for(int i = 0; i < M; i++) {
            mArr[i] = Integer.parseInt(inputs[i]);
        }

        int[] freqArr = new int[20000001];      // 전체 숫자 범위를 크기로 가지는 int 배열, 등장 빈도 체크

        // 등장한 수에 인덱스 보정하여 배열 값 + 1
        for(int i = 0; i < N; i++) {
            freqArr[nArr[i] + 10000000]++;
        }

        // 찾고자 하는 숫자를 인덱스로 하여 배열 값 출력
        for(int i = 0; i < M; i++) {
            sb.append(freqArr[mArr[i] + 10000000]).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }
}

// 풀이 1
// 0 ~ 20,000,000의 범위를 가지는 int 배열을 생성하고, 숫자 카드의 값 + 10,000,000을 인덱스로 하여 빈도수 저장.
// 1032ms

// 풀이 2
// 정렬 -> binarySearch()로 찾기 -> 찾으면 count+1, 없으면 다음 숫자 -> 반복
// 시간 초과

// Arrays.sort(nArr);
//
// int removeCount = 0;
// for(int i = 0; i < M; i++) {
//     int count = 0;
//     while(true) {
//         int index = Arrays.binarySearch(nArr, removeCount, nArr.length, mArr[i]);
//         if(index < 0) {
//             break;
//         } else {
//             count++;
//             nArr[index] = Integer.MIN_VALUE;
//             Arrays.sort(nArr);
//             removeCount++;
//         }
//     }
//     sb.append(count).append(" ");
// }