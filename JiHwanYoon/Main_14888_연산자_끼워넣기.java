
import java.util.Scanner;

public class Main_14888_연산자_끼워넣기 {
	static int N;
	// 피연산자 저장 배열
	static int[] nums;
	// 연산자 개수 저장 배열
	static int[] operators = new int[4];
	// 결과 저장 변수
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			operators[i] = sc.nextInt();
		}
		recur(0, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	// 앞에서부터 식을 계산하기 위해 재귀 함수 활용 
	// cnt는 연산 횟수, res는 지금까지의 연산 결과를 나타냄
	private static void recur(int cnt, int res) {
		// 연산을 N-1번 하면 계산이 끝난다.
		if (cnt == N-1) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		// 각 연산자에 대해 연산을 수행
		// 연산자 개수 조절에 주의
		if (operators[0] > 0) {
			operators[0]--;
			recur(cnt+1, res+nums[cnt+1]);
			operators[0]++;
		}
		if (operators[1] > 0) {
			operators[1]--;
			recur(cnt+1, res-nums[cnt+1]);
			operators[1]++;
		}
		if (operators[2] > 0) {
			operators[2]--;
			recur(cnt+1, res*nums[cnt+1]);
			operators[2]++;
		}
		if (operators[3] > 0) {
			operators[3]--;
			recur(cnt+1, res/nums[cnt+1]);
			operators[3]++;
		}
		
	}
}
