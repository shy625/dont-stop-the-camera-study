package BOJ;

import java.util.*;

public class Main_2885_초콜릿식사 {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int K = scann.nextInt();
        int chocolateSize = 0;
        int splitNum = 0;

        int p = -1;
        while (K > chocolateSize) {
            p++;
            chocolateSize = (int) Math.pow(2, p);
        }

        int eat = 0;
        Stack<Integer> chocolate = new Stack<>();
        chocolate.push(chocolateSize);
        while (eat != K) {
            int piece = chocolate.pop();
            if (piece <= K - eat) {
                eat += piece;
            } else {
                chocolate.push(piece / 2);
                chocolate.push(piece / 2);
                splitNum++;
            }
        }

        System.out.println(chocolateSize + " " + splitNum);
        scann.close();
    }
}
