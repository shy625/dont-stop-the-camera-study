import java.util.Scanner;
import java.util.Stack;

public class Solution_Lv2_k진수에서소수개수구하기 {
    public static void main(String[] args) throws Exception {
        Scanner scann = new Scanner(System.in);
        int x = scann.nextInt();
        int y = scann.nextInt();
        int s = solution(x, y);
        System.out.println(s);
    }

    public static int solution(int n, int k) {

        Stack<Integer> st = new Stack<Integer>();
        long cnum = 0; // 변환된 숫자
        int answer = 0;

        while (n != 0) {
            st.push(n % k);
            n = n / k;
        }
        while (!st.empty()) {
            cnum = cnum * 10 + st.pop();
        }

        String cstr = String.valueOf(cnum); // 숫자를 문자형으로 변환
        String[] sstr = cstr.split("0"); // 0을 기준으로 자름
        for (String s : sstr) {
            if (s.equals("")) {
                continue;
            } else {
                long num = Long.parseLong(s);
                if (isPrime(num) == true) {
                    answer++;
                }
                // System.out.println(num + " : " + answer);
            }
        }
        return answer;
    }

    public static boolean isPrime(long tmp) {
        if (tmp == 1) {
            return false;
        }
        if (tmp == 2) {
            return true;
        }
        for (int i = 2; i <= (int) Math.sqrt(tmp); i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }

}
