import java.util.Scanner;

public class Main_16953_AtoB {
    static int A;
    static int B;

    public static void main(String[] args) throws Exception {
        Scanner scann = new Scanner(System.in);
        A = scann.nextInt();
        B = scann.nextInt();
        int ans = 1;

        while (A < B) {
            if (B % 2 == 0) {
                B = B / 2;
                ans++;

            } else if (B % 10 == 1) {
                B = B / 10;
                ans++;
            }
            else {
                break;
            }
        }

        if (A != B) {
            ans = -1;

        }

        System.out.println(ans);
        scann.close();
    }

};
