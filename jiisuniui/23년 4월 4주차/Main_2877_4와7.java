package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 4와 7 -> 0,1로 생각하면 2진수
// k 0,1치환 답
// 1 0 4
// 2 1 7
// 3 00 44
// 4 01 47
// 5 10 74
// 6 11 77
// 7 000 444
// 8 001 447
// 몇자리 수인지 구하기

// 각 섹션에서 2진수 구하기
// bit==2 라면
// k 2진수 답
// 0(3) 0 44
// 1(4) 1 47
// 2(5) 10 74
// 3(6) 11 77

public class Main_2877_4와7 {
    static public void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int k = scann.nextInt();

        //k=6
        int bit = 1;
        for (;;) {
            if (k <= Math.pow(2, bit)) {
                break;
            } else {
                k -= (int) Math.pow(2, bit);
                bit++;
            }
        }

        //k=4
        List<Integer> inverseBinary = new ArrayList<>();
        k--;
        //k=3
        for (;;) {
            inverseBinary.add(k % 2);
            k = k / 2;

            if (k == 0) {
                break;
            }
        }

        String answer = "";
        String binary = "";
        for (int i = inverseBinary.size() - 1; i >= 0; i--) {
            if (inverseBinary.get(i) == 0) {
                binary += "4";
            } else {
                binary += "7";
            }
        }

        bit -= binary.length();
        for (int i = 0; i < bit; i++) {
            answer += "4";
        }
        answer = answer + binary;

        System.out.println(answer);

        scann.close();
    }
}
