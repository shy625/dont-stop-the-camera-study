import java.util.Scanner;

public class Main_16953_AB {
	static long B, answer;
	static boolean end;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		Long A = scann.nextLong();
		B = scann.nextLong();
		
		dfs(A, 1);
		
		if(!end) System.out.println(-1);
		else System.out.println(answer);
	}

	private static void dfs(Long num, int cnt) {
		if(end) return;
		if(num > B) return;
		else if (num == B) {
			answer = cnt;
			end = true;
			return;
		}
		
		dfs(num*2, cnt+1);
		dfs(num*10+1, cnt+1);
		
	}

}
