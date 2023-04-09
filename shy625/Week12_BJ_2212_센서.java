/**
 * BJ 2212 센서
 * @ prob : https://www.acmicpc.net/problem/2212
 * @ level : G5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Week12_BJ_2212_센서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);

        int[] gaps = new int[N-1];
        for(int i = 0; i < N-1; i++) {
            gaps[i] = sensors[i+1] - sensors[i];
        }

        Arrays.sort(gaps);

        int result = 0;
        for(int i = 0; i < N-K; i++) {
            result += gaps[i];
        }
        
        System.out.println(result);

        br.close();
    }
}

// 풀이 1 - 실패
// https://www.acmicpc.net/source/42578955

// 풀이 2
// 차례대로 i, i+1번째 센서들의 거리 차를 구하고 가장 큰 거리차 K-1개를 기준으로 그룹을 나눈다.
// PQ를 이용해 거리차가 큰 순서대로 정렬해 K-1개를 뽑고, 다시 idx 순서대로 정렬해 그룹을 나눈다.
// 나누어진 각 그룹의 처음 센서와 마지막 센서의 거리차를 결과에 더한다.
// 쓸데없이 어렵게 푼 방식
// https://www.acmicpc.net/source/42598906

// 풀이 3
// 풀이 2와 동일하게 거리차가 큰 K-1개를 제외
// 거리 차를 구했을 때 겹치는 부분없이 일렬로 쭉 구한 것이므로 답을 구할 때 제외한 거리 차를 빼고 나머지를 그냥 다 더하면 됨
// https://www.acmicpc.net/source/42600204