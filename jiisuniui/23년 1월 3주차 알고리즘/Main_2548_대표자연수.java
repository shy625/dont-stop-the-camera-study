package BOJ;

import java.util.Scanner;

public class Main_2548_대표자연수 {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int N = scann.nextInt();
        int[] number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = scann.nextInt();
        }

        int answer = findRepresentativeNumber(N, number);
        System.out.println(answer);

        scann.close();
    }

    public static int findRepresentativeNumber(int N, int[] number) {
        int answer = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += Math.abs(number[j] - number[i]);
            }

            if (sum < minSum) {
                answer = number[i];
                minSum = sum;
            } else if (sum == minSum) {
                if (number[i] < answer) {
                    answer = number[i];
                }
            }
        }

        return answer;
    }
}
