import java.util.Scanner;

public class Main_2529_부등호 {

    static int k;
    static String symbol;

    static boolean[] visited = new boolean[10];

    static String maxNum = "0";
    static String minNum = "9876543210";

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        k = scann.nextInt();
        scann.nextLine();
        symbol = scann.nextLine();
        symbol = symbol.replaceAll(" ", "");

        pickNumber("", 0);

        System.out.println(maxNum);
        System.out.println(minNum);
    }

    public static void pickNumber(String number, int cnt) {
        if (cnt == k + 1) {
            if (validNumber(number)) {
                if (Long.parseLong(minNum) > Long.parseLong(number)) {
                    minNum = number;
                }
                if (Long.parseLong(maxNum) < Long.parseLong(number)) {
                    maxNum = number;
                }
                return;
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                number = number + (char) (i + '0');
                pickNumber(number, cnt + 1);
                number = number.substring(0, cnt);
                visited[i] = false;
            }
        }
    }

    public static boolean validNumber(String number) {
        int[] numArray = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            numArray[i] = number.charAt(i) - '0';
        }
        for (int i = 0; i < k; i++) {
            if (symbol.charAt(i) == '<') {
                if (!(numArray[i] < numArray[i + 1])) {
                    return false;
                }
            } else {
                if (!(numArray[i] > numArray[i + 1])) {
                    return false;
                }
            }
        }
        return true;
    }
}
