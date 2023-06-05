import java.util.Arrays;
import java.util.Scanner;

public class Main_11053_가장긴증가하는부분수열 {

	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		
		int N=scann.nextInt();
		int arr[]=new int[N+1];
		int dp[]=new int[N+1];
		int answer=1;
		for (int i = 1; i <= N; i++) {
			arr[i]=scann.nextInt();
			
		}
		
		Arrays.fill(dp, 1);
		for (int i = 2; i <= N; i++) {
			
			for (int j = 1; j < i; j++) {
				if(arr[i]>arr[j])
					dp[i]=Math.max(dp[i], dp[j]+1);
			}
			answer=Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
