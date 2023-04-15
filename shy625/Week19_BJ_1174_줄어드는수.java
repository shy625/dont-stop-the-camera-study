/**
 * BJ 1174 줄어드는 수
 * @ prob : https://www.acmicpc.net/problem/1174
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week19_BJ_1174_줄어드는수 {

    static List<Long> descNumList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        descNumList = new ArrayList<>();
        int[] elements = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        subset(elements, 0, "");
        Collections.sort(descNumList);

        if (N > descNumList.size()) {
            System.out.println(-1);
        } else {
            System.out.println(descNumList.get(N - 1));
        }

        br.close();
    }
    
    private static void subset(int[] elements, int idx, String pickedNumStr) {
        if (idx == elements.length) {
            if (!pickedNumStr.equals("")) {
                Long descNum = Long.parseLong(sortDesc(pickedNumStr));
                descNumList.add(descNum);
            }
            return;
        }
        subset(elements, idx + 1, pickedNumStr + elements[idx]);
        subset(elements, idx + 1, pickedNumStr);
    }

    private static String sortDesc(String pickedNumStr) {
        char[] charArr = pickedNumStr.toCharArray();
        Arrays.sort(charArr);
        StringBuilder sb = new StringBuilder(String.valueOf(charArr));
        return sb.reverse().toString();
    }
}

// 풀이 1
// 왼쪽에서부터 자리수가 감소하는 수 ex) 321, 985, 9876543210 (가장 큰 수)
// 따라서 0 ~ 9까지의 수를 중복되지 않게 뽑아서 큰 수부터 작은 수 순서대로 정렬한 수
// 부분집합을 이용해 수를 뽑고 줄어드는 수로 만들어 리스트에 추가
// 리스트를 오름차순으로 정렬
// 주어진 N번째 수를 리스트에서 출력

// 다른 풀이 1
// BFS - Queue 이용
// https://ongveloper.tistory.com/506