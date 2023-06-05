import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열폭발 {
	
	static int size;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<>();
		char[] ch = br.readLine().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			stack.push(ch[i]);
		}
		char[] boom=br.readLine().toCharArray();
		size=boom.length;
		Stack<Character> answer = new Stack<>();//정답 보관할 문자열
		
		for (int i = 0; i < ch.length; i++) {
			char temp=stack.pop();
			answer.push(temp);
			if(temp==boom[0]) {//문자 시작 문자와 같다면 문자열 폭발 시도
				boom(answer,boom);
			}
			
		}
		
		StringBuilder sb=new StringBuilder();
		
		while(!answer.isEmpty()) {
			sb.append(answer.pop());
		}
		
		if(sb.toString().equals("")) {
			System.out.println("FRULA");
		}else {
			System.out.println(sb.toString());
		}
	}

	private static void boom(Stack<Character> answer, char[] boom) {
		Stack<Character> temp = new Stack<>();
		boolean canBoom=true;
		for (int i = 0; i < size; i++) {
			if(answer.isEmpty()) {
				canBoom=false;
				break;
			}
			char c=answer.pop();
			temp.push(c);
			if(boom[i]!=c) {
				canBoom=false;
				break;
			}
		}
		
		if(canBoom) return;
		
		while(!temp.isEmpty()) {
			answer.push(temp.pop());
		}
	}

}
