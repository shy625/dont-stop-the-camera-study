import java.util.Scanner;

public class Main_17626_FourSquares {

	static int n;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		n=scann.nextInt();
		int [] memo=new int[n+1];
		
		memo[1]=1;
		for (int i = 2; i <= n; i++) {
			int min=memo[i-1]+1;// 바로 전 수 + 1^2
			
			//현재 목표인 수 i 보다 작은j*j를 더해서 만들 수 있는 수가 있다면? 현재 목표 i에서 j*j를 뺸 위치의 갯수 +1을 하면 목적 수니까 최소만 구하면 된다
			for (int j = 1; j*j <= i; j++) {
				min=Math.min(min, memo[i-j*j]+1);
			}
			memo[i]=min;
		}
		
		System.out.println(memo[n]);
	}
}
