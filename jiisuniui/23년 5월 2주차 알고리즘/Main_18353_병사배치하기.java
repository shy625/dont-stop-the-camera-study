package BOJ;

import java.util.Scanner;

public class Main_18353_병사배치하기 {
    static int N;
    static int[] soldier;
    static int[] dp;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        N = scann.nextInt();
        soldier = new int[N + 1]; // 맨뒤에 0을 넣어서 0이 제일 작은수로 인식되게 함
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            soldier[i] = scann.nextInt();
        }

        soldier[N] = 0;
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int maxIndex = N;
            int equalIndex = N;
            for (int j = i + 1; j < N + 1; j++) {
                if (soldier[i] > soldier[j]) {
                    if (dp[maxIndex] < dp[j]) {
                        maxIndex = j;
                    }
                }
                if (soldier[maxIndex] == soldier[j]) {
                    if (dp[equalIndex] < dp[j]) {
                        equalIndex = j;
                    }
                }
            }

            if (soldier[i] == soldier[equalIndex]) { // 중복안됨
                dp[i] = dp[maxIndex];
            } else {
                dp[i] = dp[maxIndex] + 1;
            }

        }

        // for (int i = 0; i < N; i++) {
        // System.out.print(dp[i] + " ");
        // }
        // System.out.println();

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(dp[i], max);
        }

        int ans = N - max;
        System.out.println(ans);

        scann.close();
    }
}
