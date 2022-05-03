import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Solution_11052_카드구매하기2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* IDEA
		 * 1장의 카드를 최대한 비싸게 구매하기 : sumPrice[1] <- P[1]
		 * 2장의 카드를 최대한 비싸게 구매하기 : P[1]+sumPrice[1] vs P[2]
		 * 3장의 카드를 최대한 비싸게 구매하기 : P[1]+sumPrice[2] vs P[2]+sumPrice[1] vs P[3]
		 * ...
		 * N장의 카드를 최대한 비싸게 구매하기 : P[1]+sumPrice[N-1] vs P[2]+sumPrice[N-2] vs ... vs P[N]
		 */

		// 입력
		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N+1];		// 카드팩의 가격
		String[] str = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			P[i+1] = Integer.parseInt(str[i]);
		}
		
		int[] sumPrice = new int[N+1];

		// i : 구매할 카드의 장수 (i장의 카드를 최대한 비싸게 구매하기)
		for(int i =1 ;i<=N; i++) {
			// j : 비교대상이 될 카드팩의 장수 (P[j]+sumePrice[N-j])
			for(int j=1 ; j<=i;j++) {
				sumPrice[i] = Math.max(sumPrice[i],P[j]+sumPrice[i-j]);
			}
		}
		
		System.out.println(sumPrice[N]);


	}
}
