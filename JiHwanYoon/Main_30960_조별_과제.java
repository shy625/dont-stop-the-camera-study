

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_30960_조별_과제 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // greedy algorithm 활용
        // 학번 순으로 정렬한 뒤, 왼쪽, 오른쪽부터 순서대로 2명씩 조를 이루는 경우에 대해 어색함의 누적합을 구한다.
        // 그리고, 3명씩 조를 이루는 모든 경우를 고려해 어색함의 총합의 최솟값을 구한다.
        Arrays.sort(arr);
        int[] sumL = new int[n];
        for (int i = 1; i < n; i += 2) {
            sumL[i] += arr[i] - arr[i-1];
            if (i-2 >= 0) {
            	sumL[i] += sumL[i-2];
            }
        }
        int[] sumR = new int[n];
        for (int i = n-2; i > 0; i -= 2) {
        	sumR[i] += arr[i+1] - arr[i];
            if (i+2 < n) {
            	sumR[i] += sumR[i+2];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n-2; i += 2) {
            int temp = (arr[i+2] - arr[i]);
            if (i-1 >= 0) {
                temp += sumL[i-1];
            }
            if (i+3 < n) {
                temp += sumR[i+3];
            }
            min = Math.min(temp, min);
        }
        System.out.println(min);
	}
}
