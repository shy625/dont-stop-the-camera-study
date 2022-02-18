import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main_14888_연산자끼워넣기 {
	static int N; 
	static int[] numbers; 
	static int [] operNum; //+-*/
	static char [] Oper; // 최초에 연산자들을 저장해 놓는 곳
	static char [] permOper; // 연산자들을 배열해서 저장해 놓는 곳 
	static boolean [] visited;
	static int Min = Integer.MAX_VALUE;
	static int Max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers= new int[N];
		operNum = new int[4];
		Oper = new char[N-1];
		permOper = new char[N-1];
		visited = new boolean[N-1];
		for (int  i = 0 ; i < N ; i++) {
			numbers[i] = sc.nextInt();
		}
		for (int i = 0 ; i < 4 ; i++) {
			operNum[i] = sc.nextInt();
		}
		int index = 0;
		String operator = "+-*/";

		for (int i = 0 ; i < 4 ; i ++) {
			for (int j = 0 ; j < operNum[i] ; j++) {
				Oper[index] = operator.charAt(i);
				index++;
			}
		} // 여기까지 입력 처리.
		
		perm(0); // perm으로 연산자들 배열하는 경우의 수 모두 구함
		System.out.println(Max);
		System.out.println(Min);
	}
	
	public static void perm(int cnt) {
		if(cnt == N-1) {
			calculate(); // 연산자의 배열이 끝나면 해당 계산식의 값을 구한다.
			return;
		}
		
		for (int  i = 0 ; i < N-1 ; i++) {
			if(visited[i]) continue; 
			visited[i] = true;
			permOper[cnt] = Oper[i];
			perm(cnt+1);
			visited[i] = false;
		}
	}
	
	public static void calculate() {
		int first = numbers[0];
		for (int i = 0 ; i < N-1 ; i++) {
			int second = numbers[i+1];
			switch(permOper[i]) {
			case '+':
				first = first + second;
				break;
			case '-':
				first = first - second;
				break;
			case '*':
				first = first * second; 
				break; 
			case '/': 
				if (first < 0) {
					first = (first *-1)/second;
					first *= -1;
				}
				else {
					first=first / second;
				}
				break;
			}
		}
		Max = Math.max(Max,  first); // 계산식의 값을 구하고 나서는 min, max 와 비교하여 최대 최소값 설정해 준다.
		Min = Math.min(Min, first);
	}
}
