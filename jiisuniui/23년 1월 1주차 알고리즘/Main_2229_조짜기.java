import java.util.Scanner;

public class Main_2229_조짜기 {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int N = scann.nextInt();
        int[] student = new int[N];
        for (int i = 0; i < N; i++) {
            student[i] = scann.nextInt();
        }

        long answer = go(N, student);
        System.out.println(answer);
    }

    public static long go(int N, int[] score) {
        long[] maxSum = new long[N];

        for (int i = 0; i < N; i++) {
            long max = findMaxDiff(score, 0, i);

            for (int j = 0; j < i; j++) {
                max = Math.max(max, maxSum[j] + findMaxDiff(score, j + 1, i));
            }
            maxSum[i] = max;
        }

        return maxSum[N - 1];
    }

    public static int findMaxDiff(int[] score, int s, int e) {
        int maxScore = findMaxNum(score, s, e);
        int minScore = findMinNum(score, s, e);
        return maxScore - minScore;
    }

    public static int findMaxNum(int[] score, int s, int e) {
        int max = 0;
        for (int i = s; i <= e; i++) {
            if (max < score[i]) {
                max = score[i];
            }
        }
        return max;
    }

    public static int findMinNum(int[] score, int s, int e) {
        int min = findMaxNum(score, s, e);
        for (int i = s; i <= e; i++) {
            if (min > score[i]) {
                min = score[i];
            }
        }
        return min;
    }
}
