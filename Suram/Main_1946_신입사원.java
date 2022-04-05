import java.util.Scanner;

public class Main_1946_신입사원 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int T = scann.nextInt();
		for (int t = 1 ; t <= T ; t++) {
			int N = scann.nextInt();
			int [] arr = new int[N+1];
			int count = 1; 
			
			for (int i = 0 ; i < N ; i++) {
				int x = scann.nextInt();
				int y = scann.nextInt();
				arr[x]=y; // 처음에는 arr[N][2]의 형태로 해서 comparator로 정렬했지만 시간초과 문제 발생.
			}
			
			int first = arr[1]; // 첫번째 지원자의 면접 성적이 기준 
			for (int i = 2 ; i <= N ; i++) {
				if(arr[i] < first ) {
					first = arr[i];
					count ++;
					// 면접 성적이 그 전 선발된 지원자보다 뛰어날 경우 선발한다. 
				}
			}
			System.out.println(count);
		}
	}
}
