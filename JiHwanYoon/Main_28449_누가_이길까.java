

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_28449_누가_이길까 {
	static int N, M;
	static int[] A, B;
	static long HI, ARC, draw; // 최대 10^5 * 10^5 = 10^10까지 가능하므로, long 타입으로 선언해야 함에 유의
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		// 이분 탐색을 활용한다.
		// 우선 ARC 팀 내 팀원을 코딩실력 순으로 정렬한다.
		// HI 팀의 각 팀원에 대해서, ARC 팀 내 팀원 중 코딩실력이 더 높은 사람 중 가장 왼쪽에 있는 사람과,
		// 코딩실력이 더 높거나 같은 사람 중 가장 왼쪽에 있는 사람을 찾고 이를 바탕으로
		// HI 팀 승리 횟수, ARC 팀 승리 횟수, 무승부 횟수를 계산한다.
		Arrays.sort(A);
		Arrays.sort(B);
		HI = ARC = draw = 0;
		for (int i = 0; i < N; i++) {
			binarySearch(i);
		}
		System.out.println(HI+" "+ARC+" "+draw);
	}
	// 이분 탐색을 활용해 각 팀의 우승 횟수 및 무승부 횟수를 구하는 함수
	private static void binarySearch(int i) {
		int a = A[i]; // HI팀에서 i번째 팀원의 코딩실력
		// i번째 인원보다 더 높은 코딩실력을 가지는, 가장 왼쪽에 위치한 ARC팀 팀원을 찾는다.
		int l = 0;
		int r = M;
		while (l < r) {
			int mid = (l+r)/2;
			if (B[mid] > a) {
				r = mid;
			} else {
				l = mid+1;
			}
		}
		int idx1 = l;
		// i번째 인원보다 더 높거나 같은 코딩실력을 가지는, 가장 왼쪽에 위치한 ARC팀 팀원을 찾는다.
		l = 0;
		r = M;
		while (l < r) {
			int mid = (l+r)/2;
			if (B[mid] >= a) {
				r = mid;
			} else {
				l = mid+1;
			}
		}
		int idx2 = l;
		// 위에서 구한 결과를 바탕으로 HI팀, ARC팀의 우승 횟수 및 무승부 횟수를 구한다.
		HI += idx2;
		ARC += M - idx1;
		draw += idx1 - idx2;
	}
}
