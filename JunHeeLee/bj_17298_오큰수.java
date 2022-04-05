package algo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class bj_17298_오큰수 {
	static int N; //숫자갯수
	static Stack<Integer>stack;
	static int[] num;
	static int[] answer;
	public static void main(String[] args) throws IOException {
		stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		answer = new int[N];//정답담을 배열
		num = new int[N];//인풋 숫자담을 배열
		Arrays.fill(answer,-1);//정답배열을 -1로 채움
		
		for(int i=0;i<N;i++) {
			int input = Integer.parseInt(st.nextToken());
			num[i] = input;
		}//input을 num 배열에 저장

		for(int i=N-1;i>=0;i--) { //맨 오른쪽부터
			while(!stack.isEmpty()) {
				int check = stack.pop();
				if (num[i]<check) { 
					answer[i] = check;
					stack.push(check);
					break;
				}
			}
			stack.push(num[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

}
