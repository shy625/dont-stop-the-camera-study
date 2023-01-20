public class Solution_Lv1_키패드누르기 {

    public static String solution(int[] numbers, String hand) {
        int leftHand = 11;    //*
        int rightHand = 12;   //#
        String answer = "";
        String myHand = "";

        if (hand.equals("left")) {
            myHand = "L";
        } else {
            myHand = "R";
        }

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (number == 1 || number == 4 || number == 7) {
                leftHand = number;
                answer = answer + "L";
            } else if (number == 3 || number == 6 || number == 9) {
                rightHand = number;
                answer = answer + "R";
            } else {
                int moveLeft = move(leftHand, number);
                int moveRigth = move(rightHand, number);

                if (moveLeft < moveRigth) {
                    leftHand = number;
                    answer = answer + "L";
                } else if (moveLeft > moveRigth) {
                    rightHand = number;
                    answer = answer + "R";
                } else {
                    if (myHand.equals("L")) {
                        leftHand = number;
                    } else {
                        rightHand = number;
                    }
                    answer = answer + myHand;
                }
            }
        }
        return answer;
    }

    public static int move(int start, int end) {
        int[][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {11, 0, 12}};
        int sr = -1, sc = -1, er = -1, ec = -1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (keypad[i][j] == start) {
                    sr = i;
                    sc = j;
                }
                if (keypad[i][j] == end) {
                    er = i;
                    ec = j;
                }
            }
        }
        return Math.abs(er - sr) + Math.abs(ec - sc);
    }

}
