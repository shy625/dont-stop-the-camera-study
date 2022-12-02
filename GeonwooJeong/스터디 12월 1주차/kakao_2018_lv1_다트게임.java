import java.io.*;
import java.util.*;

class kakao_2018_lv1_다트게임 {
    public int solution(String dartResult) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        int skip = 0;
        
        for(int i=0;i<dartResult.length();i++) {
            if(skip > 0) {
                skip--;
                continue;
            }
            char c = dartResult.charAt(i);
            int n = c - '0';
            if(dartResult.charAt(i+1) == '0') {
                n = 10;
                skip++;
                char comm = dartResult.charAt(i+2);
                if(comm == 'D') {
                    n = 100;
                } else if(comm == 'T') {
                    n = 1000;
                }
                skip++;
                
                if(i+3 < dartResult.length() && dartResult.charAt(i+3) == '*') {
                    if(!stack.isEmpty()) {
                        int tmp = stack.pop();
                        tmp *= 2;
                        stack.push(tmp);
                    }
                    n *= 2;
                    skip++;
                } else if(i+3 < dartResult.length() && dartResult.charAt(i+3) == '#') {
                    n *= -1;
                    skip++;
                }
                stack.push(n);
            } else {
                char comm = dartResult.charAt(i+1);
                if(comm == 'D') {
                    n = n*n;
                } else if(comm == 'T') {
                    n = n*n*n;
                }
                skip++;
                
                if(i+2 < dartResult.length() && dartResult.charAt(i+2) == '*') {
                    if(!stack.isEmpty()) {
                        int tmp = stack.pop();
                        tmp *= 2;
                        stack.push(tmp);
                    }
                    n *= 2;
                    skip++;
                } else if(i+2 < dartResult.length() && dartResult.charAt(i+2) == '#') {
                    n *= -1;
                    skip++;
                }
                stack.push(n);
            }

        }
        
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }
        
        return answer;
    }
}
