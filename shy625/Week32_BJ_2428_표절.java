/**
 * BJ 2428 표절
 * @ prob : https://www.acmicpc.net/problem/2428
 * @ level : S3
 */

import java.io.*;
import java.util.*;

public class Week32_BJ_2428_표절 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[] files = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(files);

        long checkCount = 0;
        for (int i = 0; i < N; i++) {
            int baseVal = (int) Math.ceil(0.9 * files[i]);
            int idx = Arrays.binarySearch(files, baseVal);
            if (idx < 0) {
                idx = -idx - 1;
            } else if (idx > 0) {
                while (idx > 0) {
                    if (files[idx - 1] != baseVal) {
                        break;
                    }
                    idx--;
                }
            }
            System.out.println("baseVal " + baseVal + " idx " + idx);
            checkCount += i - idx;
        }

        System.out.println(checkCount);

        br.close();
    }
}

// 풀이 1 - 시간초과
// 정렬 후 파일 중 하나에 대해 0.9를 곱해 기준값을 구함
// 해당 파일보다 작은 파일부터 0까지 기준값과 비교하여 기준값보다 크면 count + 1
// 기준값보다 작은 파일이 나오면 정렬되어 해당 파일 이후는 모두 작으므로 break
// https://www.acmicpc.net/source/49545612

// 풀이 2
// 정렬 후 파일 중 하나에 대해 0.9를 곱해 기준값을 구함
// int 배열이므로 기준값은 int로 변환 -> 기준값 이상인 파일을 찾으므로 기준값에서 올림한 값 사용
// binarySearch를 사용해 배열에서 해당 값의 위치 또는 들어갈 위치를 구함
// 배열에 동일한 값이 여러 개 있는 경우, 그 중 어떤 값의 index를 반환할지 보장되지 않으므로 동일한 값 중 가장 왼쪽의 index 사용
// 비교할 대상의 개수는 (현재 파일의 index) -  (binarySearch를 통해 나온 index)
// N이 최대 10만 -> 비교횟수 최대 10만 * 10만으로 int 범위가 벗어날 수 있으므로 long 사용