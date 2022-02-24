/**
 * BJ 1541 잃어버린 괄호
 * @ prob : https://www.acmicpc.net/problem/1541
 * @ level : S2
 * @ Blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Week3_BJ_1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int i = 0;
        String numStr = "";
        int sum = 0;
        boolean isMinus = false;
        while(true) {
            if(i >= input.length()) {   // 마지막에 나온 숫자 계산 후 반복 종료
                int num = Integer.parseInt(numStr);
                sum += isMinus ? -num : num;
                break;
            }
            char ch = input.charAt(i);
            if(ch == '+' || ch == '-') {
                int num = Integer.parseInt(numStr);
                numStr = "";
                if(isMinus) {   // - 가 한번이라도 나오면 이후는 모두 뺄셈
                    sum -= num;
                } else {
                    sum += num;
                    if(ch == '-') {
                        isMinus = true;
                    }
                }
            } else {
                numStr += ch;
            }
            i++;
        }
        System.out.println(sum);

        br.close();
    }
}