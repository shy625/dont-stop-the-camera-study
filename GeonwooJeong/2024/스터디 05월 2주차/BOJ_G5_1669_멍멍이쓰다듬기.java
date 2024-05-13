import java.util.Scanner;

public class BOJ_G5_1669_멍멍이쓰다듬기 {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int x = scann.nextInt();
		int y = scann.nextInt();
		// 키의 차이를 저장할 변수
		int diff = y-x;
		
		// 키의 차이가 0이면 0을 출력하고 종료한다.
		if(diff == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		// N을 1부터 1씩 증가시키면서 최대로 올라갈 수 있는 숫자 N을 찾는다.
		long N = 1;
		while(N*N <= diff) {
			N++;
		}
		// while문 조건을 빠져나오면서 1을 더 더했기 때문에 1을 빼준다.
		N--;
		
		// 1부터 N까지 증가하면서 N*2-1일이 소요된다.
		long ans = N*2 - 1;
		// 소요 일수 만큼 diff에서 빼준다.
		diff -= N*N;
		
		// diff를 모두 소모할 때 까지 while문을 돌린다.
		while(diff > 0) {
			long tmp = N;
			// 최대한 큰 숫자부터 남은 diff에서 빼주고, ans를 ++해준다.
			while(tmp > 0) {
				if(tmp <= diff) {
					diff -= tmp;
					ans++;
					break;
				}
				tmp--;
			}
		}
		
		System.out.println(ans);

	}

}
