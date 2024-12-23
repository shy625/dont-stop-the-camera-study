import java.util.Scanner;

public class BOJ_S3_1614_영식이의손가락 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		long M = scann.nextLong();
		long ans = 0;
		
		// 기본적으로 엄지부터 시작해서 다시 엄지로 돌아오는 한 사이클은 8이다.
		// 다친 손가락이 엄지나 새끼인 경우, 한 사이클에 1번씩만 포함된다.
		// 그 외의 손가락은 한  사이클에 2번씩 포함된다.
		// 기본적으로 사이클 횟수*8 로 가정하고, 각 손가락에 따라 조정한다.
		
		if(N == 1 || N == 5) {
			// 셀 수 있는 횟수 = 사이클 횟수 * 8
			ans = M*8;
			
			// 새끼 손가락을 다쳤으면 사이클 이외에 4번 더 셀 수 있다.
			if(N == 5) ans += 4;
		} else {
			// 사이클 횟수를 구하고 8을 곱해서 기본적으로 셀 수 있는 횟수를 구한다.
			ans = (M+1)/2*8;
			
			// 홀수인 경우에는 구한 횟수보다 덜 셀 수 있다. 다친 손가락에 따라 조정한다.
			if(M % 2 != 0) {
				ans -= (N-1);
			// 짝수인 경우에는 구한 횟수보다 더 셀 수 있다. 다친 손가락에 따라 조정한다.
			} else {
				ans += (N-1);
			}
		}
		
		System.out.println(ans);

	}

}
