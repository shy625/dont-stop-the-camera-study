import java.io.*;
import java.util.*;

class kakao_2019_lv1_크레인인형뽑기게임 {
    // 1. stack을 사용해서 구현
    // 2. 인형이 없는 곳에 작동시키는 케이스가 존재하니 고려하여 구현
    // 3. 인형을 옮길 때 마다 2개씩 붙어있는지 확인한다.
    static int answer;
    static Stack<Integer> stack = new Stack<>();
    
    public int solution(int[][] board, int[] moves) {
        int leng = board.length;
        for(int i=0;i<moves.length;i++) {
            int move = moves[i]-1;
            for(int j=0;j<leng;j++) {
                if(board[j][move] == 0) continue;
                stack.push(board[j][move]);
                check();
                board[j][move] = 0;
                break;
            }
            System.out.println();
        }
        
        return answer;
    }
    
    static void check() {
        while(true) {
            if(stack.isEmpty()) break;
            
            int n = stack.pop();
            if(stack.isEmpty()) {
                stack.push(n);
                break;
            } else {
                if(stack.peek() == n) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(n);
                    break;
                }
            }
        }
    }
    
}
