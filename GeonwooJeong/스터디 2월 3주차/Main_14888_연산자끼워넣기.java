import java.util.Scanner;

public class Main_14888_연산자끼워넣기 {
	
	static int N;
	static int [] nums; // 숫자들을 넣을 배열
	static int [] sign = new int[4]; // 연산자의 수를 넣을 배열
	static int max;
	static int min;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		N = scann.nextInt();
		nums = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = scann.nextInt();
		}
		
		for (int i = 0; i < 4; i++) {
			sign[i] = scann.nextInt();
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		perm(1, nums[0]); // 초기 value를 넣어주고, 연산 횟수는 N-1번이므로 cnt = 1을 초기값으로 준다.
		
		System.out.println(max);
		System.out.println(min);		
		
	}

	private static void perm(int cnt, int value) { // cnt : 연산 횟수, value : 계산한 값
		if(cnt == N) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(sign[i] <= 0) continue; // 연산자가 0개면 continue
			sign[i]--; // 연산자를 사용한 만큼 빼준다.
			
			switch (i) {
			case 0:
				perm(cnt+1, value+nums[cnt]); // + 계산한 값
				break;

			case 1:
				perm(cnt+1, value-nums[cnt]); // - 계산한 값
				break;
				
			case 2:
				perm(cnt+1, value*nums[cnt]); // * 계산한 값
				break;
				
			case 3:
				perm(cnt+1, value/nums[cnt]); // / 계산한 값
			}
			
			sign[i]++; // 반복문 이후에 연산자의 수를 되돌려준다.
		}
	}

}
