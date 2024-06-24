/**
 * BJ 21758 꿀 따기
 * @ prob : https://www.acmicpc.net/problem/21758
 * @ level : S1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week16_BJ_21758_꿀따기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] honeys = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            honeys[i] = Integer.parseInt(st.nextToken());
        }

        int[] cumArr = new int[N];  // 얻을 수 있는 꿀의 누적값 배열
        int totalMax = 0;   // 총 얻을 수 있는 꿀의 최댓값
        int tmp = 0;

        // case 1 : 꿀통이 왼쪽 끝에 있는 경우 (index == 0)
        // 왼쪽을 시작으로 누적합 계산
        cumArr[0] = honeys[0];
        for (int i = 1; i < N; i++) {
            cumArr[i] = cumArr[i - 1] + honeys[i];
        }
        tmp = cumArr[N - 1] - honeys[N - 1];    // 오른쪽 끝에 꿀벌 배치
        for (int i = 1; i < N - 1; i++) {   // 배치한 꿀벌과 꿀통 사이에 나머지 꿀벌 배치
            totalMax = Math.max(totalMax, tmp + cumArr[i] - 2 * honeys[i]);     // 나머지 꿀벌을 배치하는 경우 중 최댓값
        }

        // case 2 : 꿀통이 오른쪽 끝에 있는 경우 (index == N - 1)
        // 오른쪽을 시작으로 누적합 계산
        cumArr[N - 1] = honeys[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            cumArr[i] = cumArr[i + 1] + honeys[i];
        }
        tmp = cumArr[0] - honeys[0];    // 왼쪽 끝에 꿀벌 배치
        for (int i = 1; i < N - 1; i++) {   // 배치한 꿀벌과 꿀통 사이에 나머지 꿀벌 배치
            totalMax = Math.max(totalMax, tmp + cumArr[i] - 2 * honeys[i]);     // 나머지 꿀벌을 배치하는 경우 중 최댓값
        }

        // case 3 : 꿀통이 중간에 있는 경우
        int max = 0;    // 배열의 값들 중 최댓값 == 꿀통이 위치한 곳의 꿀의 양
        tmp = 0;
        for (int i = 1; i < N - 1; i++) {   // 양 끝에 꿀벌 배치
            tmp += honeys[i];
            max = Math.max(max, honeys[i]);
        }
        totalMax = Math.max(totalMax, tmp + max);

        System.out.println(totalMax);

        br.close();
    }
}

// 풀이 1
// 3가지 경우로 나누어 계산
// 1. 꿀통이 오른쪽 끝에 위치하는 경우
// 2. 꿀통이 왼쪽 끝에 위치하는 경우
// 3. 꿀통이 중간에 위치하는 경우
// 1번과 2번의 경우 누적합 이용 -> 꿀벌이 꿀통까지 날아가면서 얻는 꿀의 양 == 누적합 - 출발점 값 (다른 꿀벌의 출발점을 지나가는 경우 그 값도 제외)
// 꿀통이 한쪽 끝에 위치하면 꿀벌 중 1마리는 반대쪽 끝에 배치, 나머지 1마리가 양 끝을 제외한 나머지 중 어디에 위치하느냐에 따라 최댓값이 결정되므로
// 첫번째 꿀벌의 꿀의 양 계산 후 1 ~ N-2까지 두번째 꿀벌의 값을 계산해보고 그 중 최댓값 저장
// 3번의 경우 첫번째 꿀벌의 시작점 ~ 두번째 꿀벌의 시작점 사이의 값들 합 + 꿀통이 위치한 곳의 값으로 계산
// 따라서 꿀벌들은 양 끝에 배치하고, 꿀통은 그 사이값 중 값이 가장 큰 곳에 배치하는 것이 최대