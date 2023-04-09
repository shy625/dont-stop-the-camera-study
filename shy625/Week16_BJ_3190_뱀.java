/**
 * BJ 3190 뱀
 * @ prob : https://www.acmicpc.net/problem/3190
 * @ level : G5
 */

import java.io.*;
import java.util.*;

public class Week16_BJ_3190_뱀 {

    static class Action {
        int second;
        String turn;

        Action(int second, String turn) {
            this.second = second;
            this.turn = turn;
        }
    }

    static class Snake {
        List<int[]> body;
        int direction;

        Snake() {
            body = new ArrayList<>();
            body.add(new int[] { 1, 1 });   // 초기 뱀 위치
            direction = 1;  // 초기 뱀 방향
        }
    }

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int[][] board;   // -1 : 벽, 0 : 빈칸, 1 : 사과, 2 : 뱀
    static Snake snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        board = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) {
            board[i][0] = -1;
            board[i][N + 1] = -1;
            board[0][i] = -1;
            board[N + 1][i] = -1;
        }
        board[1][1] = 2;    // 초기 뱀 위치

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        List<Action> actionList = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            String turn = st.nextToken();
            actionList.add(new Action(second, turn));
        }

        snake = new Snake();
        int time = 0;
        while (true) {
            time++;

            moveHead();     // 머리 이동 (다른 사물과 겹치는지 확인을 위해 보드 값 변경은 나중에)
            int[] head = snake.body.get(0);
            if (board[head[0]][head[1]] == -1 || board[head[0]][head[1]] == 2) { // 벽 또는 자기자신
                break;
            } else if (board[head[0]][head[1]] != 1) { // 사과 X
                moveTail();
            }
            board[head[0]][head[1]] = 2;    // 보드에 머리 위치 값 변경

            // X초가 끝난 뒤에 방향 전환
            if (!actionList.isEmpty() && actionList.get(0).second == time) {
                String turn = actionList.remove(0).turn;
                makeTurn(turn);
            }
        }
        
        System.out.println(time);

        br.close();
    }
    
    private static void moveHead() {
        int[] curHead = snake.body.get(0);
        int[] nextHead = new int[2];
        nextHead[0] = curHead[0] + dr[snake.direction];
        nextHead[1] = curHead[1] + dc[snake.direction];
        snake.body.add(0, nextHead);
    }

    private static void moveTail() {
        int[] tail = snake.body.remove(snake.body.size() - 1);
        board[tail[0]][tail[1]] = 0;
    }

    private static void makeTurn(String turn) {
        if (turn.equals("L")) {     // 좌로 90도
            snake.direction--;
            if (snake.direction < 0) {
                snake.direction = 3;
            }
        } else {    // 우로 90도
            snake.direction = (snake.direction + 1) % 4;
        }
    }
}
