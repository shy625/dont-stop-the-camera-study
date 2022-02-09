package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class bj1874 {
	static int N;
	static int num;
	public static void main(String[] args) throws IOException {
		BufferedReader scann = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(scann.readLine());
		Stack<Integer> stack = new Stack<>();
		ArrayList<Character> answer = new ArrayList<Character>();
		int cur = 1;
		for(int i=0;i<N;i++) {
			num = Integer.parseInt(scann.readLine());
			if(num>=cur) {
				for(int j=cur;j<=num;j++) {
					answer.add('+');
					stack.push(j);
				}
				cur = num+1;
			}
			if(stack.peek() == num) {
				answer.add('-');
				stack.pop();
			}
			else {
				System.out.println("NO");
				return;
			}
		}
		for(char x:answer) {
			System.out.println(x);
		}
	}//main

}//class
