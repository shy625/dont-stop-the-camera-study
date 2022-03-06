/**
 * BJ 10971 외판원 순회 2
 * @ prob : https://www.acmicpc.net/problem/10971
 * @ level : S2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week5_BJ_10971_외판원순회2 {

    static int[] cities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cities = new int[N];    // 도시들의 순회 순서
        for(int i = 0; i < N; i++) {
            cities[i] = i;
        }

        int min = Integer.MAX_VALUE;    // 순회 최소 비용
        outer :
        do {
            int weightSum = 0;

            // 마지막 도시 -> 처음 도시로 돌아오는 길이 없으면 skip
            if(adjMatrix[cities[N-1]][cities[0]] == 0) {
                continue;
            }
            for(int i = 0; i < N-1; i++) {
                int from = cities[i];
                int to = cities[i+1];
                if(adjMatrix[from][to] == 0) {      // 길이 없으면 다음 순회 방법 진행
                    continue outer;
                }
                weightSum += adjMatrix[from][to];
            }
            weightSum += adjMatrix[cities[N-1]][cities[0]];     // 마지막 도시에서 처음 도시로 돌아오는 길

            min = Math.min(min, weightSum);
        } while (np(N));

        System.out.println(min);

        br.close();
    }

    // next permutation
    private static boolean np(int N) {
        int i = N - 1;
        while(i > 0 && cities[i-1] >= cities[i]) {
            i--;
        }
        if(i == 0) {
            return false;
        }

        int j = N - 1;
        while(cities[i-1] >= cities[j]) {
            j--;
        }

        swap(i-1, j);

        int k = N - 1;
        while(i < k) {
            swap(i++, k--);
        }

        return true;
    }

    private static void swap(int a, int b) {
        int tmp = cities[a];
        cities[a] = cities[b];
        cities[b] = tmp;
    }
}

// 풀이 1
// 순열을 이용해 모든 경우의 순회 순서에 대해 비용 체크하여 최소 비용 구하기
// https://www.acmicpc.net/source/40044330