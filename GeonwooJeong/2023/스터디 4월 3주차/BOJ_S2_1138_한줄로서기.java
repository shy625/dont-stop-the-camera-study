import java.util.Scanner;

public class BOJ_S2_1138_한줄로서기 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int N = scann.nextInt();
		int [] arr = new int[N];
		
		for (int i = 1; i <= N; i++) {
			// 나보다 큰 사람이 몇 명이 있는지 센다.
			int cnt = scann.nextInt();
			for (int j = 0; j < N; j++) {
				int n = arr[j];
				// j번째 자리가 비어있으면 0이고, 이미 차있으면 다른 숫자가 들어있을 것이다.
				if(n == 0) {
					// 자리가 비어있는데 나보다 큰 사람이 더이상 없으면 해당 자리는 나의 자리다.
					if(cnt == 0) {
						arr[j] = i;
						break;
					// 자리가 비어있는데 나보다 큰 사람이 남아있으면 나보다 큰 누군가의 자리일 것이므로 cnt만 빼준다.
					} else {
						cnt--;
					}
				}
				// j번째 자리가 차있다면(0이 아닌 숫자라면) 그냥 continue 하면 되므로 따로 코드는 작성하지 않는다.
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i]+" ");
		}

	}

}
