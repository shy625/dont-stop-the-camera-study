/**
 * BJ 1874 스택 수열
 * @ prob : https://www.acmicpc.net/problem/1874
 * @ level : S3
 * @ Blog : 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Week1_BJ_1874_스택수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        
        int[] numSeq = new int[N];
        for(int n = 0; n < N; n++) {
            numSeq[n] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        int num = 0;
        stack.push(num++);  // 처음 peek() 했을 때 Exception 발생하지 않도록 바닥에 0 넣기

        int idx = 0;
        while(idx < N) {
            if(stack.peek() == numSeq[idx]) {   // top이 수열의 다음 숫자와 같은 경우
                stack.pop();
                sb.append("-").append(System.lineSeparator());
                idx++;
            } else if(stack.peek() < numSeq[idx]) { // top이 수열의 다음 숫자보다 작은 경우
                stack.push(num++);
                sb.append("+").append(System.lineSeparator());
            } else {    // top이 수열의 다음 숫자보다 큰 경우
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}