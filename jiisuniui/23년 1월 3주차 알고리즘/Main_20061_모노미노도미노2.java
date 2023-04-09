import java.util.Scanner;

// 1. 빨간색에 타일이 생성되면 파랑,초록으로 내려감
// 2. 내려갔을 때, 4개가 채워지면 없어지면서 점수생성
// 3. 연한 곳에 그림이 생성된다면
//      1) 찐한곳에서 없어진게 있는지 확인
//      2) 그래도 연한곳에 남아있으면 맨 끝줄 삭제 후 블럭 내림
//
// t=1: 1x1 인 블록을 (x, y)에 놓은 경우
// t=2: 1×2 인 블록을 (x, y), (x, y+1)에 놓은 경우
// t=3: 2×1 인 블록을 (x, y), (x+1, y)에 놓은 경우
//

// 아이디어: 빨강&초록 처럼 빨강$파랑을 뒤집어서 생각 (빨강의 행열만 열행으로 바꾸면 됨)
// 가로가 1칸일때: t=1, t=2(blue), t=3(green)
// 가로가 2칸일때: t=2(green), t=3(blue)

public class Main_20061_모노미노도미노2 {
    static boolean[][] green = new boolean[6][4];
    static boolean[][] blue = new boolean[6][4];
    static int score = 0;

    static public void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int N = scann.nextInt();

        for (int n = 0; n < N; n++) {
            int t = scann.nextInt();
            int x = scann.nextInt();
            int y = scann.nextInt();

            moveBlock(t, x, y);
        }

        int count = countBlock();

        System.out.println(score);
        System.out.println(count);
    }

    // color: green(0), blue(1)
    // 가로가 1칸일때: t=1, t=2(blue), t=3(green)
    // 가로가 2칸일때: t=2(green), t=3(blue)
    // xy 변환은 여기서 진행
    static public void moveBlock(int t, int x, int y) {
        if (t == 1) {
            moveBlockOneBottom(1, y, 'g');
            moveBlockOneBottom(1, x, 'b');
        } else if (t == 2) {
            moveBlockTwoBottom(y, y + 1, 'g');
            moveBlockOneBottom(2, x, 'b');
        } else if (t == 3) {
            moveBlockOneBottom(3, y, 'g');
            moveBlockTwoBottom(x, x + 1, 'b');
        }

        checkFullBlock();
        checkLightBlock();
    }

    // 1개의 밑면 좌표만 필요
    // t=1이 아니면 쌓을때 위로 하나 더 쌓아야됨
    static public void moveBlockOneBottom(int t, int y, char color) {
        if (color == 'g') { // green
            for (int i = 2; i < 6; i++) {
                if (green[i][y] == true) {
                    green[i - 1][y] = true;
                    if (t != 1) {
                        green[i - 2][y] = true;
                    }
                    break;
                } else if (i == 5) {
                    green[i][y] = true;
                    if (t != 1) {
                        green[i - 1][y] = true;
                    }
                }
            }
        } else { // blue
            for (int i = 2; i < 6; i++) {
                if (blue[i][y] == true) {
                    blue[i - 1][y] = true;
                    if (t != 1) {
                        blue[i - 2][y] = true;
                    }
                    break;
                } else if (i == 5) {
                    blue[i][y] = true;
                    if (t != 1) {
                        blue[i - 1][y] = true;
                    }
                }
            }
        }
    }

    // 2개의 밑면 좌표만 필요
    static public void moveBlockTwoBottom(int y1, int y2, char color) {
        if (color == 'g') { // green
            for (int i = 2; i < 6; i++) {
                if (green[i][y1] == true || green[i][y2] == true) {
                    green[i - 1][y1] = true;
                    green[i - 1][y2] = true;
                    break;
                } else if (i == 5) {
                    green[i][y1] = true;
                    green[i][y2] = true;
                }
            }

        } else { // blue
            for (int i = 2; i < 6; i++) {
                if (blue[i][y1] == true || blue[i][y2] == true) {
                    blue[i - 1][y1] = true;
                    blue[i - 1][y2] = true;
                    break;
                } else if (i == 5) {
                    blue[i][y1] = true;
                    blue[i][y2] = true;
                }
            }
        }
    }

    static public void checkFullBlock() {
        // green
        for (int i = 5; i >= 0; i--) {
            if (green[i][0] && green[i][1] && green[i][2] && green[i][3]) {
                score++;
                for (int j = i; j >= 1; j--) {
                    green[j][0] = green[j - 1][0];
                    green[j][1] = green[j - 1][1];
                    green[j][2] = green[j - 1][2];
                    green[j][3] = green[j - 1][3];
                }
                green[0][0] = false;
                green[0][1] = false;
                green[0][2] = false;
                green[0][3] = false;
                i++; // 한칸 내려갔으니 내려간 칸도 검사
            }
        }

        // blue
        for (int i = 5; i >= 0; i--) {
            if (blue[i][0] && blue[i][1] && blue[i][2] && blue[i][3]) {
                score++;
                for (int j = i; j >= 1; j--) {
                    blue[j][0] = blue[j - 1][0];
                    blue[j][1] = blue[j - 1][1];
                    blue[j][2] = blue[j - 1][2];
                    blue[j][3] = blue[j - 1][3];
                }
                blue[0][0] = false;
                blue[0][1] = false;
                blue[0][2] = false;
                blue[0][3] = false;
                i++; // 한칸 내려갔으니 내려간 칸도 검사
            }
        }
    }

    static public void checkLightBlock() {
        // green
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[1][j]) {
                    for (int k = 5; k >= 1; k--) {
                        green[k][0] = green[k - 1][0];
                        green[k][1] = green[k - 1][1];
                        green[k][2] = green[k - 1][2];
                        green[k][3] = green[k - 1][3];
                    }
                    green[0][0] = false;
                    green[0][1] = false;
                    green[0][2] = false;
                    green[0][3] = false;
                    break;
                }
            }
        }

        // blue
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[1][j]) {
                    for (int k = 5; k >= 1; k--) {
                        blue[k][0] = blue[k - 1][0];
                        blue[k][1] = blue[k - 1][1];
                        blue[k][2] = blue[k - 1][2];
                        blue[k][3] = blue[k - 1][3];
                    }
                    blue[0][0] = false;
                    blue[0][1] = false;
                    blue[0][2] = false;
                    blue[0][3] = false;

                    break;
                }
            }
        }
    }

    static public int countBlock() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[i][j]) {
                    count++;
                }
                if (green[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
