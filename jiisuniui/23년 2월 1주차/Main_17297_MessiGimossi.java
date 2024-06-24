package BOJ;

import java.util.*;

// Messi : 5, Gimossi : 7, 공백 : 1
// N이 홀수일때 마지막 글자 Messi, 짝수일때 마지막 글자 Gimossi
// M이 어디쯤 해당하는지 알아야됨 -> N-1 < M < N 으로 N을 설정
// messi(N) = messi(N-1)+" "+messi(N-2)
// size(N) = size(N-1)+ 1 + size(N-2)

// 조건 1. M == size(N) --> 무조건 'i'
// 조건 2. M == size(N-1) + 1  --> 공백
// 조건 3. M > size(N-1) + 1 --> 줄여가면서 탐색
// 조건 3-1. M<size(N-1), messi(N-1)에서 M 번째 단어 찾기
// 조건 3-1. size(N-1)+1 < M <size(N), messi(N-2)에서 M-size(N-1)-1 번째 단어 찾기

public class Main_17297_MessiGimossi {
    static long M;
    static int N;
    static char result = '.';
    static boolean isFind = false;
    static List<Long> messiSize = new ArrayList<>();
    static List<String> messi = new ArrayList<>();
    // List index 0은 없는 취급

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        M = scann.nextLong();

        messiSize.add((long) 0);
        messiSize.add((long) 5);
        messiSize.add((long) 13);
        messi.add("");
        messi.add("Messi");
        messi.add("Messi Gimossi");

        N = 2;
        while (M > messiSize.get(N)) {
            N++;
            long nextSize = messiSize.get(N - 1) + 1 + messiSize.get(N - 2);
            messiSize.add(nextSize);
        }

        while (!isFind) {
            goFind();
        }

        if (result == ' ') {
            System.out.println("Messi Messi Gimossi");
        } else {
            System.out.println(result);
        }

        scann.close();
    }

    // 조건 1. M == size(N) --> 무조건 'i'
    // 조건 2. M == size(N-1) + 1 --> 공백
    // 조건 3. M > size(N-1) + 1 --> 줄여가면서 탐색
    // 조건 3-1. M<size(N-1), messi(N-1)에서 M 번째 단어 찾기
    // 조건 3-1. size(N-1)+1 < M <size(N), messi(N-2)에서 M-size(N-1)-1 번째 단어 찾기
    public static void goFind() {
        if (!isRange()) {
            if (M == messiSize.get(N)) {
                // System.out.println("N==M");
                result = 'i';
                isFind = true;
            } else if (M == messiSize.get(N - 1) + 1) {
                // System.out.println("blank");
                result = ' ';
                isFind = true;
            } else {
                // System.out.println("N:" + N + " between");

                if (M <= messiSize.get(N - 1)) {
                    N = N - 1;
                } else {
                    M = M - messiSize.get(N - 1) - 1;
                    N = N - 2;
                }
                // System.out.println("change M:" + M + ", N:" + N);
            }
        }
    }

    public static boolean isRange() {
        if (M <= messiSize.get(1)) {
            // System.out.println("in Messi(1) range, M=" + M);
            result = messi.get(1).charAt((int) M - 1);
            isFind = true;
            return true;
        } else if (M <= messiSize.get(2)) {
            // System.out.println("in Messi(2) range, M=" + M);
            result = messi.get(2).charAt((int) M - 1);
            isFind = true;
            return true;
        }
        return false;
    }
}
