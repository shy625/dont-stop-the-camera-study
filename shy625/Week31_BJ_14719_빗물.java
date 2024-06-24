/**
 * BJ 14719 빗물
 * @ prob : https://www.acmicpc.net/problem/14719
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week31_BJ_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] blocks = new int[W];
        int highestBlockIdx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
            if (blocks[highestBlockIdx] < blocks[i]) {
                highestBlockIdx = i;
            }
        }

        int rain = 0;

        int prevBlock = -1;
        int dist = 0;
        int blockSum = 0;
        // 왼쪽 끝에서 가장 높은 블록까지 빗물 계산
        for (int i = 0; i <= highestBlockIdx; i++) {
            if (prevBlock < 0) {
                prevBlock = blocks[i];
            } else if (prevBlock > blocks[i]) {
                blockSum += blocks[i];
            } else {
                rain += prevBlock * (dist - 1) - blockSum;
                prevBlock = blocks[i];
                dist = 0;
                blockSum = 0;
            }
            dist++;
        }

        prevBlock = -1;
        dist = 0;
        blockSum = 0;
        // 오른쪽 끝에서 가장 높은 블록까지 빗물 계산
        for (int i = W - 1; i >= highestBlockIdx; i--) {
            if (prevBlock < 0) {
                prevBlock = blocks[i];
            } else if (prevBlock > blocks[i]) {
                blockSum += blocks[i];
            } else {
                rain += prevBlock * (dist - 1) - blockSum;
                prevBlock = blocks[i];
                dist = 0;
                blockSum = 0;
            }
            dist++;
        }

        System.out.println(rain);

        br.close();
    }
}

// 풀이 1
// 가장 높은 블럭을 기준으로 좌우 끝에서 가장 높은 블럭 쪽으로 이동하면서 아래와 같이 확인
// 블럭 높이 저장
// 다음 블럭이 저장한 블럭 높이보다 작으면 나중에 빗물 양에서 뺄 부피에 미리 저장
// 다음 블럭이 저장한 블럭 높이보다 같거나 높으면 해당 영역의 빗물 양 계산 후 저장한 블럭 높이 업데이트
// 해당 영역의 빗물 = 저장한 블럭 높이 * 두 블럭 사이의 거리 - 중간에 포함된 블럭 부피

// 다른 풀이 1
// https://www.acmicpc.net/source/23496230
// 저장될 빗물의 높이를 가지는 배열을 새로 만듦
// 각 위치마다 빗물의 높이값에서 기존 블럭의 높이값을 뺀 값을 더하여 계산

// 다른 풀이 2
// https://www.acmicpc.net/source/13479643
// 저장되는 빗물 양을 빗물 영역이 정해지면 높이와 거리를 이용해 한번에 계산하는게 아니라
// 이동하면서 바로바로 합을 계산해높고 빗물 영역이 정해지면 미리 계산한 합을 더하기

// else if (max < bricks[i]) {
//     answer += sum;
//     max = bricks[i];
//     sum = 0;
// }
// else 
//     sum += max - bricks[i];